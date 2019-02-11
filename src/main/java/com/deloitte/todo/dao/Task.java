package com.deloitte.todo.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;

@Entity(name="task")
@Table(name = "TASKS")
@NamedQuery(name="Task.findByUser", query="SELECT t FROM task t WHERE userId= :userId")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "TASK_NAME")
	private String taskName;

	@Column(name = "TASK_STATUS")
	private String taskStatus;

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	@Column(name = "TASK_DESC")
	private String taskDesc;

	@Column(name = "TASK_UPDATE_DATE")
	private String taskUpdateDate;
	
	@Column(name = "USER_ID")
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public String getTaskUpdateDate() {
		return taskUpdateDate;
	}

	public void setTaskUpdateDate(String taskUpdateDate) {
		this.taskUpdateDate = taskUpdateDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
