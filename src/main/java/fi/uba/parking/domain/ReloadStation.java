package fi.uba.parking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "RELOAD_STATIONS")
public class ReloadStation {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@Column(name = "display_info")
	private String displayInfo;

	@Column(name = "address")
	private String address;

	public ReloadStation() {
	}

	public ReloadStation(Double latitude, Double longitude, String displayInfo, String address) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.displayInfo = displayInfo;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(String displayInfo) {
		this.displayInfo = displayInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(latitude).append(longitude).append(id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReloadStation other = (ReloadStation) obj;

		return new EqualsBuilder().append(latitude, other.latitude).append(longitude, other.longitude)
				.append(id, other.id).isEquals();
	}

}
