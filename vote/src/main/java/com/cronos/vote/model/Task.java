package com.cronos.vote.model;

public class Task {

	private long id;
	private String idActiviti;
	private String name;
	private String description;

	public Task() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdActiviti() {
		return idActiviti;
	}

	public void setIdActiviti(String idActiviti) {
		this.idActiviti = idActiviti;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + "]";
	}

}
