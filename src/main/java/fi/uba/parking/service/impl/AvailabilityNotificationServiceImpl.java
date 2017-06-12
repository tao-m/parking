package fi.uba.parking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.Availability;
import fi.uba.parking.domain.AvailabilityNotification;
import fi.uba.parking.domain.User;
import fi.uba.parking.persistence.IAvailabilityNotificationDao;
import fi.uba.parking.service.IAvailabilityNotificationService;

@Service
public class AvailabilityNotificationServiceImpl implements IAvailabilityNotificationService {

	@Autowired
	private IAvailabilityNotificationDao availabilityDao;

	@Override
	@Transactional
	public Long createNotification(Availability availability, String street, Long number, User user) {
		AvailabilityNotification notification = new AvailabilityNotification(street, user, number, availability);

		return this.availabilityDao.save(notification);
	}

}
