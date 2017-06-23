package fi.uba.parking.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.ReloadStation;
import fi.uba.parking.geo.Address;
import fi.uba.parking.geo.Area;
import fi.uba.parking.geo.Coordinate;
import fi.uba.parking.geo.GeoClient;
import fi.uba.parking.geo.GeoClientResult;
import fi.uba.parking.geo.RequestResult;
import fi.uba.parking.persistence.IReloadStationDao;
import fi.uba.parking.response.ReloadLocationDto;
import fi.uba.parking.service.IStoreService;

@Service
public class StoreServiceImpl implements IStoreService {

	public static final Long MIN_SEARCH_RADIO = 300L;

	@Autowired
	private IReloadStationDao stationDao;

	@Autowired
	private GeoClient geoClient;

	@Override
	@Transactional
	public Long createStore(String storeName, Coordinate position) {
		GeoClientResult<Address> result = this.geoClient.reverseGeocode(position);
		if (result.getStatus() != RequestResult.OK)
			throw new IllegalArgumentException("Invalid Address");

		Address address = result.getResult();
		Coordinate validatedPos = address.getCoordinate();
		ReloadStation station = new ReloadStation(validatedPos.getLatitude(), validatedPos.getLongitude(), storeName,
				address.getRoute() + " " + address.getNumber());

		return this.stationDao.save(station);
	}

	@Override
	@Transactional
	public List<ReloadLocationDto> search(Coordinate center, Long searchRadio) {
		List<ReloadLocationDto> ret = new ArrayList<>();
		Long radio = (searchRadio != null && searchRadio > MIN_SEARCH_RADIO) ? searchRadio : MIN_SEARCH_RADIO;
		Area searchArea = new Area(center, radio);
		List<ReloadStation> stations = this.stationDao.searchByArea(searchArea);
		if(CollectionUtils.isNotEmpty(stations)) {
			List<Coordinate> coords = new ArrayList<>();
			for(ReloadStation rs : stations)
				coords.add(new Coordinate(rs.getLatitude(), rs.getLongitude()));
			
			List<Long> distances = this.geoClient.distance(center, coords);
			
			for(int i = 0; i < stations.size(); i++)
				ret.add(new ReloadLocationDto(stations.get(i), distances.get(i)));
			Collections.sort(ret, new Comparator<ReloadLocationDto>() {
                @Override
                public int compare(ReloadLocationDto dto1, ReloadLocationDto dto2) {
                    return Long.compare(dto1.getDistance(), dto2.getDistance());
                }
            });
		}
		return ret;
	}

	@Override
	@Transactional
	public List<ReloadStation> searchAll() {
		return this.stationDao.getAll();
	}

	@Override
	@Transactional
	public void delete(Long storeId) {
		ReloadStation station = this.stationDao.getById(storeId);
		if (station == null)
			throw new IllegalArgumentException("Invalid Store Id");
		this.stationDao.delete(station);
	}

}
