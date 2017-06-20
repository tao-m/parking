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
}
