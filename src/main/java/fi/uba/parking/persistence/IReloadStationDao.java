package fi.uba.parking.persistence;

import java.util.List;

import fi.uba.parking.domain.ReloadStation;
import fi.uba.parking.geo.Area;

public interface IReloadStationDao  extends GenericDao<ReloadStation, Long>{


	List<ReloadStation> searchByArea(Area area);
}
