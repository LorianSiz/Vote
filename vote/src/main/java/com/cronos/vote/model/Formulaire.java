package com.cronos.vote.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formulaires")
public class Formulaire {

	private int id;
	private String nom;
	private List<Question> questions;

	public Formulaire(int id, String nom, List<Question> questions) {
		super();
		this.id = id;
		this.nom = nom;
		this.questions = questions;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nom", nullable = false)
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "questions", nullable = false)
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Formulaire [id=" + id + ", nom=" + nom + ", questions=" + questions + "]";
	}

}
