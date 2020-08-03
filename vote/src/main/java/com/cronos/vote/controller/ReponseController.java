package com.cronos.vote.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.vote.dto.ReponseDTO;
import com.cronos.vote.service.ReponseService;

@RestController
@RequestMapping("api/reponses")
public class ReponseController {

	@Autowired
	private ReponseService reponseservice;

	private static final Logger logger = Logger.getLogger(ReponseController.class);

	@PostMapping("/creer")
	public boolean creerReponse(@Valid @RequestBody ReponseDTO reponseDto) {
		logger.info("Demande de création d'une réponse");
		return reponseservice.creerReponse(reponseDto);
	}

}
