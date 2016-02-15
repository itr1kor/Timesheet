package com.bosch.utils.timesheet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		HBox content = new HBox();
		content.setFillHeight(true);
		
//		final RecyclerView leftRecyclerView = new RecyclerView();
//		content.getChildren().add(leftRecyclerView);
		
//		final DayMappingRecyclerView rightRecyclerView = new DayMappingRecyclerView(Day.MONDAY);
//		content.getChildren().add(rightRecyclerView);
//		
//      	MappingTaskCard taskCard = new MappingTaskCard(new Task("Task 123456", TaskType.REQUIREMENT)); 
//		leftRecyclerView.addChild(taskCard);
//		taskCard = new MappingTaskCard(new Task("Task 123456", TaskType.DESIGN)); 
//		leftRecyclerView.addChild(taskCard);
//		taskCard = new MappingTaskCard(new Task("Task 123456", TaskType.CODING)); 
//		leftRecyclerView.addChild(taskCard);
//		taskCard = new MappingTaskCard(new Task("Task 123456", TaskType.TESTING)); 
//		leftRecyclerView.addChild(taskCard);
//		taskCard = new MappingTaskCard(new Task("Task 123456", TaskType.REVIEW)); 
//		leftRecyclerView.addChild(taskCard);
		
		
		stage.setScene(new Scene(content, 700, 300));
//		grid = new GridPane();
//		grid.setPadding(new Insets(10));
//		grid.setStyle("-fx-border-color: #FFD278; -fx-border-radius: 5; -fx-background-radius: 5;  "
//				+ "-fx-background-color: #FFD27870; -fx-padding: 5px;");
//		taskRecyclerView.getChildren().add(grid);
//		GridPane.setMargin(grid, new Insets(5, 10, 5, 10));

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
