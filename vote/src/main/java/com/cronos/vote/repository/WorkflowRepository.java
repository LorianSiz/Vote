package com.cronos.vote.repository;

import com.cronos.vote.model.Task;

public interface WorkflowRepository {

	/**
	 * 
	 * @param processInstanceRef
	 * @return the process id
	 */

	public Task getTask(String processId);

	public boolean runTask(String taskId);

	public String startProcess(String processId);

}
