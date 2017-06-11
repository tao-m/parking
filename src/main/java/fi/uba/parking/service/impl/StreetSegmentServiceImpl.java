package fi.uba.parking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fi.uba.parking.domain.StreetSegment;
import fi.uba.parking.persistence.IStreetSegmentDao;
import fi.uba.parking.service.IStreetSegmentService;

@Service
public class StreetSegmentServiceImpl implements IStreetSegmentService{
	
	@Autowired
	private IStreetSegmentDao segmentDao;

	@Override
	@Transactional
	public StreetSegment findByAddress(String street, Long number) {
		return segmentDao.findByAddress(street, number);
	}
	
	@Override
	@Transactional
	public void saveSegment(StreetSegment segment) {
		this.segmentDao.saveOrUpdate(segment);
	}

}
