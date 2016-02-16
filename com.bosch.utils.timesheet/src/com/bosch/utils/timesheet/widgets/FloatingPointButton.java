package com.bosch.utils.timesheet.widgets;

import com.bosch.utils.timesheet.wizard.Wizard;
import com.bosch.utils.timesheet.wizard.WizardDialog;
import com.bosch.utils.timesheet.wizard.impl.CreateTaskWizard;
import com.bosch.utils.timesheet.wizard.impl.CreateTaskWizardPage;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FloatingPointButton extends Circle {

	public FloatingPointButton(double radius) {
		super(radius);
		setFill(Color.TOMATO);
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				WizardDialog dialog  = new WizardDialog();
				Wizard wizard = new CreateTaskWizard();
				CreateTaskWizardPage wizardPage = new CreateTaskWizardPage();
				wizard.addPage(wizardPage);
				wizard.setTitle("Create New Task");
				wizard.setMessage("Create new tasks by filling name/number and selecting specific task type.");
				dialog.setWizard(wizard);
				dialog.show();
			}
		});
	}
}
