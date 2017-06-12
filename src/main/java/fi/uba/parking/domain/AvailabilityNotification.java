package fi.uba.parking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATIONS")
public class AvailabilityNotification {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "street")
	private String street;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Device.class)
	@JoinColumn(name = "id_account", nullable = false)
	private User acount;

	@Column(name = "number")
	private Long number;

	@Column(name = "last_status")
	private Availability lastStatus;

	public AvailabilityNotification(String street, User acount, Long number, Availability lastStatus) {
		super();
		this.acount = acount;
		this.number = number;
		this.lastStatus = lastStatus;
		this.street = street;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public User getAcount() {
		return acount;
	}

	public void setAcount(User acount) {
		this.acount = acount;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Availability getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(Availability lastStatus) {
		this.lastStatus = lastStatus;
	}

}
