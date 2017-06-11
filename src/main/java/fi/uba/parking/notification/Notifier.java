package fi.uba.parking.notification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Notifier {
	
	@Value("#{appConfig['notification.key']}")
	private String apiKey;
	
	@Value("#{appConfig['notification.url']}")
	private String url;

	public void sendNotification(Notification notification) {
		//TODO (No se a donde hay que mandar las notificaciones)
	}
}
