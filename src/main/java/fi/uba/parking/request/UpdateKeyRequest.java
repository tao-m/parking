package fi.uba.parking.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateKeyRequest", propOrder = { "device" })
@XmlRootElement(name = "UpdateKeyRequest")
public class UpdateKeyRequest {

	private String device;

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
	
	

}
