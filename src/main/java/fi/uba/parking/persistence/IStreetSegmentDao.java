package fi.uba.parking.persistence;

import java.util.List;

import fi.uba.parking.domain.StreetSegment;

public interface IStreetSegmentDao  extends GenericDao<StreetSegment, Long>{

	StreetSegment findByAddress(String street, Long number);
	
	List<StreetSegment> findSegmentByRangeAndName(String street, Long from, Long to);

}
