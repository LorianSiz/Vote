package com.cronos.vote.repository.ActivitiWorkflowRepository.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskActiviti implements Serializable {

	private String id;
	private String url;
	private String owner = null;
	private String assignee;
	private String delegationState = null;
	private String name;
	private String description;
	private String createTime;
	private String dueDate = null;
	private float priority;
	private boolean suspended;
	private String taskDefinitionKey;
	private String tenantId;
	private String category = null;
	private String parentTaskId = null;
	private String parentTaskUrl = null;
	private String executionId;
	private String executionUrl;
	private String processInstanceId;
	private String processInstanceUrl;
	private String processDefinitionId;
	private String processDefinitionUrl;
	ArrayList<Object> variables = new ArrayList<Object>();

	public TaskActiviti() {
	}

	public TaskActiviti(String id, String url, String owner, String assignee, String delegationState, String name,
			String description, String createTime, String dueDate, float priority, boolean suspended,
			String taskDefinitionKey, String tenantId, String category, String parentTaskId, String parentTaskUrl,
			String executionId, String executionUrl, String processInstanceId, String processInstanceUrl,
			String processDefinitionId, String processDefinitionUrl, ArrayList<Object> variables) {
		super();
		this.id = id;
		this.url = url;
		this.owner = owner;
		this.assignee = assignee;
		this.delegationState = delegationState;
		this.name = name;
		this.description = description;
		this.createTime = createTime;
		this.dueDate = dueDate;
		this.priority = priority;
		this.suspended = suspended;
		this.taskDefinitionKey = taskDefinitionKey;
		this.tenantId = tenantId;
		this.category = category;
		this.parentTaskId = parentTaskId;
		this.parentTaskUrl = parentTaskUrl;
		this.executionId = executionId;
		this.executionUrl = executionUrl;
		this.processInstanceId = processInstanceId;
		this.processInstanceUrl = processInstanceUrl;
		this.processDefinitionId = processDefinitionId;
		this.processDefinitionUrl = processDefinitionUrl;
		this.variables = variables;
	}

	// Getter Methods

	public ArrayList<Object> getVariables() {
		return variables;
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getOwner() {
		return owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public String getDelegationState() {
		return delegationState;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getDueDate() {
		return dueDate;
	}

	public float getPriority() {
		return priority;
	}

	public boolean getSuspended() {
		return suspended;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public String getTenantId() {
		return tenantId;
	}

	public String getCategory() {
		return category;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public String getParentTaskUrl() {
		return parentTaskUrl;
	}

	public String getExecutionId() {
		return executionId;
	}

	public String getExecutionUrl() {
		return executionUrl;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public String getProcessInstanceUrl() {
		return processInstanceUrl;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public String getProcessDefinitionUrl() {
		return processDefinitionUrl;
	}

	// Setter Methods

	public void setVariables(ArrayList<Object> variables) {
		this.variables = variables;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public void setDelegationState(String delegationState) {
		this.delegationState = delegationState;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setPriority(float priority) {
		this.priority = priority;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public void setParentTaskUrl(String parentTaskUrl) {
		this.parentTaskUrl = parentTaskUrl;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public void setExecutionUrl(String executionUrl) {
		this.executionUrl = executionUrl;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public void setProcessInstanceUrl(String processInstanceUrl) {
		this.processInstanceUrl = processInstanceUrl;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public void setProcessDefinitionUrl(String processDefinitionUrl) {
		this.processDefinitionUrl = processDefinitionUrl;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", url=" + url + ", owner=" + owner + ", assignee=" + assignee + ", delegationState="
				+ delegationState + ", name=" + name + ", description=" + description + ", createTime=" + createTime
				+ ", dueDate=" + dueDate + ", priority=" + priority + ", suspended=" + suspended
				+ ", taskDefinitionKey=" + taskDefinitionKey + ", tenantId=" + tenantId + ", category=" + category
				+ ", parentTaskId=" + parentTaskId + ", parentTaskUrl=" + parentTaskUrl + ", executionId=" + executionId
				+ ", executionUrl=" + executionUrl + ", processInstanceId=" + processInstanceId
				+ ", processInstanceUrl=" + processInstanceUrl + ", processDefinitionId=" + processDefinitionId
				+ ", processDefinitionUrl=" + processDefinitionUrl + ", variables=" + variables + "]";
	}

}