package com.cronos.vote.repository.ActivitiWorkflowRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cronos.vote.model.Task;
import com.cronos.vote.repository.WorkflowRepository;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.ProcessInstance;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.TaskActiviti;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.TaskList;

@Service
public class ActivitiRestClient implements WorkflowRepository {

	private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

	@Autowired
	RestTemplate restTemplate;

	@Override
	public String startProcess(String processId) {
		this.restTemplate = this.restTemplateBuilder.basicAuthentication("SpringVote", "12345").build();
		String url = "http://localhost:8080/activiti-rest/service/runtime/process-instances";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("processDefinitionKey", processId);
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		return this.restTemplate.postForEntity(url, entity, ProcessInstance.class).getBody().getId();
	}

	@Override
	public Task getTask(String processId) {
		this.restTemplate = this.restTemplateBuilder.basicAuthentication("SpringVote", "12345").build();
		String url = "http://localhost:8080/activiti-rest/service/runtime/tasks?processInstanceId=" + processId;
		ResponseEntity<TaskList> response = this.restTemplate.getForEntity(url, TaskList.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			TaskActiviti taskActiviti = response.getBody().getData().get(0);
			Task task = new Task();
			task.setIdActiviti(taskActiviti.getId());
			task.setName(taskActiviti.getName());
			task.setDescription(taskActiviti.getDescription());
			return task;
		} else {
			return null;
		}
	}

	@Override
	public boolean runTask(String taskId) {
		this.restTemplate = this.restTemplateBuilder.basicAuthentication("SpringVote", "12345").build();
		String url = "http://localhost:8080/activiti-rest/service/runtime/tasks/" + taskId;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("action", "complete");

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<TaskActiviti> response = this.restTemplate.postForEntity(url, entity, TaskActiviti.class);

		if ((response.getStatusCode() == HttpStatus.CREATED) && (response.getBody() == null)) {
			return true;
		} else {
			return false;
		}
	}

}