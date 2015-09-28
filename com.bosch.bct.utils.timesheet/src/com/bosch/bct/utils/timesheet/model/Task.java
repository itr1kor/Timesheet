package com.bosch.bct.utils.timesheet.model;

import java.util.HashMap;
import java.util.Map;

public class Task {
	private String name;
	private TaskType taskType;
	private Map<Day, Double> taskMapping = new HashMap<>();
	
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
	public Map<Day, Double> getTaskMapping() {
		return taskMapping;
	}
	public void setTaskMapping(Map<Day, Double> taskMapping) {
		this.taskMapping = taskMapping;
	}
	public void addTaskMapping(Day day, Double effort) {
		if(taskMapping.containsKey(day)){
			Double value = taskMapping.get(day);
			taskMapping.put(day, value+effort);
		}else{
			taskMapping.put(day, effort);
		}
	}
}
