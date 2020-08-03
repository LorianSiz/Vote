package com.cronos.vote.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronos.vote.controller.NotifController;
import com.cronos.vote.model.User;
import com.cronos.vote.model.Vote;
import com.cronos.vote.repository.VoteRepository;

@Service
@Transactional
public class NotifService {

	@Autowired
	private EmailService emailservice;

	@Autowired
	private VoteRepository voterepository;

	@Autowired
	private VoteService voteservice;

	@Autowired
	private VoteVotantService votevotantservice;

	private static final Logger logger = Logger.getLogger(NotifController.class);

	public void envoieNotifDebut(String id) {
		id = id.replace("=", "");
		logger.info("Vote processId: " + id);
		long voteId = voterepository.getVoteByProcessId(id).getId();
		logger.info("Demande de notification de lancement pour le vote: " + voteId);

		List<User> users = votevotantservice.findUserByVoteId(voteId);
		users.forEach(user -> {
			String pseudo = user.getPseudo();
			emailservice.sendSimpleMessage(pseudo, "Nouveau vote",
					"Vous avez été inscrit à un vote.\nVous pouvez dès maintenant y participer depuis ce lien: http://localhost:4200/details/"
							+ voteId + "/" + pseudo);
		});
	}

	public void envoieNotifRappel(String id) {
		id = id.replace("=", "");
		logger.info("Vote processId: " + id);
		long voteId = voterepository.getVoteByProcessId(id).getId();
		logger.info("Demande de notification de rappel pour le vote: " + voteId);

		List<User> users = votevotantservice.findUserByVoteId(voteId);
		users.forEach(user -> {
			String pseudo = user.getPseudo();
			emailservice.sendSimpleMessage(pseudo, "Rappel vote",
					"Vous n'avez toujours pas participé au vote.\nVous pouvez y participer depuis ce lien: http://localhost:4200/details/"
							+ voteId + "/" + pseudo);
		});
	}

	public void envoieNotifResultat(String id) {
		id = id.replace("=", "");
		logger.info("Vote processId: " + id);
		Vote vote = voterepository.getVoteByProcessId(id);
		long voteId = vote.getId();
		logger.info("Demande de notification de résultat pour le vote: " + voteId);

		List<User> users = votevotantservice.findUserByVoteId(voteId);
		users.forEach(user -> {
			String pseudo = user.getPseudo();
			emailservice.sendSimpleMessage(pseudo, "Vote clos",
					"Le vote est maintenant clos.\nLes résultats du vote sont disponibles ici: http://localhost:4200/resultat/2/false");
		});

		voteservice.terminerVote(vote, false);
	}

}
