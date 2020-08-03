package com.cronos.vote.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronos.vote.dto.VoteDTO;
import com.cronos.vote.model.Question;
import com.cronos.vote.model.Question.TYPE;
import com.cronos.vote.model.User;
import com.cronos.vote.model.Vote;
import com.cronos.vote.repository.FormulaireRepository;
import com.cronos.vote.repository.QuestionRepository;
import com.cronos.vote.repository.ReponseRepository;
import com.cronos.vote.repository.UserRepository;
import com.cronos.vote.repository.VoteRepository;
import com.cronos.vote.repository.ActivitiWorkflowRepository.ActivitiRestClient;

@Service
@Transactional
public class VoteService {

	@Autowired
	private VoteRepository voterepository;

	@Autowired
	private FormulaireRepository formulairerepository;

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private QuestionRepository questionrepository;

	@Autowired
	private ReponseRepository reponserepository;

	@Autowired
	private VoteVotantService votevotantservice;

	@Autowired
	private ActivitiRestClient activitirestclient;

	private static final Logger logger = Logger.getLogger(VoteService.class);

	public List<Vote> getAllVotes(long userId) {
		logger.info("Récupération de la liste des votes pour le user: " + userId);
		return voterepository.findVoteByUserId(userId);
	}

	public ResponseEntity<Vote> getVoteById(Long voteId) {
		logger.info("Récupération du vote id: " + voteId + ".");
		if (voterepository.findById(voteId).isPresent()) {
			Vote vote = voterepository.getOne(voteId);
			return ResponseEntity.ok().body(vote);
		} else {
			logger.error("Impossible de récupérer le vote: " + voteId);
			Vote vote = new Vote();
			return ResponseEntity.ok().body(vote);
		}
	}

	public long creerVote(VoteDTO voteDto) {
		logger.info("Création d'un vote: " + voteDto.toString());

		Vote vote = new Vote();
		vote.setDisponible(true);
		vote.setDescription(voteDto.getDescription());
		vote.setDateFin(voteDto.getDateFin());
		vote.setProfilForm(formulairerepository.getOne(voteDto.getProfilFormId()));
		vote.setVoteForm(formulairerepository.getOne(voteDto.getVoteFormId()));
		vote.setUser(userrepository.getOne(voteDto.getUserId()));
		vote.setProcessId(activitirestclient.startProcess("voteProcess"));
		return voterepository.save(vote).getId();
	}

	public boolean verifVoteTerminer(long voteId) {
		logger.info("Vérification vote terminé: " + voteId);
		boolean etatVote = true;
		int i = 0;
		Vote vote = voterepository.getOne(voteId);
		List<User> users = votevotantservice.findUserByVoteId(voteId);

		while ((etatVote != false) && (i < users.size())) {
			long userId = users.get(i).getId();
			etatVote = getVerifVotant(voteId, userId);
			i++;
		}

		if (etatVote) {
			terminerVote(vote, true);
		}

		return etatVote;
	}

	public void terminerVote(Vote vote, boolean runTaskTest) {
		logger.info("Fin du vote: " + vote.getId());
		vote.setDisponible(false);
		voterepository.save(vote);
		if (runTaskTest)
			activitirestclient.runTask(activitirestclient.getTask(vote.getProcessId()).getIdActiviti());
	}

	public boolean getVerifVotant(long voteId, long votantId) {
		logger.info("Vérification du vote: " + voteId + " du votant: " + votantId);
		boolean etatVote = true;
		Vote vote = voterepository.getOne(voteId);
		List<Question> questionsProfil = questionrepository.findByFormulaire_id(vote.getProfilForm().getId());
		List<Question> questionsVote = questionrepository.findByFormulaire_id(vote.getVoteForm().getId());

		for (int y = 0; y < questionsProfil.size(); y++) {
			if (reponserepository.findReponseByQuestion_idAndUser_id(questionsProfil.get(y).getId(), votantId).isEmpty()
					&& (questionsProfil.get(y).getType() != TYPE.MULTICHOIX)) {
				etatVote = false;
			}
		}
		for (int j = 0; j < questionsVote.size(); j++) {
			if (reponserepository.findReponseByQuestion_idAndUser_id(questionsVote.get(j).getId(), votantId).isEmpty()
					&& (questionsVote.get(j).getType() != TYPE.MULTICHOIX)) {
				etatVote = false;
			}
		}
		return etatVote;
	}

	public ResponseEntity<Boolean> getDisponible(long voteId) {
		logger.info("Récupération de la disponibilité du vote: " + voteId);
		if (voterepository.findById(voteId).isPresent()) {
			Vote vote = voterepository.getOne(voteId);
			return ResponseEntity.ok().body(vote.isDisponible());
		} else {
			logger.error("Impossible de récupérer la disponibilité du vote: " + voteId);
			return ResponseEntity.ok().body(false);
		}
	}

}
