package fi.uba.parking.persistence.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.ParkingRecord;
import fi.uba.parking.domain.ReloadStation;
import fi.uba.parking.geo.Area;
import fi.uba.parking.persistence.IReloadStationDao;

@Repository
public class ReloadStationDaoImpl extends AbstractDao<ReloadStation, Long> implements IReloadStationDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<ReloadStation> searchByArea(Area area) {
		Criteria criteria = getSession().createCriteria(ReloadStation.class);

		criteria.add(Restrictions.le("latitude", area.getLatitudeUpperBound()));
        criteria.add(Restrictions.ge("latitude", area.getLatitudeLowerBound()));
        
        criteria.add(Restrictions.le("longitude", area.getLongitudeUpperBound()));
        criteria.add(Restrictions.ge("longitude", area.getLongitudeLowerBound()));

		return criteria.list();
	}

}
