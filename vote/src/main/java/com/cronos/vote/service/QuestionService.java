package com.cronos.vote.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronos.vote.dto.QuestionDTO;
import com.cronos.vote.model.Question;
import com.cronos.vote.model.Question.TYPE;
import com.cronos.vote.model.Reponse;
import com.cronos.vote.model.ReponsesQuestion;
import com.cronos.vote.repository.FormulaireRepository;
import com.cronos.vote.repository.QuestionRepository;
import com.cronos.vote.repository.ReponseRepository;
import com.cronos.vote.repository.ReponsesQuestionRepository;
import com.cronos.vote.repository.VoteRepository;

@Service
@Transactional
public class QuestionService {

	@Autowired
	private VoteRepository voterepository;

	@Autowired
	private QuestionRepository questionrepository;

	@Autowired
	private FormulaireRepository formulairerepository;

	@Autowired
	private ReponseRepository reponserepository;

	@Autowired
	private ReponsesQuestionRepository reponsesquestionrepository;

	private static final Logger logger = Logger.getLogger(QuestionService.class);

	public ResponseEntity<List<QuestionDTO>> getByQuestionProfilById(Long voteId) {
		logger.info("Récupération des questions du formulaire profil du vote id: " + voteId + ".");

		List<Question> profilQuests = new ArrayList<Question>();
		if (voterepository.findById(voteId).isPresent()) {
			long profilId = voterepository.getOne(voteId).getProfilForm().getId();
			logger.info("Profil Form id: " + profilId);
			profilQuests = questionrepository.findByFormulaire_id(profilId);

			List<QuestionDTO> questResult = new ArrayList<QuestionDTO>();
			profilQuests.forEach(quest -> {
				List<ReponsesQuestion> repQuestList = new ArrayList<ReponsesQuestion>();
				List<String> repList = new ArrayList<String>();
				repQuestList = reponsesquestionrepository.findReponsesQuestionByQuestion_id(quest.getId());
				for (ReponsesQuestion repQuest : repQuestList) {
					repList.add(repQuest.getReponse());
				}
				questResult.add(new QuestionDTO(quest.getId(), quest.getContenu(), quest.getType(), repList));
			});

			return ResponseEntity.ok().body(questResult);
		} else {
			logger.error("Impossible de récupérer le formulaire profil du vote: " + voteId);
			List<QuestionDTO> listeVide = new ArrayList<QuestionDTO>();
			return ResponseEntity.ok().body(listeVide);
		}
	}

	public ResponseEntity<List<QuestionDTO>> getByQuestionVoteById(Long voteId) {
		logger.info("Récupération des questions du formulaire vote du vote id: " + voteId + ".");

		List<Question> voteQuests = new ArrayList<Question>();
		long voteFormId = voterepository.getOne(voteId).getVoteForm().getId();
		logger.info("Vote Form id: " + voteFormId);
		voteQuests = questionrepository.findByFormulaire_id(voteFormId);
		if (voterepository.findById(voteId).isPresent()) {
			List<QuestionDTO> questResult = new ArrayList<QuestionDTO>();
			voteQuests.forEach(quest -> {
				List<String> repList = new ArrayList<String>();
				List<ReponsesQuestion> repQuestList = new ArrayList<ReponsesQuestion>();
				repQuestList = reponsesquestionrepository.findReponsesQuestionByQuestion_id(quest.getId());
				for (ReponsesQuestion repQuest : repQuestList) {
					repList.add(repQuest.getReponse());
				}
				questResult.add(new QuestionDTO(quest.getId(), quest.getContenu(), quest.getType(), repList));
			});

			return ResponseEntity.ok().body(questResult);
		} else {
			logger.error("Impossible de récupérer le formulaire vote du vote: " + voteId);
			List<QuestionDTO> listeVide = new ArrayList<QuestionDTO>();
			return ResponseEntity.ok().body(listeVide);
		}
	}

	public boolean creerQuestion(QuestionDTO questionDTO) {
		logger.info("Création d'une question: " + questionDTO.toString());

		Question question = new Question();
		question.setContenu(questionDTO.getContenu());
		question.setType(questionDTO.getType());
		question.setFormulaire(formulairerepository.getOne(questionDTO.getFormulaireId()));
		long idQuest = questionrepository.save(question).getId();
		if (idQuest != 0) {
			ReponsesQuestion repQuest;
			for (String element : questionDTO.getReponses()) {
				repQuest = new ReponsesQuestion();
				repQuest.setQuestion(questionrepository.getOne(idQuest));
				repQuest.setReponse(element.trim());
				reponsesquestionrepository.save(repQuest);
			}
			return true;
		} else {
			logger.info("Erreur dans la création de la question");
			return false;
		}
	}

	public String getResultat(Long id) {
		logger.info("Récupération des résultats de la question: " + id);
		String resultat = "";
		Question quest = questionrepository.getOne(id);
		List<Reponse> reponses = reponserepository.findReponseByQuestion_id(id);

		if (quest.getType() != TYPE.JAUGE) {
			int max = 0, compt = 0;
			List<ReponsesQuestion> repsquests = reponsesquestionrepository.findReponsesQuestionByQuestion_id(id);
			for (ReponsesQuestion repquest : repsquests) {
				String rep_contenu = repquest.getReponse();
				for (Reponse rep : reponses) {
					if (rep.getContenu().equals(rep_contenu))
						compt++;
				}
				if (compt == max)
					resultat += rep_contenu + ", ";
				else if (compt > max)
					resultat = rep_contenu;
			}
		} else {
			int moyenne = 0;
			for (Reponse reponse : reponses) {
				moyenne += Integer.parseInt(reponse.getContenu());
			}
			resultat = String.valueOf(moyenne / reponses.size());
		}

		return resultat;
	}
}