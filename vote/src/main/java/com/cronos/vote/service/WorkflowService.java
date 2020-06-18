package com.cronos.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cronos.vote.repository.WorkflowRepository;

@Service
public class WorkflowService {

	@Autowired
	private WorkflowRepository workflowRepostory;

	/*
	 * public void createVote(Vote vote) {
	 * workflowRepostory.startProcess(processInstanceRef);
	 * voteRepository.save(vote);
	 * 
	 * }
	 */

}
