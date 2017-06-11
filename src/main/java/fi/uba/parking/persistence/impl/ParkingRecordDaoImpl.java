package fi.uba.parking.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.ParkingRecord;
import fi.uba.parking.domain.ParkingStatus;
import fi.uba.parking.domain.User;
import fi.uba.parking.persistence.IParkingRecordDao;

@Repository
public class ParkingRecordDaoImpl extends AbstractDao<ParkingRecord, Long> implements IParkingRecordDao{

	@Override
	public ParkingRecord findActiveRecordByUser(User user) {
		Criteria criteria = getSession().createCriteria(ParkingRecord.class);

		criteria.add(Restrictions.eq("status", ParkingStatus.ON_GOING));
		criteria.add(Restrictions.eq("account", user));
		
		return (ParkingRecord) criteria.uniqueResult();
	}

}
