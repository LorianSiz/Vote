package com.cronos.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reponses")
public class Reponse {

	private int id;
	private String contenu;
	private Votant votant;

	public Reponse(int id, String contenu, Votant votant) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.votant = votant;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "contenu", nullable = false)
	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	@Column(name = "votant", nullable = false)
	public Votant getVotant() {
		return votant;
	}

	public void setVotant(Votant votant) {
		this.votant = votant;
	}

	@Override
	public String toString() {
		return "Reponse [id=" + id + ", contenu=" + contenu + ", votant=" + votant + "]";
	}

}
