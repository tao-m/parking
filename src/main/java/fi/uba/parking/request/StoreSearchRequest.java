package fi.uba.parking.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StoreSearchRequest", propOrder = { "lat", "lng", "searchRadio" })
@XmlRootElement(name = "StoreSearchRequest")
public class StoreSearchRequest {

	private Double lat;
	private Double lng;
	private Long searchRadio;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Long getSearchRadio() {
		return searchRadio;
	}

	public void setSearchRadio(Long searchRadio) {
		this.searchRadio = searchRadio;
	}

}
