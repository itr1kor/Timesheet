package com.bosch.utils.timesheet.wizard;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class WizardPage extends Pane {
	
	public WizardPage() {
		super();
		createContent();
		setStyle("-fx-padding: 10px; -fx-background-color: #FFFFFF");
	}

	private void createContent() {
		HBox taskNumnerFields = new HBox(10);
		Text text = new Text("Task Name : ");
		taskNumnerFields.getChildren().add(text);
		
		TextField textField = new TextField();
		textField.setMinWidth(300);
		taskNumnerFields.getChildren().add(textField);
		
		getChildren().add(taskNumnerFields);
	}

	public void getNextPage() {

	}
	
	public void getPreviousPage() {

	}
	
	public boolean hasNextPage() {
		return false;
	}
	
	public boolean canFinish() {
		return false;
	}
}
