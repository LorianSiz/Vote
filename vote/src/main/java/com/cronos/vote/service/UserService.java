package com.cronos.vote.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cronos.vote.dto.UserDTO;
import com.cronos.vote.model.User;
import com.cronos.vote.model.VoteVotant;
import com.cronos.vote.repository.UserRepository;
import com.cronos.vote.repository.VoteRepository;
import com.cronos.vote.repository.VoteVotantRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private VoteRepository voterepository;

	@Autowired
	private VoteVotantRepository votevotantrepository;

	private static final Logger logger = Logger.getLogger(UserService.class);

	public boolean verifExisteUser(String pseudo) {
		logger.info("Demande de vérification de l'existance de l'utilisateur: " + pseudo);
		return (userrepository.getUserByPseudo(pseudo) != null);
	}

	public boolean creerUser(UserDTO userDto) {
		if (verifExisteUser(userDto.getPseudo())) {
			logger.info("Utilisateur déjà existant");
			return false;
		} else {
			logger.info("Création d'un utilisateur: " + userDto.toString());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			User user = new User();
			user.setPseudo(userDto.getPseudo());
			user.setRole(userDto.getRole());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userrepository.save(user);
			return true;
		}
	}

	public boolean creerVotant(UserDTO votant) {
		logger.info("Création d'un votant: " + votant.toString());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		User user = new User();
		if (verifExisteUser(votant.getPseudo())) {
			user = userrepository.getUserByPseudo(votant.getPseudo());
		} else {
			user.setPseudo(votant.getPseudo());
			user.setRole(votant.getRole());
			user.setPassword(passwordEncoder.encode(votant.getPassword()));
			user = userrepository.save(user);
		}

		VoteVotant votevotant = new VoteVotant();
		votevotant.setUser(user);
		votevotant.setVote(voterepository.getOne(votant.getVoteId()));
		votevotantrepository.save(votevotant);
		return true;
	}

	public long getIdByPseudo(String pseudo) {
		logger.info("Récupération de l'id de l'utilisateur: " + pseudo);
		return userrepository.getUserByPseudo(pseudo).getId();
	}

	public boolean getRoleById(long id) {
		logger.info("Récupération du rôle de l'utilisateur: " + id);
		return (userrepository.getOne(id).getRole().equals("CREATOR"));
	}
}
