package fi.uba.parking.persistence.impl;

import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.StreetSegment;
import fi.uba.parking.persistence.IStreetSegmentDao;

@Repository
public class StreetSegmentDaoImpl extends AbstractDao<StreetSegment, Long> implements IStreetSegmentDao{

}
