package fi.uba.parking.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckAvilabilityRequest", propOrder = { "street", "number", "enableNotifications" })
@XmlRootElement(name = "CheckAvilabilityRequest")
public class CheckAvilabilityRequest {

	private String street;
	
	private Long number;
	
	private boolean enableNotifications;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public boolean isEnableNotifications() {
		return enableNotifications;
	}

	public void setEnableNotifications(boolean enableNotifications) {
		this.enableNotifications = enableNotifications;
	}

}
