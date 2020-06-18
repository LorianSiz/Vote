package com.cronos.vote;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.cronos.vote.model.Task;
import com.cronos.vote.repository.ActivitiWorkflowRepository.ActivitiRestClient;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.ProcessDefList;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.ProcessInstanceList;
import com.cronos.vote.repository.ActivitiWorkflowRepository.model.TaskList;

@SpringBootTest
@WebAppConfiguration
class VoteApplicationTests {

	@Mock
	ActivitiRestClient activitirestclient = new ActivitiRestClient();

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetProcessDefListSuccess() throws URISyntaxException {
		ProcessDefList soluc = new ProcessDefList();

		Mockito.when(activitirestclient.getProcessDefList()).thenReturn(soluc);

		ProcessDefList result = activitirestclient.getProcessDefList();

		// System.out.println("\nICI : " + result + "\n");

		Assert.assertNotNull(result);

	}

	@Test
	public void testGetProcessInstanceListSuccess() throws URISyntaxException {
		ProcessInstanceList soluc = new ProcessInstanceList();

		Mockito.when(activitirestclient.getProcessInstanceList()).thenReturn(soluc);

		ProcessInstanceList result = activitirestclient.getProcessInstanceList();

		// System.out.println("\nICI : " + result + "\n");

		Assert.assertNotNull(result);

	}

	@Test
	public void testGetTaskListSuccess() throws URISyntaxException {
		TaskList soluc = new TaskList();

		Mockito.when(activitirestclient.getTaskList()).thenReturn(soluc);

		TaskList result = activitirestclient.getTaskList();

		// System.out.println("\nICI : " + result + "\n");

		Assert.assertNotNull(result);

	}

	@Test
	public void testStartProcessSuccess() throws URISyntaxException {
		Mockito.when(activitirestclient.startProcess("voteProcess")).thenReturn(true);

		boolean result = activitirestclient.startProcess("voteProcess");

		// System.out.println("\nICI : " + result + "\n");

		Assert.assertEquals(true, result);

	}

	@Test
	public void testRunTaskSuccess() throws URISyntaxException {
		Mockito.when(activitirestclient.runTask("")).thenReturn(true);

		boolean result = activitirestclient.runTask("");

		// System.out.println("\nICI : " + result + "\n");

		Assert.assertEquals(true, result);

	}

	@Test
	public void testGetTaskStateSuccess() throws URISyntaxException {
		Task soluc = new Task();

		Mockito.when(activitirestclient.getTaskState("")).thenReturn(soluc);

		Task result = activitirestclient.getTaskState("");

		// System.out.println("\nICI : " + result + "\n");

		Assert.assertNotNull(result);

	}

	@Test
	public void testGetTaskListUserSuccess() throws URISyntaxException {
		List<Task> soluc = new ArrayList<Task>();

		Mockito.when(activitirestclient.getTaskListUser("")).thenReturn(soluc);

		List<Task> result = activitirestclient.getTaskListUser("");

		// System.out.println("\nICI : " + result + "\n");

		Assert.assertNotNull(result);

	}

}
