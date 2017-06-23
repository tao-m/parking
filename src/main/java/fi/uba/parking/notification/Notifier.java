package fi.uba.parking.notification;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Notifier {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(Notifier.class);

	@Value("#{appConfig['notification.key']}")
	private String apiKey;

	@Value("#{appConfig['notification.url']}")
	private String url;

	public void sendNotification(Notification notification) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-type", "application/json");
			post.setHeader("Authorization", apiKey);

			JSONObject message = new JSONObject();
			message.put("to", notification.getUserKey());

			JSONObject jsonNotification = new JSONObject();
			jsonNotification.put("body", notification.getContent());
			jsonNotification.put("title", "Estacionamiento");
			jsonNotification.put("click_action", "OPEN_PARKING_ACTIVITY");
			message.put("notification", jsonNotification);

			post.setEntity(new StringEntity(message.toString(), "UTF-8"));
			HttpResponse response = client.execute(post);
			System.out.println(response);
		} catch (JSONException | IOException e) {
			LOGGER.error("Error sending Notification", e);
		}
	}
}
