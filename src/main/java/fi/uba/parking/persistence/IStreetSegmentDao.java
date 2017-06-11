package fi.uba.parking.persistence;

import fi.uba.parking.domain.StreetSegment;

public interface IStreetSegmentDao  extends GenericDao<StreetSegment, Long>{

	StreetSegment findByAddress(String street, Long number);

}
