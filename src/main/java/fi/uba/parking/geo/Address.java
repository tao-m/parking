package fi.uba.parking.geo;

import fi.uba.parking.domain.Coordinate;

public class Address {

	private final Coordinate coordinate;

	private final String route;

	private final Long number;

	private final String district;

	public Address(Coordinate coordinate, String route, Long number, String district) {
		this.coordinate = coordinate;
		this.route = route;
		this.number = number;
		this.district = district;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public String getRoute() {
		return route;
	}

	public Long getNumber() {
		return number;
	}

	public String getDistrict() {
		return district;
	}

}
