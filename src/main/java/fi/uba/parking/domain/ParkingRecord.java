package fi.uba.parking.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "PARKING_RECORDS")
public class ParkingRecord {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
	@JoinColumn(name = "id_account", nullable = false)
	private User account;

	@Column(name = "vehicleDomain")
	private String vehicleDomain;

	@Column(name = "mode")
	private ParkingMode mode;

	@Column(name = "status")
	private ParkingStatus status;

	@Column(name = "latitude")
	private Double latitude;

	@Column(name = "longitude")
	private Double longitude;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = StreetSegment.class)
	@JoinColumn(name = "id_street_segment", nullable = false)
	private StreetSegment streetSegment;

	private Date startTime;
	private Date endTime;

	public ParkingRecord() {
	}

	public ParkingRecord(User account, String vehicleDomain, ParkingMode mode, ParkingStatus status, Double lat,
			Double lon, StreetSegment streetSegment, Date startTime, Date endTime) {
		super();
		this.account = account;
		this.vehicleDomain = vehicleDomain;
		this.mode = mode;
		this.status = status;
		this.latitude = lat;
		this.longitude = lon;
		this.streetSegment = streetSegment;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAccount() {
		return account;
	}

	public void setAccount(User account) {
		this.account = account;
	}

	public String getVehicleDomain() {
		return vehicleDomain;
	}

	public void setVehicleDomain(String vehicleDomain) {
		this.vehicleDomain = vehicleDomain;
	}

	public ParkingMode getMode() {
		return mode;
	}

	public void setMode(ParkingMode mode) {
		this.mode = mode;
	}

	public ParkingStatus getStatus() {
		return status;
	}

	public void setStatus(ParkingStatus status) {
		this.status = status;
	}

	public StreetSegment getStreetSegment() {
		return streetSegment;
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

	public void setStreetSegment(StreetSegment streetSegment) {
		this.streetSegment = streetSegment;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(account).append(latitude).append(longitude).append(endTime).append(id)
				.append(mode).append(startTime).append(status).append(streetSegment).append(vehicleDomain).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingRecord other = (ParkingRecord) obj;

		return new EqualsBuilder().append(account, other.account).append(latitude, other.latitude)
				.append(longitude, other.longitude).append(endTime, other.endTime).append(id, other.id)
				.append(mode, other.mode).append(startTime, other.startTime).append(status, other.status)
				.append(streetSegment, other.streetSegment).append(vehicleDomain, other.vehicleDomain).isEquals();
	}

}
