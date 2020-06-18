package com.cronos.vote.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class NotifController {

	private static final Logger logger = Logger.getLogger(NotifController.class);

	@PostMapping("/notification/debut")
	@ResponseStatus(HttpStatus.CREATED)
	public void envoieNotifDebut(@RequestBody String user) {
		user = user.split("=")[1];
		logger.info("Demande de notification de lancement de vote pour l'utilisateur: " + user + ".");
	}

	@PostMapping("/notification/rappel")
	@ResponseStatus(HttpStatus.CREATED)
	public void envoieNotifRappel(@RequestBody String user) {
		user = user.split("=")[1];
		logger.info("Demande de notification de rappel pour l'utilisateur: " + user + ".");
	}

	@PostMapping("/notification/resultat")
	@ResponseStatus(HttpStatus.CREATED)
	public void envoieNotifResultat(@RequestBody String user) {
		user = user.split("=")[1];
		logger.info("Demande de notification de résultat pour l'utilisateur: " + user + ".");
	}

}
