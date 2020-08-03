package com.cronos.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "contenu", nullable = false)
	private String contenu;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private Question.TYPE type;

	@ManyToOne
	@JoinColumn(name = "formulaire_id")
	private Formulaire formulaire;

	public Question() {
	}

	public Question(String contenu, Question.TYPE type) {
		super();
		this.contenu = contenu;
		this.type = type;
	}

	public Question(String contenu, Question.TYPE type, Formulaire formulaire) {
		super();
		this.contenu = contenu;
		this.type = type;
		this.formulaire = formulaire;
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

	public Question.TYPE getType() {
		return type;
	}

	public void setType(Question.TYPE type) {
		this.type = type;
	}

	public Formulaire getFormulaire() {
		return formulaire;
	}

	public void setFormulaire(Formulaire formulaire) {
		this.formulaire = formulaire;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", contenu=" + contenu + ", type=" + type + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Question form = (Question) obj;
		if (this.id == form.id)
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	public enum TYPE {
		MULTICHOIX, SIMPLECHOIX, JAUGE
	}

}
