package com.bosch.utils.timesheet.wizard;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Wizard extends Scene {

	private VBox root;
	private Text titleText;
	private Text messageText;
	private StackPane content;
	private WizardPage currentPage;
	
	private Button backButton;
	private Button nextButton;
	private Button finishButton;
	
	public Wizard() {
		super(new VBox(1));
		root = (VBox) getRoot();
		createContent();
		root.setMinSize(600, 300);
		root.setStyle("-fx-background-color: #000000");
	}
	
	private void createContent() {
		
		VBox header = new VBox(5);
		header.setFillWidth(true);
		header.setMinHeight(50);
		header.setStyle("-fx-padding: 10px; -fx-background-color: #FFFFFF");
		
		titleText = new Text("Title");
		titleText.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
		header.getChildren().add(titleText);
		
		messageText = new Text("This must be very long message");
		messageText.setFont(Font.font("Times New Roman", FontWeight.LIGHT, 12));
		header.getChildren().add(messageText);
		root.getChildren().add(header);
		
		content = new StackPane();
		content.setMinHeight(220);
		content.setStyle("-fx-padding: 10px; -fx-background-color: #FFFFFF");
		root.getChildren().add(content);
		
		HBox navigationButtonList = new HBox(10);
		navigationButtonList.setMinHeight(50);
		navigationButtonList.setAlignment(Pos.CENTER_RIGHT);
		navigationButtonList.setStyle("-fx-padding: 10px; -fx-background-color: #FFFFFF");
		
		backButton = new Button("Back");
		backButton.setMinWidth(75);
		backButton.setDisable(true);
		backButton.setVisible(content.getChildren().size() > 1);
		navigationButtonList.getChildren().add(backButton);
		backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				currentPage.setVisible(false);
				int index = content.getChildren().indexOf(currentPage);
				if (-1 < index - 1) {
					currentPage = (WizardPage) content.getChildren().get(index - 1);
//					content.getChildren().remove(currentPage);
//					content.getChildren().add(currentPage);
					currentPage.setVisible(true);
					updateButton();
				}
			}
		});
		
		nextButton = new Button("Next");
		nextButton.setMinWidth(75);
		nextButton.setVisible(content.getChildren().size() > 1);
		navigationButtonList.getChildren().add(nextButton);
		nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				currentPage.setVisible(false);
				int index = content.getChildren().indexOf(currentPage);
				if (content.getChildren().size() > index + 1) {
					currentPage = (WizardPage) content.getChildren().get(index + 1);
//					content.getChildren().remove(currentPage);
//					content.getChildren().add(currentPage);
					currentPage.setVisible(true);
					updateButton();
				}
			}
		});
		
		
		finishButton = new Button("Finish");
		finishButton.setMinWidth(75);
		navigationButtonList.getChildren().add(finishButton);
		finishButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				performFinish();
			}
		});
		
		Button cancelButton = new Button("Cancel");
		cancelButton.setMinWidth(75);
		navigationButtonList.getChildren().add(cancelButton);
		cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				perfomCancel();
			}
		});
		
		root.getChildren().add(navigationButtonList);
	}
	
	
	protected void updateButton() {
		backButton.setDisable(content.getChildren().indexOf(currentPage) == 0);
		nextButton.setDisable(!currentPage.hasNextPage());
		finishButton.setDisable(!currentPage.canFinish());
	}

	public void addPage(WizardPage wizardPage) {
		content.getChildren().add(content.getChildren().size(), wizardPage);
		StackPane.setMargin(wizardPage, new Insets(5.0));
		backButton.setVisible(content.getChildren().size() > 1);
		nextButton.setVisible(content.getChildren().size() > 1);
		if (currentPage == null) {
			currentPage = (WizardPage) content.getChildren().get(0);
		}
	}
	
	public void setTitle(String title) {
		titleText.setText(title);
	}
	
	public void setMessage(String message) {
		messageText.setText(message);
	}
	
	public void performFinish() {
    }

    public void perfomCancel() {
    }
}
