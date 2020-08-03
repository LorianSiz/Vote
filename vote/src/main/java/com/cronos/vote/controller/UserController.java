package com.cronos.vote.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.vote.dto.UserDTO;
import com.cronos.vote.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userservice;

	private static final Logger logger = Logger.getLogger(UserController.class);

	@PostMapping("/creer")
	public boolean creerUser(@Valid @RequestBody UserDTO userDto) {
		logger.info("Demande de création d'un utilisateur");
		return userservice.creerUser(userDto);
	}

	@PostMapping("/pseudo")
	public long getIdByPseudo(@Valid @RequestBody String pseudo) {
		logger.info("Demande de récupération de l'id d'un utilisateur");
		return userservice.getIdByPseudo(pseudo);
	}

	@PostMapping("/votant")
	public boolean verifExisteUser(@Valid @RequestBody UserDTO votant) {
		logger.info("Demande de création d'un votant");
		return userservice.creerVotant(votant);
	}

	@PostMapping("/role")
	public boolean getRoleById(@Valid @RequestBody long id) {
		logger.info("Demande de récupération du rôle d'un utilisateur");
		return userservice.getRoleById(id);
	}

}
