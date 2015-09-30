package br.com.takenet.takeio.entities;

public class Item <T> {
	private T entry;
	
	public Item() {
	}

	public T getEntry() {
		return entry;
	}

	public void setEntry(T entry) {
		this.entry = entry;
	}
}
