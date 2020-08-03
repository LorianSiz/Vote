package com.cronos.vote.dto;

public class VoteDTO {

	private long id;

	private String dateFin;

	private String description;

	private long profilFormId;

	private long voteFormId;

	private long userId;

	public VoteDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public long getProfilFormId() {
		return profilFormId;
	}

	public void setProfilFormId(long profilFormId) {
		this.profilFormId = profilFormId;
	}

	public long getVoteFormId() {
		return voteFormId;
	}

	public void setVoteFormId(long voteFormId) {
		this.voteFormId = voteFormId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
