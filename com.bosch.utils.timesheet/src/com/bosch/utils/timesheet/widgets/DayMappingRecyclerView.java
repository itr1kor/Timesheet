package com.bosch.utils.timesheet.widgets;

import com.bosch.utils.timesheet.model.Task;
import com.bosch.utils.timesheet.model.TaskManager;import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DayMappingRecyclerView extends ScrollPane {

	private VBox contentView;

	public DayMappingRecyclerView(TaskManager taskManager) {
		
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
		for (Task task : taskManager.tasks()) {
			MappingTaskCard taskCard = new MappingTaskCard(task);
			addChild(taskCard);

			TranslateTransition transition = new TranslateTransition(Duration.millis(500));
			transition.setAutoReverse(false);
			transition.setNode(taskCard);
			transition.setToX(0);
			transition.setCycleCount(1);
			transition.setInterpolator(Interpolator.LINEAR);
			transition.setDelay(Duration.millis(index++ * 100));
			transition.playFromStart();
		}
		
		contentView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				for (Node node : contentView.getChildren()) {
					node.setEffect(null);
				}
				EventTarget target = event.getTarget();
				if(target instanceof MappingTaskCard) {
					((MappingTaskCard)target).setEffect(new Lighting());
				} else if (target instanceof Text) {
					MappingTaskCard card = (MappingTaskCard) ((Text) target).getParent();
					card.setEffect(new Lighting());
				}
			}
		});
	}
	
	public void addChild(Node node) {
		contentView.getChildren().add(node);
	}
	
}
