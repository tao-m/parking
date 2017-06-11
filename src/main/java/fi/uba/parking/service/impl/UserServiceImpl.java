package fi.uba.parking.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.User;
import fi.uba.parking.geo.Coordinate;
import fi.uba.parking.geo.GeoClient;
import fi.uba.parking.geo.GeoClientResult;
import fi.uba.parking.geo.RequestResult;
import fi.uba.parking.notification.Notification;
import fi.uba.parking.notification.Notifier;
import fi.uba.parking.persistence.IUserDao;
import fi.uba.parking.service.IParkingService;
import fi.uba.parking.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Value("#{appConfig['avg.walk.speed']}")
	private Double avgWalkSpeed;

	@Autowired
	private GeoClient geoClient;

	@Autowired
	private IParkingService parkingService;
	
	@Autowired
	private Notifier notifier;

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

	@Override
	@Transactional
	public void updateUserPosition(Long id, Coordinate coordinate) {
		User user = this.userDao.getById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid User");

		Coordinate lastPosition = user.currentPosition();
		Date lastUpdate = user.getLastUpdate();

		user.setLastUpdate(new Date());
		user.updatePosition(coordinate);
		this.userDao.save(user);

		if (this.parkingService.checkForActivePaking(user))
			this.checkPositionNotification(coordinate, lastPosition,
					(user.getLastUpdate().getTime() - lastUpdate.getTime()) / 1000, user.getDevice().getKey());
	}

	private void checkPositionNotification(Coordinate currentPosition, Coordinate lastPosition, Long timeInSeconds, String device) {
		if (lastPosition == null)
			return;

		GeoClientResult<Long> distanceResult = this.geoClient.distance(lastPosition, currentPosition);
		if (distanceResult.getStatus() == RequestResult.OK) {
			Long distance = distanceResult.getResult();
			Double speed = (double) (distance / timeInSeconds);
			if (speed > this.avgWalkSpeed) {
				this.notifier.sendNotification(new Notification(Notification.MOVEMENT_MESSAGE, device));
			}
		}
	}

	@Override
	@Transactional
	public void updateUserDevice(Long id, String key) {
		User user = this.getUserById(id);
		if (user == null)
			throw new IllegalArgumentException("Invalid User");

		user.getDevice().setKey(key);

		this.saveUser(user);
	}
}
