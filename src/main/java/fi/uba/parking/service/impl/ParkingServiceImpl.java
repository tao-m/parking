/**
 * 
 */
package fi.uba.parking.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.ParkingMode;
import fi.uba.parking.domain.ParkingRecord;
import fi.uba.parking.domain.ParkingStatus;
import fi.uba.parking.domain.StreetSegment;
import fi.uba.parking.domain.User;
import fi.uba.parking.geo.Address;
import fi.uba.parking.geo.Coordinate;
import fi.uba.parking.geo.GeoClient;
import fi.uba.parking.geo.GeoClientResult;
import fi.uba.parking.geo.RequestResult;
import fi.uba.parking.persistence.IParkingRecordDao;
import fi.uba.parking.service.IParkingService;
import fi.uba.parking.service.IStreetSegmentService;
import fi.uba.parking.service.IUserService;

/**
 * @author mario
 *
 */
@Service
public class ParkingServiceImpl implements IParkingService {

	@Autowired
	private GeoClient geoClient;

	@Autowired
	private IUserService userService;

	@Autowired
	private IStreetSegmentService steetService;

	@Value("#{appConfig['district']}")
	private String district;
	
	@Autowired
	private IParkingRecordDao parkingDao;

	@Override
	@Transactional
	public Long startParking(Long userId, Coordinate coordinate, String domain) {
		User user = this.userService.getUserById(userId);
		if(user == null)
			throw new IllegalArgumentException("Invalid User");
		
		GeoClientResult<Address> result = geoClient.reverseGeocode(coordinate);
		if(result.getStatus() != RequestResult.OK) 
			throw new IllegalArgumentException("Invalid Coordinate");
		
		Address address = result.getResult();
		if(!StringUtils.equals(district, address.getDistrict()))
			throw new IllegalArgumentException("Address out of reach");
		
		StreetSegment segment = this.steetService.findByAddress(address.getRoute(), address.getNumber());
		if(segment == null)
			segment = this.buildSegment(address);
		
		segment.takeSlot();
		steetService.saveSegment(segment);
		
		ParkingRecord record = new ParkingRecord(user, domain, ParkingMode.FREE, ParkingStatus.ON_GOING, 
								coordinate.getLatitude(), coordinate.getLongitude(), segment, new Date(), null);
		
		return parkingDao.save(record);
	}

	@Override
	public void stopParking(Long userId, Long parkingId) {
		// TODO Auto-generated method stub

	}

	// This is only to make things faste during testing, remove
	private StreetSegment buildSegment(Address address) {
		Long nbr = address.getNumber();
		Long from = nbr - nbr % 100;
		Long to = from + 99;

		return new StreetSegment(address.getRoute(), address.getRoute(), from, to, StreetSegment.DEFAULT_CAPACITY,
				StreetSegment.DEFAULT_CAPACITY);
	}

}
