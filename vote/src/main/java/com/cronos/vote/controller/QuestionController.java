package com.cronos.vote.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.vote.dto.QuestionDTO;
import com.cronos.vote.service.QuestionService;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionservice;

	private static final Logger logger = Logger.getLogger(VoteController.class);

	@GetMapping("/{id}/profil")
	public ResponseEntity<List<QuestionDTO>> getByQuestionProfilById(@PathVariable(value = "id") Long id) {
		logger.info("Demande de récupération des questions d'un formulaire profil");
		return questionservice.getByQuestionProfilById(id);
	}

	@GetMapping("/{id}/vote")
	public ResponseEntity<List<QuestionDTO>> getByQuestionVoteById(@PathVariable(value = "id") Long id) {
		logger.info("Demande de récupération des questions d'un formulaire vote");
		return questionservice.getByQuestionVoteById(id);
	}

	@PostMapping("/creer")
	public boolean creerQuestion(@Valid @RequestBody QuestionDTO questionDTO) {
		logger.info("Demande de création d'une question");
		return questionservice.creerQuestion(questionDTO);
	}

	@GetMapping("/{id}/resultat")
	public String getResultat(@PathVariable(value = "id") Long id) {
		logger.info("Demande de récupération des résultats d'une question");
		return "\"" + questionservice.getResultat(id) + "\"";
	}

}