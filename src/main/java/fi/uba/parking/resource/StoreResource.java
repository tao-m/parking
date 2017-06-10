package fi.uba.parking.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/")
public class StoreResource {

	@POST
    @Path("/store/search")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response searchStore() {
		return Response.ok().build();
	}
	
	@POST
    @Path("/store")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response createStore() {
		return Response.ok().build();
	}
	
	@DELETE
    @Path("/store/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response deleteStore(@PathParam("id") final Long id) {
		return Response.ok().build();
	}
	
}
