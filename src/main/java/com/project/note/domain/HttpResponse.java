package com.project.note.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/***
 * 
 * {
    "timestamp": "2022-01-11T00:14:38.525+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "No message available",
    "path": "/12321312312345"
}
 * @author dev
 *
 */

public class HttpResponse {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Timestamp timestamp;
	private int status;
	private String error;
	private String message;
	    
	public HttpResponse() {
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public HttpResponse(int status, String error, String message) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HttpResponse [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message="
				+ message + "]";
	}

}