package fi.uba.parking.service;

import fi.uba.parking.domain.User;

public interface IUserService {
	
	Long saveUser(User user);

	User getUserById(Long id);
}
