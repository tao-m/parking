package fi.uba.parking.persistence.impl;

import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.Device;
import fi.uba.parking.persistence.IDeviceDao;

@Repository
public class DeviceDaoImpl extends AbstractDao<Device, Long> implements IDeviceDao{

}
