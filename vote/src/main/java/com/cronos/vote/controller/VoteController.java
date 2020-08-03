package com.cronos.vote.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.vote.dto.VoteDTO;
import com.cronos.vote.model.Vote;
import com.cronos.vote.service.VoteService;

@RestController
@RequestMapping("api/votes")
public class VoteController {

	@Autowired
	private VoteService voteservice;

	private static final Logger logger = Logger.getLogger(VoteController.class);

	@PostMapping("")
	public List<Vote> getAllVotes(@Valid @RequestBody long userId) {
		logger.info("demande de récupération de la liste des votes.");
		return voteservice.getAllVotes(userId);
	}

	@GetMapping("/{voteId}")
	public ResponseEntity<Vote> getVoteById(@PathVariable(value = "voteId") Long voteId) {
		logger.info("Demande de récupération du vote id");
		return voteservice.getVoteById(voteId);
	}

	@PostMapping("/creer")
	public long creerVote(@Valid @RequestBody VoteDTO voteDto) {
		logger.info("Demande de création d'un vote");
		return voteservice.creerVote(voteDto);
	}

	@GetMapping("/verifvotant/{idVote}/{idVotant}")
	public boolean getVerifVotant(@PathVariable(value = "idVote") Long voteId,
			@PathVariable(value = "idVotant") Long VotantId) {
		logger.info("Demande de vérification du vote d'un votant");
		return voteservice.getVerifVotant(voteId, VotantId);
	}

	@PostMapping("/verifterminer")
	public boolean verifVoteTerminer(@Valid @RequestBody long voteId) {
		logger.info("Demande de création d'un vote");
		return voteservice.verifVoteTerminer(voteId);
	}

	@GetMapping("/disponible/{voteId}")
	public ResponseEntity<Boolean> getDisponible(@PathVariable(value = "voteId") Long voteId) {
		logger.info("Demande de récupération de la disponibilité d'un vote");
		return voteservice.getDisponible(voteId);
	}

}
