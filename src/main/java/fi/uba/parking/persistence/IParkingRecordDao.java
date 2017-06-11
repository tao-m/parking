package fi.uba.parking.persistence;

import fi.uba.parking.domain.ParkingRecord;
import fi.uba.parking.domain.User;

public interface IParkingRecordDao  extends GenericDao<ParkingRecord, Long>{
	
	ParkingRecord findActiveRecordByUser(User user);
}
