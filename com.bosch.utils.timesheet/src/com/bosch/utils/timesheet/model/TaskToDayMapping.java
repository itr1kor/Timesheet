package com.bosch.utils.timesheet.model;

import java.util.HashMap;
import java.util.Map;

public class TaskToDayMapping {

	private Map<Task, Double> mondayTasks = new HashMap<>();
	private Map<Task, Double> tuesdayTasks = new HashMap<>();
	private Map<Task, Double> wednesTasks = new HashMap<>();
	private Map<Task, Double> thursdayTasks = new HashMap<>();
	private Map<Task, Double> fridayTasks = new HashMap<>();
	
	public void addTaskToMonday(Task task, double effort) {
		assert task != null;
		mondayTasks.put(task, effort);
	}
	
	public void addTaskToTuesday(Task task, double effort) {
		assert task != null;
		tuesdayTasks.put(task, effort);
	}
	
	public void addTaskToWednesday(Task task, double effort) {
		assert task != null;
		wednesTasks.put(task, effort);
	}
	
	public void addTaskToThursday(Task task, double effort) {
		assert task != null;
		thursdayTasks.put(task, effort);
	}
	
	public void addTaskToFriday(Task task, double effort) {
		assert task != null;
		fridayTasks.put(task, effort);
	}
	
	public Map<Task, Double> getMondayTasks() {
		return mondayTasks;
	}

	public Map<Task, Double> getTuesdayTasks() {
		return tuesdayTasks;
	}

	public Map<Task, Double> getWednesTasks() {
		return wednesTasks;
	}

	public Map<Task, Double> getThursdayTasks() {
		return thursdayTasks;
	}

	public Map<Task, Double> getFridayTasks() {
		return fridayTasks;
	}
	
}
