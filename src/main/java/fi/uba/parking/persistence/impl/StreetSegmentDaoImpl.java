package fi.uba.parking.persistence.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.StreetSegment;
import fi.uba.parking.persistence.IStreetSegmentDao;

@Repository
public class StreetSegmentDaoImpl extends AbstractDao<StreetSegment, Long> implements IStreetSegmentDao {

	@Override
	public StreetSegment findByAddress(String street, Long number) {
		Criteria criteria = getSession().createCriteria(StreetSegment.class);

		criteria.add(Restrictions.eq("displayName", street));
		criteria.add(Restrictions.le("addressFrom", number));
		criteria.add(Restrictions.ge("addressTo", number));

		return (StreetSegment) criteria.uniqueResult();
	}

}
