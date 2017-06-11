package fi.uba.parking.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import fi.uba.parking.geo.Coordinate;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;

	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dni")
	private String dni;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Device.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_device", nullable = false)
	private Device device;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Credit.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_credit", nullable = false)
	private Credit availableCredit;

	@Column(name="active")
	private boolean active;

	@Column(name="creationDate")
	private Date creationDate;

	@Column(name="deleteDate")
	private Date deleteDate;
	
	@Column(name="lastUpdate")
	private Date lastUpdate;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longitude")
	private Double longitude;

	public User() {
	}

	public User(String name, String email, String dni, Device devices, Credit availableCredit,
			boolean active, Date creationDate, Date deleteDate) {
		super();
		this.name = name;
		this.email = email;
		this.dni = dni;
		this.device = devices;
		this.availableCredit = availableCredit;
		this.active = active;
		this.creationDate = creationDate;
		this.deleteDate = deleteDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevices(Device devices) {
		this.device = devices;
	}

	public Credit getAvailableCredit() {
		return availableCredit;
	}

	public void setAvailableCredit(Credit availableCredit) {
		this.availableCredit = availableCredit;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
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

	public void setDevice(Device device) {
		this.device = device;
	}

	public void updatePosition(Coordinate coordinate) {
		this.latitude = coordinate.getLatitude();
		this.longitude = coordinate.getLongitude();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(active).append(availableCredit).append(creationDate).append(deleteDate)
				.append(device).append(dni).append(email).append(id).append(name).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return new EqualsBuilder().append(active, other.active).append(availableCredit, other.availableCredit)
				.append(creationDate, other.creationDate).append(deleteDate, other.deleteDate)
				.append(device, other.device).append(dni, other.dni).append(email, other.email).append(id, other.id)
				.append(name, other.name).isEquals();

	}

}
