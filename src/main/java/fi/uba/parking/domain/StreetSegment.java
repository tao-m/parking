package fi.uba.parking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STREET_SEGMENTS")
public class StreetSegment {

	public static final Long DEFAULT_CAPACITY = 4L;
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "codeName")
	private String codeName;

	@Column(name = "displayName")
	private String displayName;

	@Column(name = "addressFrom")
	private long addressFrom;

	@Column(name = "addressTo")
	private long addressTo;

	@Column(name = "vehicleCapacity")
	private long vehicleCapacity;

	@Column(name = "remainingCapacity")
	private long remainingCapacity;

	public StreetSegment() {
	}

	public StreetSegment(String codeName, String displayName, long addressFrom, long addressTo, long vehicleCapacity,
			long remainingCapacity) {
		super();
		this.codeName = codeName;
		this.displayName = displayName;
		this.addressFrom = addressFrom;
		this.addressTo = addressTo;
		this.vehicleCapacity = vehicleCapacity;
		this.remainingCapacity = remainingCapacity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public long getAddressFrom() {
		return addressFrom;
	}

	public void setAddressFrom(long addressFrom) {
		this.addressFrom = addressFrom;
	}

	public long getAddressTo() {
		return addressTo;
	}

	public void setAddressTo(long addressTo) {
		this.addressTo = addressTo;
	}

	public long getVehicleCapacity() {
		return vehicleCapacity;
	}

	public void setVehicleCapacity(long vehicleCapacity) {
		this.vehicleCapacity = vehicleCapacity;
	}

	public long getRemainingCapacity() {
		return remainingCapacity;
	}

	public void setRemainingCapacity(long remainingCapacity) {
		this.remainingCapacity = remainingCapacity;
	}

	public void takeSlot() {
		this.remainingCapacity = (this.remainingCapacity == 0) ? 0 : this.remainingCapacity - 1L;
	}

	public void releaseSlot() {
		this.remainingCapacity = (this.remainingCapacity == this.vehicleCapacity) ? this.vehicleCapacity
				: this.remainingCapacity + 1L;
	}
}
