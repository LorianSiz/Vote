package com.cronos.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

	private int id;
	private String contenu;
	private Object type;

	public Question(int id, String contenu, Object type) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.type = type;
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

	@Column(name = "type", nullable = false)
	public Object getType() {
		return type;
	}

	public void setType(Object type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", contenu=" + contenu + ", type=" + type + "]";
	}

}
