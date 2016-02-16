package com.bosch.utils.timesheet.widgets;

import java.util.List;

import com.bosch.utils.timesheet.model.Task;
import com.bosch.utils.timesheet.model.TaskManager;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
		 
		 ObservableList<Task> tasks = TaskManager.getInstance().tasks();
		 tasks.addListener(new ListChangeListener<Task>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Task> change) {
				
				while (change.next()) {
					List<? extends Task> addedObjects = change.getAddedSubList();
					for (Task task : addedObjects) {
						MappingTaskCard taskCard = new MappingTaskCard(task);
						recyclerView.addChild(taskCard);
					}
				}
			}
		});
	}
	
	public DayMappingRecyclerView getRecyclerView() {
		return recyclerView;
	}
}
