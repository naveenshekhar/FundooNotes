package com.bridgelabz.fundoo.responces;

public class Responce {

	private String message;
	private int statuscode;
	private Object object;

	public Responce(String message, int statuscode, Object object) {
		this.message = message;
		this.statuscode = statuscode;
		this.object = object;
	}

	public Responce(String message, int statuscode) {
		this.message = message;
		this.statuscode = statuscode;
	}
	

	public Responce() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
