package fi.uba.parking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.User;
import fi.uba.parking.persistence.IUserDao;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional
	public Long saveUser(User user) {
		return this.userDao.save(user);
	}
}
