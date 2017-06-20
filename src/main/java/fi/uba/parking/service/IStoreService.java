package fi.uba.parking.service;

import java.util.List;

import fi.uba.parking.domain.ReloadStation;
import fi.uba.parking.geo.Coordinate;

public interface IStoreService {
	
	Long createStore(String storeName, Coordinate position);

	List<ReloadStation> search(Coordinate center, Long searchRadio);

	List<ReloadStation> searchAll();
	
	void delete(Long storeId);
}
