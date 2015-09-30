package br.com.takenet.takeio.client;

import java.net.URI;
import javax.ws.rs.core.Response.StatusType;

public class Response<T> {
	private StatusType status;
	private String description;
	private T content;

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
	
	public static String getUUIDComponent(URI uri) {
		String uriStr = uri.toString();
		String uuid = uriStr.substring(uriStr.lastIndexOf("/") + 1);
		
		return uuid;
	}

}
