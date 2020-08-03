package com.cronos.vote.dto;

public class ReponseDTO {

	private long id;

	private String contenu;

	private long userId;

	private long questionId;

	public ReponseDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "ReponseDTO [id=" + id + ", contenu=" + contenu + ", userId=" + userId + ", questionId=" + questionId
				+ "]";
	}

}
