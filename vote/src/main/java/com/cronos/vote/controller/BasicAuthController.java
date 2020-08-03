package com.cronos.vote.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.vote.model.Authentication;

@RestController
@RequestMapping("/api")
public class BasicAuthController {

	private static final Logger logger = Logger.getLogger(BasicAuthController.class);

	@GetMapping("/basicauth")
	public Authentication basicauth() {
		logger.info("Authentification réussie.");
		return new Authentication("Authentification réussie.");
	}
}