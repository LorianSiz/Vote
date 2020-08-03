package com.cronos.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "votes")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "date_fin", nullable = false)
	private String dateFin;

	@Column(name = "disponible", nullable = false)
	private boolean disponible;

	@Column(name = "description", nullable = false)
	private String description;

	@Column(name = "process_id", nullable = true)
	private String processId;

	@OneToOne
	@JoinColumn(name = "profil_form_id")
	private Formulaire profilForm;

	@OneToOne
	@JoinColumn(name = "vote_form_id")
	private Formulaire voteForm;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Vote() {
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

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Formulaire getProfilForm() {
		return profilForm;
	}

	public void setProfilForm(Formulaire profilForm) {
		this.profilForm = profilForm;
	}

	public Formulaire getVoteForm() {
		return voteForm;
	}

	public void setVoteForm(Formulaire voteForm) {
		this.voteForm = voteForm;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", dateFin=" + dateFin + ", disponible=" + disponible + ", description=" + description
				+ ", processId=" + processId + ", profilForm=" + profilForm + ", voteForm=" + voteForm + ", user="
				+ user + "]";
	}

}
