package fi.uba.parking.response;

import fi.uba.parking.domain.ReloadStation;

public class ReloadLocationDto {

	private final Long id;
	private final Double latitude;
	private final Double longitude;

	private final String displayInfo;
	private final String address;
	
	private final Long distance;
	
	public ReloadLocationDto(ReloadStation station, Long distance) {
		this.id = station.getId();
		this.latitude = station.getLatitude();
		this.longitude = station.getLongitude();
		this.displayInfo = station.getDisplayInfo();
		this.address = station.getAddress();
		this.distance = distance;
	}

	public Long getId() {
		return id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getDisplayInfo() {
		return displayInfo;
	}

	public String getAddress() {
		return address;
	}

	public Long getDistance() {
		return distance;
	}
	
	
}
