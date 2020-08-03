package com.cronos.vote.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronos.vote.dto.ReponseDTO;
import com.cronos.vote.model.Reponse;
import com.cronos.vote.repository.QuestionRepository;
import com.cronos.vote.repository.ReponseRepository;
import com.cronos.vote.repository.UserRepository;

@Service
@Transactional
public class ReponseService {

	@Autowired
	private ReponseRepository reponserepository;

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private QuestionRepository questionrepository;

	private static final Logger logger = Logger.getLogger(ReponseService.class);

	public boolean creerReponse(ReponseDTO reponseDto) {
		logger.info("Création de la réponse : " + reponseDto.toString());

		Reponse reponse = new Reponse();
		reponse.setContenu(reponseDto.getContenu());
		reponse.setUser(userrepository.getOne(reponseDto.getUserId()));
		reponse.setQuestion(questionrepository.getOne(reponseDto.getQuestionId()));
		reponserepository.save(reponse);
		return true;
	}

}
