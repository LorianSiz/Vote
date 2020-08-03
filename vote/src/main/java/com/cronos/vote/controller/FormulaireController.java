package com.cronos.vote.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.vote.dto.FormulaireDTO;
import com.cronos.vote.service.FormulaireService;

@RestController
@RequestMapping("api/formulaires")
public class FormulaireController {

	@Autowired
	private FormulaireService formulaireservice;

	private static final Logger logger = Logger.getLogger(FormulaireController.class);

	@PostMapping("/creer")
	public long creerFormulaire(@Valid @RequestBody FormulaireDTO formulaireDto) {
		logger.info("Demande de création d'un formulaire");
		return formulaireservice.creerFormulaire(formulaireDto);
	}

}