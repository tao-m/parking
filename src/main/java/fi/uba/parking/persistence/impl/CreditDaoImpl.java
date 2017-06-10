package fi.uba.parking.persistence.impl;

import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.Credit;
import fi.uba.parking.persistence.ICreditDao;

@Repository
public class CreditDaoImpl extends AbstractDao<Credit, Long> implements ICreditDao{

}
