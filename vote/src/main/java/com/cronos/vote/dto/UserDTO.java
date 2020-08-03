package com.cronos.vote.dto;

public class UserDTO {

	private long id;

	private String pseudo;

	private String password;

	private String role;

	private long voteId;

	public UserDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getVoteId() {
		return voteId;
	}

	public void setVoteId(long voteId) {
		this.voteId = voteId;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", pseudo=" + pseudo + ", password=" + password + ", role=" + role + ", voteId="
				+ voteId + "]";
	}

}
