package fi.uba.parking.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.Availability;
import fi.uba.parking.domain.StreetSegment;
import fi.uba.parking.domain.User;
import fi.uba.parking.persistence.IStreetSegmentDao;
import fi.uba.parking.service.IAvailabilityNotificationService;
import fi.uba.parking.service.IStreetSegmentService;
import fi.uba.parking.service.IUserService;

@Service
public class StreetSegmentServiceImpl implements IStreetSegmentService{
	
	@Autowired
	private IStreetSegmentDao segmentDao;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IAvailabilityNotificationService availabilityService;


	@Override
	@Transactional
	public StreetSegment findByAddress(String street, Long number) {
		return segmentDao.findByAddress(street, number);
	}
	
	@Override
	@Transactional
	public void saveSegment(StreetSegment segment) {
		this.segmentDao.saveOrUpdate(segment);
	}

	@Override
	@Transactional
	public Availability checkAvailability(Long userId, String street, Long number, Boolean notify) {
		User user = this.userService.getUserById(userId);
		if (user == null)
			throw new IllegalArgumentException("Invalid User");
		
		Long from = (number - number % 100);
		Long to = from + 199;
		from = from - 100;
		
		List<StreetSegment> segments = this.segmentDao.findSegmentByRangeAndName(street, from, to);
		if(CollectionUtils.isEmpty(segments)) 
			throw new IllegalArgumentException("Street not found");
		
		Long totalCapacity = 0L;
		Long remainingCapacity = 0L;
		
		for(StreetSegment s : segments) {
			totalCapacity += s.getVehicleCapacity();
			remainingCapacity += s.getRemainingCapacity();
		}
		
		Availability availability = Availability.calculateAvalability(totalCapacity, remainingCapacity);
		
		if(notify){
			this.availabilityService.createNotification(availability, street, number, user);
		}		
		return availability;
	}

}
