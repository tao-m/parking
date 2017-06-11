package fi.uba.parking.service;

import fi.uba.parking.geo.Coordinate;

public interface IParkingService {
	
	Long startParking(Long userId, Coordinate coordinate, String domain);
	
	void stopParking(Long userId, Long parkingId);

}
