package com.example.application.api.model;

import javax.validation.constraints.NotNull;

public class LogStatus {
	
	private String message;
	public LogStatus(String message, String messageDev, int code) {
		super();
		this.message = message;
		this.messageDev = messageDev;
		this.code = code;
	}
	private String messageDev;
	private int code;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageDev() {
		return messageDev;
	}
	public void setMessageDev(String messageDev) {
		this.messageDev = messageDev;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	

}
