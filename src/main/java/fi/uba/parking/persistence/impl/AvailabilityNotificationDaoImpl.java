package fi.uba.parking.persistence.impl;

import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.AvailabilityNotification;
import fi.uba.parking.persistence.IAvailabilityNotificationDao;

@Repository
public class AvailabilityNotificationDaoImpl extends AbstractDao<AvailabilityNotification, Long> implements IAvailabilityNotificationDao {

}
