package fi.uba.parking.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.uba.parking.domain.Availability;
import fi.uba.parking.geo.Coordinate;
import fi.uba.parking.request.CheckAvilabilityRequest;
import fi.uba.parking.request.StartParkingRequest;
import fi.uba.parking.request.UpdateKeyRequest;
import fi.uba.parking.request.UpdatePositionRequest;
import fi.uba.parking.service.IParkingService;
import fi.uba.parking.service.IStreetSegmentService;
import fi.uba.parking.service.IUserService;

@Component
@Path("/user")
public class UserResource {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

	@Autowired
	private IParkingService parkingService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IStreetSegmentService segmentService;

	@PUT
	@Path("/{id}/position")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updatePosition(final UpdatePositionRequest req, @PathParam("id") final Long id) {
		try {
			userService.updateUserPosition(id, new Coordinate(req.getLat(), req.getLng()));
			return Response.ok().build();
		} catch (IllegalArgumentException e) {
			return this.buildErrorResponse(Status.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: ", e);
			return this.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@POST
	@Path("/{id}/parking")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response startParking(final StartParkingRequest req, @PathParam("id") final Long id) {
		try {
			return Response
					.ok(parkingService.startParking(id, new Coordinate(req.getLat(), req.getLng()), req.getDomain()))
					.build();
		} catch (IllegalArgumentException e) {
			return this.buildErrorResponse(Status.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: ", e);
			return this.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}

	}

	@DELETE
	@Path("/{userId}/parking/{parkingId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response stopParking(@PathParam("userId") final Long userId, @PathParam("parkingId") final Long parkingId) {
		try {
			Map<String, String> resp = new HashMap<String, String>();
			resp.put("totalCost", parkingService.stopParking(userId, parkingId).toString());
			return Response.ok(resp).build();
		} catch (IllegalArgumentException e) {
			return this.buildErrorResponse(Status.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: ", e);
			return this.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@POST
	@Path("/{id}/parking/availability")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response checkParkingAvailability(final CheckAvilabilityRequest req, @PathParam("id") final Long id) {
		try {
			Map<String, Availability> resp = new HashMap<String, Availability>();
			resp.put("avaliability", segmentService.checkAvailability(id, req.getStreet(), req.getNumber(), false));
			return Response.ok(resp).build();
		} catch (IllegalArgumentException e) {
			return this.buildErrorResponse(Status.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: ", e);
			return this.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@DELETE
	@Path("/{id}/notification/{notificationId}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response stopNotification(@PathParam("userId") final Long userId,
			@PathParam("parkingId") final Long notificationId) {
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}/notification")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response stopAllNotifications(@PathParam("id") final Long id) {
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}/device")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateDevice(final UpdateKeyRequest req, @PathParam("id") final Long id) {
		try {
			userService.updateUserDevice(id, req.getDevice());
			return Response.ok().build();
		} catch (IllegalArgumentException e) {
			return this.buildErrorResponse(Status.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: ", e);
			return this.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
	
	private Response buildErrorResponse(Status status, String message) {
		Map<String, String> resp = new HashMap<>();
		resp.put("message", message);
		return Response.status(status).entity(resp).build();
	}
}
