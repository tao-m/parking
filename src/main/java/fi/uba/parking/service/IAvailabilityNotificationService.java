package fi.uba.parking.service;

import fi.uba.parking.domain.Availability;
import fi.uba.parking.domain.User;

public interface IAvailabilityNotificationService {
	
	Long createNotification(Availability availability, String street, Long number, User user);

}
