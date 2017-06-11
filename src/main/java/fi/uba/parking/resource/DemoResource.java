package fi.uba.parking.resource;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fi.uba.parking.domain.Credit;
import fi.uba.parking.domain.Device;
import fi.uba.parking.domain.User;
import fi.uba.parking.notification.Notification;
import fi.uba.parking.notification.Notifier;
import fi.uba.parking.service.IUserService;

@Component
@Path("/init")
public class DemoResource {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private Notifier notifier;
	
	@GET
    @Path("/")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response init() {
		
		Device d1 = new Device("Asdiuiyasuidyiaysd7", true);
		Credit c1 = new Credit(BigInteger.valueOf(1000000000000L), new Date(new Date().getTime() + 10000000000L));
		User u1 = new User("Mario S", "mario@mario.com", "12000999", d1, c1, true, new Date(), null);
		
		Device d2 = new Device("AKsdasdae", true);
		Credit c2 = new Credit(BigInteger.valueOf(1000000000000L), new Date(new Date().getTime() + 10000000000L));
		User u2 = new User("Marcos r", "marcos@marcos.com", "11000999", d2, c2, true, new Date(), null);

    	Map<Long, User> resp = new HashMap<Long, User>();
    	resp.put(userService.saveUser(u1), u1);
    	resp.put(userService.saveUser(u2), u2);
    	
        return Response.ok(resp).build();
    }
	
	@POST
    @Path("/notification")
    @Produces({ MediaType.APPLICATION_JSON })
	public Response notification(Map<String, String> map) {
		
		this.notifier.sendNotification(new Notification("HOLA!", map.get("user")));
    	
        return Response.ok().build();
    }

}
