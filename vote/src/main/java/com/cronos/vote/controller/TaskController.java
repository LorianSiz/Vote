package com.cronos.vote.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cronos.vote.model.Task;
import com.cronos.vote.repository.ActivitiWorkflowRepository.ActivitiRestClient;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

	private ActivitiRestClient activitirestclient = new ActivitiRestClient();

	private static final Logger logger = Logger.getLogger(TaskController.class);

	@GetMapping("/tasks")
	public List<Task> getAllTask() {
		logger.info("Récupération de la liste des tasks.");

		// System.out.println(activitirestclient.getTaskListUser("Kermit"));
		return activitirestclient.getTaskListUser("Kermit");
	}

	@PostMapping("/tasks/do/{id}")
	public void doTask(@PathVariable(value = "id") String id) {
		logger.info("Execution d'une task.");

		// System.out.println(id);
		activitirestclient.runTask(id);
	}

}
