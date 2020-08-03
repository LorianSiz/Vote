package com.cronos.vote.dto;

import java.util.List;

import com.cronos.vote.model.Question;
import com.cronos.vote.model.Question.TYPE;

public class QuestionDTO {

	private long id;

	private String contenu;

	private Question.TYPE type;

	private List<String> reponses;

	private long formulaireId;

	public QuestionDTO() {
	}

	public QuestionDTO(long id, String contenu, TYPE type, List<String> reponses) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.type = type;
		this.reponses = reponses;
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

	public List<String> getReponses() {
		return reponses;
	}

	public void setReponses(List<String> reponses) {
		this.reponses = reponses;
	}

	public long getFormulaireId() {
		return formulaireId;
	}

	public void setFormulaireId(long formulaireId) {
		this.formulaireId = formulaireId;
	}

	@Override
	public String toString() {
		return "QuestionDTO [id=" + id + ", contenu=" + contenu + ", type=" + type + ", reponses=" + reponses
				+ ", formulaireId=" + formulaireId + "]";
	}

}
