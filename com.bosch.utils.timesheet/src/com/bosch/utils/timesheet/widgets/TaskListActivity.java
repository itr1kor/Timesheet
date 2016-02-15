package com.bosch.utils.timesheet.widgets;

import com.bosch.utils.timesheet.model.TaskManager;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TaskListActivity extends VBox{

	private DayMappingRecyclerView recyclerView;
	
	
	public TaskListActivity(TaskManager taskManager) {
		setFillWidth(true);
		
		Text header = new Text("Task List");
		getChildren().add(header);
		header.setFill(Color.TOMATO);
		header.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
		header.setStyle("-fx-text-alignment:center");
		VBox.getVgrow(header);
		
		StackPane stackPane = new StackPane();
		getChildren().add(stackPane);
		stackPane.prefWidthProperty().bind(widthProperty());
		stackPane.prefHeightProperty().bind(heightProperty());
		
		recyclerView = new DayMappingRecyclerView(taskManager);
		stackPane.getChildren().add(recyclerView);
		recyclerView.prefWidthProperty().bind(stackPane.widthProperty());
		recyclerView.prefHeightProperty().bind(stackPane.heightProperty());
		StackPane.setAlignment(recyclerView, Pos.TOP_CENTER);
		
		FloatingPointButton floatingPointButton = new FloatingPointButton(20.0);
		stackPane.getChildren().add(floatingPointButton);
		StackPane.setAlignment(floatingPointButton, Pos.BOTTOM_RIGHT);
		
		//TODO check for the visibility of scrollbar
		 StackPane.setMargin(floatingPointButton, new Insets(0, 20.0, 5.0, 0));
	}
	
	public DayMappingRecyclerView getRecyclerView() {
		return recyclerView;
	}
}
