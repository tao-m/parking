package fi.uba.parking.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.User;
import fi.uba.parking.persistence.IUserDao;
import fi.uba.parking.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional
	public Long saveUser(User user) {
		user.setLastUpdate(new Date());
		return this.userDao.save(user);
	}

	@Override
	@Transactional
	public User getUserById(Long id) {
		return this.userDao.getById(id);
	}
}
