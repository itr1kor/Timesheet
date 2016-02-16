package com.bosch.utils.timesheet.wizard.impl;

import com.bosch.utils.timesheet.model.Task;
import com.bosch.utils.timesheet.model.TaskManager;
import com.bosch.utils.timesheet.model.TaskType;
import com.bosch.utils.timesheet.wizard.Wizard;

public class CreateTaskWizard extends Wizard {

	@Override
	public void performFinish() {

		CreateTaskWizardPage wizardPage = (CreateTaskWizardPage) getCurrentPage();
		String taskName = wizardPage.getTaskName();
		if(wizardPage.isRequirementSelected()) {
			Task task = new Task(taskName + "_REQUIREMENT", TaskType.REQUIREMENT);
			TaskManager.getInstance().addTask(task);
		}
		if(wizardPage.isDesignSelected()) {
			Task task = new Task(taskName + "_DESIGN", TaskType.DESIGN);
			TaskManager.getInstance().addTask(task);
		}
		if(wizardPage.isCodingSelected()) {
			Task task = new Task(taskName + "_CODING", TaskType.CODING);
			TaskManager.getInstance().addTask(task);
		}
		if(wizardPage.isTestingSelected()) {
			Task task = new Task(taskName + "_TESTING", TaskType.TESTING);
			TaskManager.getInstance().addTask(task);
		}
		if(wizardPage.isReviewSelected()) {
			Task task = new Task(taskName + "_REVIEW", TaskType.REVIEW);
			TaskManager.getInstance().addTask(task);
		}
	}

	@Override
	public void perfomCancel() {
	}

}
