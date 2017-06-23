/*
 * GeoUtils.java
 * 
 * Created on 18/2/2015
 * 
 */
package fi.uba.parking.geo;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import fi.uba.parking.geo.Coordinate;

public class GeoUtils {

	public static final double NORTH = 0.0;
	public static final double SOUTH = 180.0;
	public static final double EAST = 90.0;
	public static final double WEST = 270.0;

	public static Coordinate moveCoordinateInMiles(Double latitude, Double longitude, Long distance,
			Double bearingAngle) {

		LatLng movedLatLon = LatLngTool.travel(new LatLng(latitude, longitude), bearingAngle, distance.doubleValue(),
				LengthUnit.METER);

		return new Coordinate(movedLatLon.getLatitude(), movedLatLon.getLongitude());
	}

	public static double distance(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters
		return distance;
	}
}
