package com.cronos.vote.repository.ActivitiWorkflowRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.ProcessDefList;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.ProcessInstance;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.ProcessInstanceList;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.TaskActiviti;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.TaskList;

@Service
public class ActivitiRestClient implements WorkflowRepository {

	private RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();

	@Autowired
	RestTemplate restTemplate;

	public ActivitiRestClient() {
		this.restTemplate = this.restTemplateBuilder.basicAuthentication("kermit", "kermit").build();
	}

	public ProcessDefList getProcessDefList() {
		String url = "http://localhost:8080/activiti-rest/service/repository/process-definitions";
		ResponseEntity<ProcessDefList> response = this.restTemplate.getForEntity(url, ProcessDefList.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();

		} else {
			return null;
		}
	}

	public ProcessInstanceList getProcessInstanceList() {
		String url = "http://localhost:8080/activiti-rest/service/runtime/process-instances";
		ResponseEntity<ProcessInstanceList> response = this.restTemplate.getForEntity(url, ProcessInstanceList.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();

		} else {
			return null;
		}
	}

	public TaskList getTaskList() {
		String url = "http://localhost:8080/activiti-rest/service/runtime/tasks";
		ResponseEntity<TaskList> response = this.restTemplate.getForEntity(url, TaskList.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();

		} else {
			return null;
		}
	}

	@Override
	public boolean startProcess(String processInstanceRef) {
		String url = "http://localhost:8080/activiti-rest/service/runtime/process-instances";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		Map<String, Object> map = new HashMap<>();
		map.put("processDefinitionKey", processInstanceRef);

		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		ResponseEntity<ProcessInstance> response = this.restTemplate.postForEntity(url, entity, ProcessInstance.class);

		if ((response.getStatusCode() == HttpStatus.CREATED) && (response.getBody() != null)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Task getTaskState(String processId) {
		String url = "http://localhost:8080/activiti-rest/service/runtime/tasks/" + processId;
		ResponseEntity<TaskActiviti> response = this.restTemplate.getForEntity(url, TaskActiviti.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			return new Task(response.getBody().getId(), response.getBody().getName(),
					response.getBody().getDescription());
		} else {
			return null;
		}
	}

	@Override
	public boolean runTask(String taskId) {
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

	@Override
	public List<Task> getTaskListUser(String user) {
		String url = "http://localhost:8080/activiti-rest/service/runtime/tasks?user=" + user;
		ResponseEntity<TaskList> response = this.restTemplate.getForEntity(url, TaskList.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			List<Task> tasklist = new ArrayList<Task>();
			TaskList tasklistactiviti = response.getBody();
			for (int i = 0; i < tasklistactiviti.getData().size(); i++) {
				Task task = new Task(tasklistactiviti.getData().get(i).getId(),
						tasklistactiviti.getData().get(i).getName(),
						tasklistactiviti.getData().get(i).getDescription());
				tasklist.add(task);
			}
			return tasklist;
		} else {
			return null;
		}
	}
}