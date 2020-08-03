package com.cronos.vote.repository.ActivitiWorkflowRepository.model;

import java.util.List;

public class ActivitiParam {

	private String nom;
	private List<String> value;

	public ActivitiParam(String nom, List<String> value) {
		super();
		this.nom = nom;
		this.value = value;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ActivitiParam [nom=" + nom + ", value=" + value + "]";
	}

}
