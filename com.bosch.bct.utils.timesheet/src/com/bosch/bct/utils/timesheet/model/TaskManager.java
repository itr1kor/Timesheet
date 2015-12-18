package com.bosch.bct.utils.timesheet.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskManager {
	private static TaskManager taskManager = new TaskManager(); 

	private List<Task> tasks = new ArrayList<>();
	private TaskToDayMapping taskToDayMapping = new TaskToDayMapping();
	
	private TaskManager() {
		
	}
	
	public List<Task> tasks() {
		return tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void mapTask(Day day, Task task, double effort) {
		if(day.name().equalsIgnoreCase("Monday")) {
			taskToDayMapping.addTaskToMonday(task, effort);
		} else if(day.name().equalsIgnoreCase("Tuesday")) {
			taskToDayMapping.addTaskToTuesday(task, effort);
		} else if(day.name().equalsIgnoreCase("Wednesday")) {
			taskToDayMapping.addTaskToWednesday(task, effort);
		} else if(day.name().equalsIgnoreCase("Thursday")) {
			taskToDayMapping.addTaskToThursday(task, effort);
		} else if(day.name().equalsIgnoreCase("Friday")) {
			taskToDayMapping.addTaskToFriday(task, effort);
		} 
	}
	
	public Map<Task, Double> getMapping(Day day) {
		if(day.name().equalsIgnoreCase("Monday")) {
			return taskToDayMapping.getMondayTasks();
		} else if(day.name().equalsIgnoreCase("Tuesday")) {
			return taskToDayMapping.getTuesdayTasks();
		} else if(day.name().equalsIgnoreCase("Wednesday")) {
			return taskToDayMapping.getWednesTasks();
		} else if(day.name().equalsIgnoreCase("Thursday")) {
			return taskToDayMapping.getThursdayTasks();
		} else if(day.name().equalsIgnoreCase("Friday")) {
			return taskToDayMapping.getFridayTasks();
		}
		return null; 
	}
	
	
	public Double dayEffort(Day day) {
		Double totalEffort = 0.0;
		Map<Task, Double> mappedTasks = null;
		if(day.name().equalsIgnoreCase("Monday")) {
			mappedTasks = taskToDayMapping.getMondayTasks();
		} else if(day.name().equalsIgnoreCase("Tuesday")) {
			mappedTasks =  taskToDayMapping.getTuesdayTasks();
		} else if(day.name().equalsIgnoreCase("Wednesday")) {
			mappedTasks =  taskToDayMapping.getWednesTasks();
		} else if(day.name().equalsIgnoreCase("Thursday")) {
			mappedTasks =  taskToDayMapping.getThursdayTasks();
		} else if(day.name().equalsIgnoreCase("Friday")) {
			mappedTasks =  taskToDayMapping.getFridayTasks();
		}
		Set<Task> keySet = mappedTasks.keySet();
		for (Task task : keySet) {
			totalEffort += mappedTasks.get(task);
		}
		return totalEffort;
	}
	
	public Double getTaskEffort(Task task) {
		Double totalEffort = 0.0;
		if (taskToDayMapping.getMondayTasks().containsKey(task)) {
			totalEffort += taskToDayMapping.getMondayTasks().get(task);
		}
		if (taskToDayMapping.getTuesdayTasks().containsKey(task)) {
			totalEffort += taskToDayMapping.getTuesdayTasks().get(task);
		}
		if (taskToDayMapping.getWednesTasks().containsKey(task)) {
			totalEffort += taskToDayMapping.getWednesTasks().get(task);
		}
		if (taskToDayMapping.getThursdayTasks().containsKey(task)) {
			totalEffort += taskToDayMapping.getThursdayTasks().get(task);
		}
		if (taskToDayMapping.getFridayTasks().containsKey(task)) {
			totalEffort += taskToDayMapping.getFridayTasks().get(task);
		}
		return totalEffort;
	}

	public void removeTasks(Task task) {
		tasks.remove(task);		
	}

	public static TaskManager getInstance() {
		return taskManager;
	}
}
