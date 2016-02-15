package com.bosch.utils.timesheet.widgets;

import java.util.Map;

import com.bosch.utils.timesheet.model.Task;
import com.bosch.utils.timesheet.model.TaskManager;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MappedTaskCard extends VBox {

	private static String[] colors = {"FFFF7055", "DDFF7055", "99FF7055", "55FF0055", "00FF0055"};
	private TextField textField;	
	
	public MappedTaskCard(Task task, final Double effort) {
		super(5);
		setFillWidth(true);
		setAlignment(Pos.CENTER_LEFT);
		setTranslateX(1000);
		setUserData(task);
		String backgroundColor = colors[task.getTaskType().ordinal()];
		setStyle("-fx-border-color: #FFD278; -fx-border-radius: 5; -fx-background-radius: 5;"
				+ "-fx-padding: 5px; -fx-background-color: #" + backgroundColor +";");

		final Text taskName = new Text(task.getName());
		taskName.setFont(Font.font("Verdana", FontWeight.BOLD, 21));
		taskName.setStyle("-fx-padding: 5px;");
		getChildren().add(taskName);

		final Text taskType = new Text(task.getTaskType().name());
		taskType.setFont(Font.font("times new roman", FontWeight.LIGHT, 14));
		taskType.setStyle("-fx-padding: 5px;");
		getChildren().add(taskType);
		
		textField = new TextField();
		textField.setText(String.valueOf(effort));
		textField.setMinHeight(25);
		getChildren().add(textField);
		
		setOnDragDetected(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(taskName.getText() + ":" + taskType.getText() + ":" + String.valueOf(textField.getText()));
				db.setContent(content);
				event.consume();
			}
		});
		
		setOnDragDone(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				DayMappedRecyclerView parent = (DayMappedRecyclerView) getParent().getParent().getParent().getParent();
				parent.removeChild(MappedTaskCard.this);
//				TaskManager.getInstance(). TODO remove from model
				Map<Task, Double> mapping = TaskManager.getInstance().getMapping(parent.getDay());
				mapping.remove(getUserData());
			}
		});
	}

	public TextField getTextField() {
		return textField;
	}
}
