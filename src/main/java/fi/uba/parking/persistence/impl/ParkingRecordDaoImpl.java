package fi.uba.parking.persistence.impl;

import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.ParkingRecord;
import fi.uba.parking.persistence.IParkingRecordDao;

@Repository
public class ParkingRecordDaoImpl extends AbstractDao<ParkingRecord, Long> implements IParkingRecordDao{

}
