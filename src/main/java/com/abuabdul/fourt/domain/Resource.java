package com.abuabdul.fourt.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author abuabdul
 *
 */
@Entity
@Table(name = "RESOURCE")
public class Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private String id;

	@Column(name = "NAME", length = 50, nullable = false)
	private String name;

	@Column(name = "TASK_DATE", nullable = false)
	private String taskDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "resource")
	private List<TaskDetail> taskDetailList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(String taskDate) {
		this.taskDate = taskDate;
	}

	public List<TaskDetail> getTaskDetailList() {
		return taskDetailList;
	}

	public void setTaskDetailList(List<TaskDetail> taskDetailList) {
		this.taskDetailList = taskDetailList;
	}

}
