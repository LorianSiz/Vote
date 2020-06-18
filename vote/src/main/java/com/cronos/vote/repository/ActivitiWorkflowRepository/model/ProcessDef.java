package com.cronos.vote.repository.ActivitiWorkflowRepository.model;

import java.io.Serializable;

public class ProcessDef implements Serializable {

	private String id;
	private String url;
	private String key;
	private float version;
	private String name;
	private String description;
	private String deploymentId;
	private String deploymentUrl;
	private String resource;
	private String diagramResource = null;
	private String category;
	private boolean graphicalNotationDefined;
	private boolean suspended;
	private boolean startFormDefined;

	public ProcessDef() {
	}

	public ProcessDef(String id, String url, String key, float version, String name, String description,
			String deploymentId, String deploymentUrl, String resource, String diagramResource, String category,
			boolean graphicalNotationDefined, boolean suspended, boolean startFormDefined) {
		super();
		this.id = id;
		this.url = url;
		this.key = key;
		this.version = version;
		this.name = name;
		this.description = description;
		this.deploymentId = deploymentId;
		this.deploymentUrl = deploymentUrl;
		this.resource = resource;
		this.diagramResource = diagramResource;
		this.category = category;
		this.graphicalNotationDefined = graphicalNotationDefined;
		this.suspended = suspended;
		this.startFormDefined = startFormDefined;
	}

	// Getter Methods

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getKey() {
		return key;
	}

	public float getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public String getDeploymentUrl() {
		return deploymentUrl;
	}

	public String getResource() {
		return resource;
	}

	public String getDiagramResource() {
		return diagramResource;
	}

	public String getCategory() {
		return category;
	}

	public boolean getGraphicalNotationDefined() {
		return graphicalNotationDefined;
	}

	public boolean getSuspended() {
		return suspended;
	}

	public boolean getStartFormDefined() {
		return startFormDefined;
	}

	// Setter Methods

	public void setId(String id) {
		this.id = id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public void setDeploymentUrl(String deploymentUrl) {
		this.deploymentUrl = deploymentUrl;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void setDiagramResource(String diagramResource) {
		this.diagramResource = diagramResource;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setGraphicalNotationDefined(boolean graphicalNotationDefined) {
		this.graphicalNotationDefined = graphicalNotationDefined;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public void setStartFormDefined(boolean startFormDefined) {
		this.startFormDefined = startFormDefined;
	}

	@Override
	public String toString() {
		return "Process [id=" + id + ", url=" + url + ", key=" + key + ", version=" + version + ", name=" + name
				+ ", description=" + description + ", deploymentId=" + deploymentId + ", deploymentUrl=" + deploymentUrl
				+ ", resource=" + resource + ", diagramResource=" + diagramResource + ", category=" + category
				+ ", graphicalNotationDefined=" + graphicalNotationDefined + ", suspended=" + suspended
				+ ", startFormDefined=" + startFormDefined + "]";
	}

}