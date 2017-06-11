package fi.uba.parking.service;

import fi.uba.parking.domain.User;
import fi.uba.parking.geo.Coordinate;

public interface IUserService {
	
	Long saveUser(User user);

	User getUserById(Long id);

	void updateUserPosition(Long id, Coordinate coordinate);
	
	void updateUserDevice(Long id, String key);
}
