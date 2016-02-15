package com.bosch.utils.timesheet.widgets;

import com.bosch.utils.timesheet.model.Task;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MappingTaskCard extends VBox {

	private static String[] colors = {"FFFF7055", "DDFF7055", "99FF7055", "55FF0055", "00FF0055"};

	double orgSceneX;
	double orgSceneY;
	double orgTranslateX;
	double orgTranslateY;

	public MappingTaskCard(Task task) {
		super(5);
		setFillWidth(true);
		setAlignment(Pos.CENTER_LEFT);
		setTranslateX(1000);
		String backgroundColor = colors[task.getTaskType().ordinal()];
		System.out.println(backgroundColor);
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

		setOnDragDetected(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				Dragboard db = startDragAndDrop(TransferMode.COPY);
				ClipboardContent content = new ClipboardContent();
				content.putString(taskName.getText() + ":" + taskType.getText());
				MappingTaskCard.this.setEffect(null);
				db.setContent(content);
				event.consume();
			}
		});
	}
	
	
	
//	EventHandler<MouseEvent> circleOnMousePressedEventHandler = 
//			new EventHandler<MouseEvent>() {
//
//
//
//		@Override
//		public void handle(MouseEvent t) {
//			orgSceneX = t.getSceneX();
//			orgSceneY = t.getSceneY();
//			orgTranslateX = ((MappingTaskCard)(t.getSource())).getTranslateX();
//			orgTranslateY = ((MappingTaskCard)(t.getSource())).getTranslateY();
//		}
//	};
//
//	EventHandler<MouseEvent> circleOnMouseDraggedEventHandler = 
//			new EventHandler<MouseEvent>() {
//
//		@Override
//		public void handle(MouseEvent t) {
//			double offsetX = t.getSceneX() - orgSceneX;
//			double offsetY = t.getSceneY() - orgSceneY;
//			double newTranslateX = orgTranslateX + offsetX;
//			double newTranslateY = orgTranslateY + offsetY;
//			((MappingTaskCard)(t.getSource())).setTranslateX(newTranslateX);
//			((MappingTaskCard)(t.getSource())).setTranslateY(newTranslateY);
//		}
//	};
//	
//	setOnMousePressed(circleOnMousePressedEventHandler);
//    setOnMouseDragged(circleOnMouseDraggedEventHandler);

}
