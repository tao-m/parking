package fi.uba.parking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVICES")
public class Device {

	@Id
	@Column(name="id")
	@GeneratedValue
	private Long id;
	
	@Column(name="app_key")
	private String key;
	
	@Column(name="active")
	private boolean active;

	public Device() {
	}

	public Device(String key, boolean active) {
		super();
		this.key = key;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
