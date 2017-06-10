package fi.uba.parking.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import fi.uba.parking.request.CheckAvilabilityRequest;
import fi.uba.parking.request.StartParkingRequest;
import fi.uba.parking.request.UpdateDeviceRequest;
import fi.uba.parking.request.UpdatePositionRequest;

@Component
@Path("/user")
public class UserResource {

	@PUT
    @Path("/{id}/position")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response updatePosition(final UpdatePositionRequest req, @PathParam("id") final Long id) {
		return Response.ok(req).build();
	}
	
	@POST
    @Path("/{id}/parking")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response startParking(final StartParkingRequest req, @PathParam("id") final Long id) {
		return Response.ok(req).build();
	}
	
	@DELETE
    @Path("/{userId}/parking/{parkingId}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response stopParking(@PathParam("userId") final Long userId, @PathParam("parkingId") final Long parkingId) {
		return Response.ok().build();
	}
	
	@POST
    @Path("/{id}/parking/availability")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response checkParkingAvailability(final CheckAvilabilityRequest req, @PathParam("id") final Long id) {
		return Response.ok(req).build();
	}
	
	@DELETE
    @Path("/{id}/notification/{notificationId}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response stopNotification(@PathParam("userId") final Long userId, @PathParam("parkingId") final Long notificationId) {
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
	public Response updateDevice(final UpdateDeviceRequest req, @PathParam("id") final Long id) {
		return Response.ok(req).build();
	}
}
