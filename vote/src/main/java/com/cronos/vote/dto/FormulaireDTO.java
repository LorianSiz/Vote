package com.cronos.vote.dto;

public class FormulaireDTO {

	private long id;

	private String nom;

	public FormulaireDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "FormulaireDTO [id=" + id + ", nom=" + nom + "]";
	}

}
