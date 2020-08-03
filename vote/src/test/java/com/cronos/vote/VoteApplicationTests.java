package com.cronos.vote;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cronos.vote.dto.FormulaireDTO;
import com.cronos.vote.dto.QuestionDTO;
import com.cronos.vote.dto.ReponseDTO;
import com.cronos.vote.dto.UserDTO;
import com.cronos.vote.model.Question.TYPE;
import com.cronos.vote.model.User;
import com.cronos.vote.model.Vote;
import com.cronos.vote.repository.FormulaireRepository;
import com.cronos.vote.repository.QuestionRepository;
import com.cronos.vote.repository.ReponsesQuestionRepository;
import com.cronos.vote.service.FormulaireService;
import com.cronos.vote.service.NotifService;
import com.cronos.vote.service.QuestionService;
import com.cronos.vote.service.ReponseService;
import com.cronos.vote.service.UserService;
import com.cronos.vote.service.VoteService;
import com.cronos.vote.service.VoteVotantService;

@SpringBootTest
@WebAppConfiguration
@Transactional
class VoteApplicationTests {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	private static final Logger logger = Logger.getLogger(VoteApplicationTests.class);

	@Test
	void contextLoads() {
	}

	@Autowired
	FormulaireService formulaireservice;

	@Autowired
	FormulaireRepository formulairepository;

	@Test
	public void testFormulaireService() throws URISyntaxException {
		logger.info("Test du FormulaireService");

		FormulaireDTO formulaireDto = new FormulaireDTO();
		formulaireDto.setNom("Test");
		long formulaireId = formulaireservice.creerFormulaire(formulaireDto);
		Assert.assertNotNull(formulaireId);

	}

	@Autowired
	NotifService notifservice;

	@Test
	public void testNotifService() throws URISyntaxException {
		logger.info("Test du NotifService");

		notifservice.envoieNotifDebut("1");
		notifservice.envoieNotifRappel("1");
		notifservice.envoieNotifResultat("1");

	}

	@Autowired
	QuestionService questionservice;

	@Autowired
	QuestionRepository questionpository;

	@Autowired
	ReponsesQuestionRepository reponsesquestionpository;

	@Test
	public void testQuestionService() throws URISyntaxException {
		logger.info("Test du QuestionService");

		QuestionDTO questionDto = new QuestionDTO();
		questionDto.setContenu("Test");
		questionDto.setFormulaireId(1);
		List<String> reponses = new ArrayList<String>();
		reponses.add("RepTest");
		questionDto.setReponses(reponses);
		questionDto.setType(TYPE.JAUGE);
		boolean questTest = questionservice.creerQuestion(questionDto);
		Assert.assertTrue(questTest);

		ResponseEntity<List<QuestionDTO>> questionsProfil = questionservice.getByQuestionProfilById((long) 1);
		Assert.assertFalse(questionsProfil.getBody().isEmpty());

		ResponseEntity<List<QuestionDTO>> questionsVote = questionservice.getByQuestionVoteById((long) 1);
		Assert.assertFalse(questionsVote.getBody().isEmpty());

		String resultat = questionservice.getResultat((long) 1);
		Assert.assertNotNull(resultat);
	}

	@Autowired
	ReponseService reponseservice;

	@Test
	public void testReponseService() throws URISyntaxException {
		logger.info("Test du ReponseService");

		ReponseDTO reponseDto = new ReponseDTO();
		reponseDto.setContenu("Test");
		reponseDto.setQuestionId(1);
		reponseDto.setUserId(1);
		boolean reponsetest = reponseservice.creerReponse(reponseDto);
		Assert.assertTrue(reponsetest);
	}

	@Autowired
	UserService userservice;

	@Test
	public void testUserService() throws URISyntaxException {
		logger.info("Test du UserService");

		boolean verifTest = userservice.verifExisteUser("createurtest");
		Assert.assertTrue(verifTest);

		boolean roleTest = userservice.getRoleById(3);
		Assert.assertTrue(roleTest);

		long userId = userservice.getIdByPseudo("createurtest");
		Assert.assertEquals(3, userId);

		UserDTO userDto = new UserDTO();
		userDto.setPassword("123");
		userDto.setPseudo("Test");
		userDto.setRole("CREATOR");
		userDto.setVoteId(1);
		boolean userTest = userservice.creerUser(userDto);
		Assert.assertTrue(userTest);

		UserDTO votant = new UserDTO();
		votant.setPassword("123");
		votant.setPseudo("Test");
		votant.setRole("USER");
		votant.setVoteId(1);
		boolean votantTest = userservice.creerVotant(votant);
		Assert.assertTrue(votantTest);
	}

	@Autowired
	VoteService voteservice;

	@Test
	public void testVoteService() throws URISyntaxException {
		logger.info("Test du VoteService");

//		when(activitirestclient.startProcess(Mockito.anyString())).thenReturn("2");
//		when(activitirestclient.runTask(Mockito.anyString())).thenReturn(true);
//		when(activitirestclient.getTask(Mockito.anyString())).thenReturn(new Task());

		List<Vote> votes = voteservice.getAllVotes(3);
		Assert.assertFalse(votes.isEmpty());

		ResponseEntity<Vote> vote = voteservice.getVoteById((long) 1);
		Assert.assertNotNull(vote.getBody());

//		VoteDTO voteDto = new VoteDTO();
//		voteDto.setDateFin("DateTest");
//		voteDto.setDescription("Test");
//		voteDto.setProfilFormId(1);
//		voteDto.setVoteFormId(1);
//		voteDto.setUserId(1);
//		long voteId = voteservice.creerVote(voteDto);
//		Assert.assertNotNull(voteId);
//
//		boolean voteTest = voteservice.verifVoteTerminer(1);
//		Assert.assertFalse(voteTest);

		voteservice.terminerVote(vote.getBody(), false);

		boolean votantTest = voteservice.getVerifVotant(1, 2);
		Assert.assertTrue(votantTest);

		ResponseEntity<Boolean> dispoTest = voteservice.getDisponible(1);
		Assert.assertFalse(dispoTest.getBody());
	}

	@Autowired
	VoteVotantService votevotantservice;

	@Test
	public void testVoteVotantService() throws URISyntaxException {
		logger.info("Test du VoteVotantService");

		List<User> users = votevotantservice.findUserByVoteId(1);
		Assert.assertFalse(users.isEmpty());
	}

}
