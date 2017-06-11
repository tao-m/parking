package fi.uba.parking.notification;

public class Notification {
	
	public static final String MOVEMENT_MESSAGE = "Viajas a gran velocidad, recueda que tienes una sesion de estacionamiento activa";

	private final String content;
	private final String userKey;

	public Notification(String content, String userKey) {
		super();
		this.content = content;
		this.userKey = userKey;
	}

	public String getContent() {
		return content;
	}

	public String getUserKey() {
		return userKey;
	}

}
