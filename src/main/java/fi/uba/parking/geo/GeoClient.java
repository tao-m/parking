package fi.uba.parking.geo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.AddressComponentType;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixElementStatus;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

@Component
public class GeoClient {

	private GeoApiContext context;
	private String district;

	@Autowired
	public GeoClient(@Value("#{appConfig['maps.key']}") String key,
			@Value("#{appConfig['district']}") String district) {
		this.context = new GeoApiContext().setApiKey(key);
		this.district = district;
	}

	public GeoClientResult<Coordinate> geoCodeAddress(String route, Long number) {
		try {
			GeocodingResult[] results = GeocodingApi.geocode(context, this.buildGeocodeRequest(route, number)).await();
			if (results.length != 0) {
				LatLng location = results[0].geometry.location;
				return new GeoClientResult<Coordinate>(RequestResult.OK, new Coordinate(location.lat, location.lng));
			} else {
				return new GeoClientResult<Coordinate>(RequestResult.INVALID, null);
			}
		} catch (Exception e) {
			return new GeoClientResult<Coordinate>(RequestResult.ERROR, null);
		}
	}

	public GeoClientResult<Address> reverseGeocode(Coordinate coordinate) {
		try {
			GeocodingResult[] results = GeocodingApi
					.reverseGeocode(context, new LatLng(coordinate.getLatitude(), coordinate.getLongitude())).await();

			if (results.length != 0) {
				AddressComponent[] components = results[0].addressComponents;

				Address address = new Address(coordinate, this.getComponent(components, AddressComponentType.ROUTE),
						this.getNumber(this.getComponent(components, AddressComponentType.STREET_NUMBER)),
						this.getComponent(components, AddressComponentType.LOCALITY));

				return new GeoClientResult<Address>(RequestResult.OK, address);
			} else {
				return new GeoClientResult<Address>(RequestResult.INVALID, null);
			}
		} catch (Exception e) {
			return new GeoClientResult<Address>(RequestResult.ERROR, null);
		}
	}

	public GeoClientResult<Long> distance(Coordinate origin, Coordinate destination) {
		try {
			DistanceMatrix distanceMatrix = DistanceMatrixApi.newRequest(context)
					.origins(new LatLng(origin.getLatitude(), origin.getLongitude()))
					.destinations(new LatLng(destination.getLatitude(), destination.getLongitude()))
					.mode(TravelMode.WALKING).await();

			DistanceMatrixElement result = distanceMatrix.rows[0].elements[0];
			if (result.status == DistanceMatrixElementStatus.OK) {
				return new GeoClientResult<Long>(RequestResult.OK, result.distance.inMeters);
			} else {
				return new GeoClientResult<Long>(RequestResult.INVALID, null);
			}
		} catch (Exception e) {
			return new GeoClientResult<Long>(RequestResult.ERROR, null);
		}
	}

	public List<Long> distance(Coordinate origin, List<Coordinate> destinations) {
		try {
			LatLng[] coords = new LatLng[destinations.size()];
			for (int i = 0; i < destinations.size(); i++) {
				Coordinate c = destinations.get(i);
				coords[i] = new LatLng(c.getLatitude(), c.getLongitude());
			}

			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context)
					.origins(new LatLng(origin.getLatitude(), origin.getLongitude())).destinations(coords);

			DistanceMatrixElement[] result = req.mode(TravelMode.WALKING).await().rows[0].elements;
			List<Long> ret = new ArrayList<Long>();
			for (DistanceMatrixElement e : result) {
				ret.add(e.distance.inMeters);
			}
			return ret;
		} catch (Exception e) {
			return null;
		}
	}

	private String buildGeocodeRequest(String route, Long number) {
		StringBuffer sb = new StringBuffer(number.toString());
		sb.append(" ").append(route).append(",").append(this.district);
		return sb.toString();
	}

	private String getComponent(AddressComponent[] components, AddressComponentType type) {
		for (AddressComponent c : components) {
			if (c.types[0] == type) {
				return c.longName;
			}
		}
		return "";
	}

	private Long getNumber(String number) {
		if (number.contains("-")) {
			return Long.valueOf(number.substring(0, number.indexOf("-")));
		}
		return Long.valueOf(number);
	}

}
