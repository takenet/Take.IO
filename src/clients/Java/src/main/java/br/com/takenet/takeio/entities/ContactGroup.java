package br.com.takenet.takeio.entities;

import java.util.UUID;

public class ContactGroup {

	private UUID id;
	private String name;
	private UUID owner;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

}
