package fi.uba.parking.geo;

public class GeoClientResult<T> {

	private final RequestResult status;
	
	private final T result;

	public GeoClientResult(RequestResult status, T result) {
		super();
		this.status = status;
		this.result = result;
	}

	public RequestResult getStatus() {
		return status;
	}

	public T getResult() {
		return result;
	}


}
