package io.firestrap.oauthserver.model;

import java.io.Serializable;

public class ResponseBean implements Serializable {

	
	private static final long serialVersionUID = 46594127005179394L;
	
	
	private String status;
	
	private String message;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
	public ResponseBean(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ApprovalsResponseBean [status=" + status + ", message=" + message + "]";
	}
	
	
}
