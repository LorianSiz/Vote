package com.cronos.vote.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronos.vote.dto.FormulaireDTO;
import com.cronos.vote.model.Formulaire;
import com.cronos.vote.repository.FormulaireRepository;

@Service
@Transactional
public class FormulaireService {

	@Autowired
	private FormulaireRepository formulairerepository;

	private static final Logger logger = Logger.getLogger(FormulaireService.class);

	public long creerFormulaire(FormulaireDTO formulaireDto) {
		logger.info("Création d'un formulaire: " + formulaireDto.toString());
		Formulaire formulaire = new Formulaire();
		formulaire.setNom(formulaireDto.getNom());
		return formulairerepository.save(formulaire).getId();
	}
}