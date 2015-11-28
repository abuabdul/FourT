package com.abuabdul.fourt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author abuabdul
 *
 */
@Entity
@Table(name = "TASK_DETAIL")
public class TaskDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TASK_DESC", nullable = false)
	private String taskDesc;

	@Column(name = "TASK_DURATION", nullable = false)
	private Float duration;

	@Column(name = "TASK_STATUS", nullable = false)
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RESOURCE_ID")
	private Resource resource;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public Float getDuration() {
		return duration;
	}

	public void setDuration(Float duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

}
