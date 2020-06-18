package com.cronos.vote.repository;

import java.util.List;

import com.cronos.vote.model.Task;

public interface WorkflowRepository {

	/**
	 * 
	 * @param processInstanceRef
	 * @return the process id
	 */
	public boolean startProcess(String processInstanceRef);

	public Task getTaskState(String processId);

	public boolean runTask(String taskId);

	public List<Task> getTaskListUser(String user);

}
