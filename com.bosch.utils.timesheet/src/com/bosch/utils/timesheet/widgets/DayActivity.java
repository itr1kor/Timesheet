package com.bosch.utils.timesheet.widgets;

import com.bosch.utils.timesheet.model.Day;
import com.bosch.utils.timesheet.model.TaskManager;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class DayActivity extends VBox {

	private DayMappedRecyclerView recyclerView;

	public DayActivity(TaskManager taskManager, Day day) {
		setFillWidth(true);
		
		Text header = new Text(day.name());
		getChildren().add(header);
		header.setFill(Color.TOMATO);
		header.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 24));
		header.setStyle("-fx-text-alignment:center");
		VBox.getVgrow(header);
		
		recyclerView = new DayMappedRecyclerView(taskManager, day);
		getChildren().add(recyclerView);
		recyclerView.prefWidthProperty().bind(widthProperty());
		recyclerView.prefHeightProperty().bind(heightProperty());
		
		Text footer = new Text("10.0");
		getChildren().add(footer);
	}
	
	public DayMappedRecyclerView getRecyclerView() {
		return recyclerView;
	}
}
