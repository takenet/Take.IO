package br.com.takenet.takeio.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

//Never send empty properties in JSON
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipientsResource {
	private Long attempts;
	private String carrier;
	private String status;
	private String updated;
	private String value;
	
	public RecipientsResource() {
	}
	
	public RecipientsResource(String value) {
		this.value = value;
	}

	public Long getAttempts() {
		return attempts;
	}

	public void setAttempts(Long attempts) {
		this.attempts = attempts;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
