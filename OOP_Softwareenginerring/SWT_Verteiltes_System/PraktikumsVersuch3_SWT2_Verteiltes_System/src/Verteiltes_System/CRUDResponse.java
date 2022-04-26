package Verteiltes_System;
import java.io.Serializable;

/**
 * 
 * @author Christian Weidauer, Hochschule Bochum
 *
 */
public class CRUDResponse implements Serializable {
	
	static public enum Status {
		OK, FAILED, LOCKED, UNSUPPORTED;
	}
	
	private static final long serialVersionUID = 632963367219922152L;
	private Status status = Status.OK;
	private Serializable object = null;
	
	public static final CRUDResponse FAILED = new CRUDResponse(Status.FAILED);
	public static final CRUDResponse UNSUPPORTED = new CRUDResponse(Status.UNSUPPORTED);
	public static final CRUDResponse LOCKED = new CRUDResponse(Status.LOCKED);

	public CRUDResponse() {
		// used for deserialization
	}

	public CRUDResponse(Status status) {
		this(status, null);
	}

	public CRUDResponse(Status status, Serializable object) {
		super();
		this.status = status;
		this.object = object;
	}

	public static CRUDResponse ok(Serializable object) {
		return new CRUDResponse(Status.OK, object);
	}
	
	public Status getStatus() {
		return status;
	}

	public Serializable getObject() {
		return object;
	}

	public boolean isOK() {
		return status.equals(Status.OK);
	}
	
	@Override
	public String toString() {
		return "Response [status=" + status + ", object=" + object + "]";
	}


}
