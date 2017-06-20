package fi.uba.parking.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import fi.uba.parking.geo.Coordinate;
import fi.uba.parking.request.CreateStoreRequest;
import fi.uba.parking.request.StoreSearchRequest;
import fi.uba.parking.service.IStoreService;

@Component
@Path("/")
public class StoreResource {
	private final static Logger LOGGER = LoggerFactory.getLogger(StoreResource.class);

	@Autowired
	private IStoreService storeService;

	@POST
	@Path("/store/search")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response searchStore(StoreSearchRequest request) {
		try {
			return Response.ok(
					storeService.search(new Coordinate(request.getLat(), request.getLng()), request.getSearchRadio()))
					.build();
		} catch (IllegalArgumentException e) {
			return this.buildErrorResponse(Status.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: ", e);
			return this.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@POST
	@Path("/store")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createStore(CreateStoreRequest request) {
		try {
			return Response
					.ok(storeService.createStore(request.getName(), new Coordinate(request.getLat(), request.getLng())))
					.build();
		} catch (IllegalArgumentException e) {
			return this.buildErrorResponse(Status.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: ", e);
			return this.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@GET
	@Path("/store")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAll() {
		try {
			return Response.ok(storeService.searchAll()).build();
		} catch (IllegalArgumentException e) {
			return this.buildErrorResponse(Status.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			LOGGER.error("Unexpected Error: ", e);
			return this.buildErrorResponse(Status.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@DELETE
	@Path("/store/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response deleteStore(@PathParam("id") final Long id) {
		try {
			storeService.delete(id);
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
