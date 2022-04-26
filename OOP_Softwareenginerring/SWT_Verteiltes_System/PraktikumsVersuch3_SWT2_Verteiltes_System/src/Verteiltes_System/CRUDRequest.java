package Verteiltes_System;

import java.io.Serializable;

/**
 * 
 * @author Christian Weidauer, Hochschule Bochum
 *
 */
public class CRUDRequest implements Serializable {
	
	static public enum CRUD_COMMAND {
		CREATE, READ, UPDATE, DELETE, READ_AND_LOCK_EXCLUSIVELY;
	}
	
	
	private static final long serialVersionUID = -7814741003799590066L;
	private CRUD_COMMAND command;
	private Serializable object;
	
	public CRUDRequest(CRUD_COMMAND command, Serializable object) {
		super();
		this.command = command;
		this.object = object;
	}

	public CRUDRequest() {
	}

	public CRUD_COMMAND getCommand() {
		return command;
	}

	public void setCommand(CRUD_COMMAND command) {
		this.command = command;
	}


	public Serializable getObject() {
		return object;
	}

	public void setObject(Serializable object) {
		this.object = object;
	}

	
	@Override
	public String toString() {
		return "Request [" + "command=" + command + ", object=" + object + "]";
	}

	
}
