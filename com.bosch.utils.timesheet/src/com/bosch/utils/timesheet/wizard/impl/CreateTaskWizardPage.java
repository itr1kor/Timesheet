package com.bosch.utils.timesheet.wizard.impl;

import com.bosch.utils.timesheet.wizard.WizardPage;

import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreateTaskWizardPage extends WizardPage {

	private TextField textField;
	private CheckBox requirementBox;
	private CheckBox designBox;
	private CheckBox codingBox;
	private CheckBox testingBox;
	private CheckBox reviewBox;

	@Override
	public void createPageContents() {
		
		HBox taskNumnerFields = new HBox(10);
		Text text = new Text("Task Name/Number : ");
		taskNumnerFields.getChildren().add(text);
		
		textField = new TextField("first");
		textField.setMinWidth(300);
		taskNumnerFields.getChildren().add(textField);
		getChildren().add(taskNumnerFields);
		
		VBox checkBoxList = new VBox(5);
		checkBoxList.setStyle("-fx-padding: 20px; -fx-background-color: #FFFFFF");
		requirementBox = new CheckBox("Requirement");
		checkBoxList.getChildren().add(requirementBox);
		designBox = new CheckBox("Design");
		checkBoxList.getChildren().add(designBox);
		codingBox = new CheckBox("Coding");
		checkBoxList.getChildren().add(codingBox);
		testingBox = new CheckBox("Testing");
		checkBoxList.getChildren().add(testingBox);
		reviewBox = new CheckBox("Review");
		checkBoxList.getChildren().add(reviewBox);
		
		getChildren().add(checkBoxList);
		
	}

	public String getTaskName() {
		return textField.getText();
	}
	
	public boolean isRequirementSelected() {
		return requirementBox.isSelected();
	}
	
	public boolean isDesignSelected() {
		return designBox.isSelected();
	}
	
	public boolean isCodingSelected() {
		return codingBox.isSelected();
	}
	
	public boolean isTestingSelected() {
		return testingBox.isSelected();
	}
	
	public boolean isReviewSelected() {
		return reviewBox.isSelected();
	}

}
