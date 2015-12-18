package com.bosch.bct.utils.timesheet.model;

public class Task {
	private String name;
	private TaskType taskType;
	
	public Task(String initName, TaskType initTaskType) {
		name = initName;
		taskType = initTaskType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TaskType getTaskType() {
		return taskType;
	}
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}
}
