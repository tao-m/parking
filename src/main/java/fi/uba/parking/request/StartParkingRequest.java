package fi.uba.parking.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StartParkingRequest", propOrder = { "lat", "lng" })
@XmlRootElement(name = "StartParkingRequest")
public class StartParkingRequest {

	private Double lat;
	private Double lng;

	public Double getLat() {
		return lat;
	}

	public Double getLng() {
		return lng;
	}

}
