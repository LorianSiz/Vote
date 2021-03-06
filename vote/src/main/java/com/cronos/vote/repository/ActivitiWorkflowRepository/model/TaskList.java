package com.cronos.vote.repository.ActivitiWorkflowRepository.model;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {

	ArrayList<TaskActiviti> data = new ArrayList<TaskActiviti>();
	private float total;
	private float start;
	private String sort;
	private String order;
	private float size;

	public TaskList() {
	}

	public TaskList(ArrayList<TaskActiviti> data, float total, float start, String sort, String order, float size) {
		super();
		this.data = data;
		this.total = total;
		this.start = start;
		this.sort = sort;
		this.order = order;
		this.size = size;
	}

	// Getter Methods

	public ArrayList<TaskActiviti> getData() {
		return data;
	}

	public float getTotal() {
		return total;
	}

	public float getStart() {
		return start;
	}

	public String getSort() {
		return sort;
	}

	public String getOrder() {
		return order;
	}

	public float getSize() {
		return size;
	}

	// Setter Methods

	public void setData(ArrayList<TaskActiviti> data) {
		this.data = data;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public void setStart(float start) {
		this.start = start;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setSize(float size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "TaskList [data=" + data + ", total=" + total + ", start=" + start + ", sort=" + sort + ", order="
				+ order + ", size=" + size + "]";
	}

}
