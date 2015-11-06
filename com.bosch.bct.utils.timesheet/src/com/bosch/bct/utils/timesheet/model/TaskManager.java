package com.bosch.bct.utils.timesheet.model;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
	private static TaskManager taskManager;
	private TaskManager() {}

	public static TaskManager getInstance() {
		if (taskManager == null) {
			taskManager = new TaskManager();
		}
		return taskManager;
	}
	private List<Task> tasks = new ArrayList<>();
	public List<Task> tasks() {
		return tasks;
	}
	public void addTask(Task task) {
		tasks.add(task);
	}

	public void removeTasks(Task task) {
		tasks.remove(task);		
	}
}
