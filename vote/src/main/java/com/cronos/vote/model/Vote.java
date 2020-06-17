package com.cronos.vote.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "votes")
public class Vote {

	private int id;
	private String dateFin;
	private boolean disponible;
	private List<Votant> votants;
	private Formulaire profil;
	private Formulaire vote;

	public Vote(int id, String dateFin, boolean disponible, List<Votant> votants, Formulaire profil, Formulaire vote) {
		super();
		this.id = id;
		this.dateFin = dateFin;
		this.disponible = disponible;
		this.votants = votants;
		this.profil = profil;
		this.vote = vote;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "date_fin", nullable = false)
	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	@Column(name = "disponible", nullable = false)
	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Column(name = "votants", nullable = false)
	public List<Votant> getVotants() {
		return votants;
	}

	public void setVotants(List<Votant> votants) {
		this.votants = votants;
	}

	@Column(name = "profilForm", nullable = false)
	public Formulaire getProfil() {
		return profil;
	}

	public void setProfil(Formulaire profil) {
		this.profil = profil;
	}

	@Column(name = "voteForm", nullable = false)
	public Formulaire getVote() {
		return vote;
	}

	public void setVote(Formulaire vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", dateFin=" + dateFin + ", disponible=" + disponible + ", votants=" + votants
				+ ", profil=" + profil + ", vote=" + vote + "]";
	}

}
