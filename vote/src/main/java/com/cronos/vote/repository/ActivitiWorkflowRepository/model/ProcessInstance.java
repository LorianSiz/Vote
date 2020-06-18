package com.cronos.vote.repository.ActivitiWorkflowRepository.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProcessInstance implements Serializable {

	private String id;
	private String url;
	private String businessKey = null;
	private boolean suspended;
	private String processDefinitionId;
	private String processDefinitionUrl;
	private String activityId;
	ArrayList<Object> variables = new ArrayList<Object>();
	private String tenantId;

	public ProcessInstance() {
	}

	public ProcessInstance(String id, String url, String businessKey, boolean suspended, String processDefinitionId,
			String processDefinitionUrl, String activityId, ArrayList<Object> variables, String tenantId) {
		super();
		this.id = id;
		this.url = url;
		this.businessKey = businessKey;
		this.suspended = suspended;
		this.processDefinitionId = processDefinitionId;
		this.processDefinitionUrl = processDefinitionUrl;
		this.activityId = activityId;
		this.variables = variables;
		this.tenantId = tenantId;
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

	public String getBusinessKey() {
		return businessKey;
	}

	public boolean getSuspended() {
		return suspended;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public String getProcessDefinitionUrl() {
		return processDefinitionUrl;
	}

	public String getActivityId() {
		return activityId;
	}

	public String getTenantId() {
		return tenantId;
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

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public void setProcessDefinitionUrl(String processDefinitionUrl) {
		this.processDefinitionUrl = processDefinitionUrl;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	@Override
	public String toString() {
		return "ProcessInstance [id=" + id + ", url=" + url + ", businessKey=" + businessKey + ", suspended="
				+ suspended + ", processDefinitionId=" + processDefinitionId + ", processDefinitionUrl="
				+ processDefinitionUrl + ", activityId=" + activityId + ", variables=" + variables + ", tenantId="
				+ tenantId + "]";
	}

}