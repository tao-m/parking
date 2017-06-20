/*
 * Area.java
 * 
 * Created on Mar 18, 2015
 */
package fi.uba.parking.geo;

import fi.uba.parking.geo.GeoUtils;

public class Area {

	private final Coordinate centralCoordinate;
	private final Long radius;

	public Area(Double latitude, Double longitude, Long radius) {
		this(new Coordinate(latitude, longitude), radius);
	}

	public Area(Coordinate centralCoordinate, Long radius) {
		this.centralCoordinate = centralCoordinate;
		this.radius = radius;
		validate();
	}

	private void validate() {
		if (radius == null)
			throw new IllegalArgumentException("Missing values to build area");
	}

	public Double getLatitudeUpperBound() {
		return GeoUtils.moveCoordinateInMiles(centralCoordinate.getLatitude(), centralCoordinate.getLongitude(), radius,
				GeoUtils.NORTH).getLatitude();

	}

	public Double getLatitudeLowerBound() {
		return GeoUtils.moveCoordinateInMiles(centralCoordinate.getLatitude(), centralCoordinate.getLongitude(), radius,
				GeoUtils.SOUTH).getLatitude();

	}

	public Double getLongitudeUpperBound() {
		return GeoUtils.moveCoordinateInMiles(centralCoordinate.getLatitude(), centralCoordinate.getLongitude(), radius,
				GeoUtils.EAST).getLongitude();

	}

	public Double getLongitudeLowerBound() {
		return GeoUtils.moveCoordinateInMiles(centralCoordinate.getLatitude(), centralCoordinate.getLongitude(), radius,
				GeoUtils.WEST).getLongitude();

	}

}
