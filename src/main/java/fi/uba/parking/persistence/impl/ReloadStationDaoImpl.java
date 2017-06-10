package fi.uba.parking.persistence.impl;

import org.springframework.stereotype.Repository;

import fi.uba.parking.domain.ReloadStation;
import fi.uba.parking.persistence.IReloadStationDao;

@Repository
public class ReloadStationDaoImpl extends AbstractDao<ReloadStation, Long> implements IReloadStationDao{

}
