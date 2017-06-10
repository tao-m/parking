package fi.uba.parking.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/test")
public class AlertResource {

    @GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getUserAlerts(@PathParam("id") final String id) {

    	Map<String, String> resp = new HashMap<String, String>();
    	resp.put("Mario", "sossich");
        return Response.ok(resp).build();
    }

}
