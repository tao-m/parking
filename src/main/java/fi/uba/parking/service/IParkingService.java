package fi.uba.parking.service;

import java.math.BigInteger;

import fi.uba.parking.domain.ParkingRecord;
import fi.uba.parking.domain.User;
import fi.uba.parking.geo.Coordinate;

public interface IParkingService {
	
	Long startParking(Long userId, Coordinate coordinate, String domain);
	
	BigInteger stopParking(Long userId, Long parkingId);

	ParkingRecord findActiveRecordByUser(User user);

	boolean checkForActivePaking(User user);
}
