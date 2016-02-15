package com.bosch.utils.timesheet.widgets;

import java.util.Map;

import com.bosch.utils.timesheet.model.Day;
import com.bosch.utils.timesheet.model.Task;
import com.bosch.utils.timesheet.model.TaskManager;
import com.bosch.utils.timesheet.model.TaskType;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class DayMappedRecyclerView extends ScrollPane {

	private VBox contentView;
	private Map<Task, Double> mapping;
	private Day thisDay;

	public DayMappedRecyclerView(final TaskManager taskManager, Day day) {
		
		thisDay = day;
		
		setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		
		contentView = new VBox(10);
		contentView.setFillWidth(true);
		contentView.setAlignment(Pos.TOP_CENTER);
		contentView.setPadding(new Insets(10, 10, 10, 10));
		
		setFitToWidth(true);
		setFitToHeight(true);
		setContent(contentView);
		
		int index = 0;
		mapping = taskManager.getMapping(day);
		for (Task task : mapping.keySet()) {
			MappedTaskCard card = createMappedTaskCard(task, mapping.get(task));
			
			TranslateTransition transition = new TranslateTransition(Duration.millis(500));
			transition.setAutoReverse(false);
			transition.setNode(card);
			transition.setToX(0);
			transition.setCycleCount(1);
			transition.setInterpolator(Interpolator.LINEAR);
			transition.setAutoReverse(true);
			transition.setDelay(Duration.millis(index++ * 100));
			transition.playFromStart();
		}
		
        contentView.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasString()) {
                    String[] taskString = db.getString().split(":");
                    Task dummyTask = new Task(taskString[0], TaskType.valueOf(taskString[1]));
                    double effort = 0;
                    if(taskString.length == 3) {
                    	effort = Double.parseDouble(taskString[2]);
                    	//TODO remove from previous day
                    }
                    
                    Task actualtask = returnActualTaskObject(dummyTask);
                    if (mapping.containsKey(actualtask)) {
                    	for (Node node : contentView.getChildren()) {
							if(node.getUserData() == actualtask) {
								MappedTaskCard taskCard = (MappedTaskCard) node;
								TextField textField = taskCard.getTextField();
								effort += mapping.get(actualtask);
								textField.setText(String.valueOf(effort));
							}
						}
					} else {
						MappedTaskCard card = createMappedTaskCard(actualtask, effort);
						card.setTranslateX(0);
					}
					mapping.put(actualtask, effort);
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
            }

			private Task returnActualTaskObject(Task dummyTask) {
				for (Task task : taskManager.tasks()) {
					if(task.equals(dummyTask)){
						return task;
					}
				}
				return null;
			}
		});
        
        contentView.setOnDragOver(new EventHandler <DragEvent>() {

        	@Override
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.COPY);
			}
		});
	}

	private MappedTaskCard createMappedTaskCard(final Task task, double effort) {
		MappedTaskCard taskCard = new MappedTaskCard(task, effort); 
		final TextField textField = taskCard.getTextField();
		addChild(taskCard);
		textField.textProperty().addListener(new ChangeListener<String>() {
		    @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (newValue.matches("\\d+(\\.\\d+)?")) {
		            double value = Double.parseDouble(newValue);
		            textField.setText(newValue);
		            TaskManager.getInstance().getMapping(thisDay);
		            mapping.put(task, value);
		        } else {
		        	textField.setText(oldValue);
		        }
		    }
		});
		return taskCard;
	}
	
	public void addChild(Node node) {
		contentView.getChildren().add(node);
	}
	
	public void removeChild(Node node) {
		contentView.getChildren().remove(node);
	}
	
	public void addMouseClickListener(EventHandler<MouseEvent> eventHandler) {
		contentView.setOnMouseClicked(eventHandler);
	}
	
	public Day getDay() {
		return thisDay;
	}

}
