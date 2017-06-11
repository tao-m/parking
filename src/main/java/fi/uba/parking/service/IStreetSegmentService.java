package fi.uba.parking.service;

import fi.uba.parking.domain.StreetSegment;

public interface IStreetSegmentService {

	StreetSegment findByAddress(String street, Long number);

	void saveSegment(StreetSegment segment);
}
