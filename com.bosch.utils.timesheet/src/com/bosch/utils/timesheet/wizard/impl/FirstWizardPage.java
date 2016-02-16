package com.bosch.utils.timesheet.wizard.impl;

import com.bosch.utils.timesheet.wizard.WizardPage;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class FirstWizardPage extends WizardPage {

	@Override
	public void createPageContents() {
		HBox taskNumnerFields = new HBox(10);
		Text text = new Text("Task Name : ");
		taskNumnerFields.getChildren().add(text);
		
		TextField textField = new TextField("first");
		textField.setMinWidth(300);
		taskNumnerFields.getChildren().add(textField);
		
		getChildren().add(taskNumnerFields);
	}

}
