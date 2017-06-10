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
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Device.class)
	@JoinColumn(name = "id_segment", nullable = false)
	private StreetSegment segment;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Device.class)
	@JoinColumn(name = "id_account", nullable = false)
	private User acount;

	public AvailabilityNotification(StreetSegment segment, User acount) {
		super();
		this.segment = segment;
		this.acount = acount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StreetSegment getSegment() {
		return segment;
	}

	public void setSegment(StreetSegment segment) {
		this.segment = segment;
	}

	public User getAcount() {
		return acount;
	}

	public void setAcount(User acount) {
		this.acount = acount;
	}

}
