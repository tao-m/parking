package fi.uba.parking.service;

import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.User;

public interface IUserService {
	
	@Transactional
	Long saveUser(User user);

}
