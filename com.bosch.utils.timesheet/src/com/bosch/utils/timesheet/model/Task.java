package com.bosch.utils.timesheet.model;

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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Task) {
			Task task = (Task) obj;
			if(task.name.equalsIgnoreCase(name) && task.taskType.equals(taskType)) {
				return true;
			}
		}
		return super.equals(obj);
	}
}
