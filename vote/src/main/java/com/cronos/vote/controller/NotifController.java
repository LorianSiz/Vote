package com.cronos.vote.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.vote.service.NotifService;

@RestController
@RequestMapping("api/notifications")
public class NotifController {

	@Autowired
	private NotifService notifservice;

	private static final Logger logger = Logger.getLogger(NotifController.class);

	@PostMapping("/debut")
	public void envoieNotifDebut(@RequestBody String id) {
		logger.info("Demande de notification de lancement");
		notifservice.envoieNotifDebut(id);
	}

	@PostMapping("/rappel")
	public void envoieNotifRappel(@RequestBody String id) {
		logger.info("Demande de notification de rappel");
		notifservice.envoieNotifRappel(id);
	}

	@PostMapping("/resultat")
	public void envoieNotifResultat(@RequestBody String id) {
		logger.info("Demande de notification de résultat");
		notifservice.envoieNotifResultat(id);
	}

}
