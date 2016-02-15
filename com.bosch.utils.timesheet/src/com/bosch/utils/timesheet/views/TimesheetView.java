package com.bosch.utils.timesheet.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.bosch.utils.timesheet.model.Day;
import com.bosch.utils.timesheet.model.Task;
import com.bosch.utils.timesheet.model.TaskManager;
import com.bosch.utils.timesheet.model.TaskType;
import com.bosch.utils.timesheet.widgets.DayActivity;
import com.bosch.utils.timesheet.widgets.DayMappedRecyclerView;
import com.bosch.utils.timesheet.widgets.MappedTaskCard;
import com.bosch.utils.timesheet.widgets.TaskListActivity;

import javafx.embed.swt.FXCanvas;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TimesheetView extends ViewPart {

	TaskManager taskManager = TaskManager.getInstance();
	public TimesheetView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		//TODO remove this code while deploying
		if (taskManager.tasks().isEmpty()) {
			createExample();
		}
		parent.setLayout(new GridLayout(1, false));
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		firstLevelLayout(parent);
	}
	
	private void firstLevelLayout(Composite parent) {

		HBox content = new HBox(10);
		content.setFillHeight(true);
		
		TaskListActivity taskListActivity = new TaskListActivity(taskManager) ;
//		final DayMappingRecyclerView leftRecyclerView = taskListActivity.getRecyclerView();
		content.getChildren().add(taskListActivity);
		taskListActivity.prefWidthProperty().bind(content.widthProperty());
		taskListActivity.prefHeightProperty().bind(content.heightProperty());
		
		Day[] days = {Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY , Day.FRIDAY};
		EventHandler<MouseEvent> eventListener = createMouseClickListener();
		
		for (int i = 0; i < days.length; i++) {
			final DayActivity dayActivity = new DayActivity(taskManager, days[i]);
			DayMappedRecyclerView recyclerView = dayActivity.getRecyclerView();
			recyclerView.addMouseClickListener(eventListener);
			content.getChildren().add(dayActivity);
			dayActivity.prefWidthProperty().bind(content.widthProperty());
			dayActivity.prefHeightProperty().bind(content.heightProperty());
		}
		
		FXCanvas canvas = new FXCanvas(parent, SWT.NONE);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		Scene scene = new Scene(content);
		content.prefWidthProperty().bind(scene.widthProperty());
		content.prefHeightProperty().bind(scene.heightProperty());
		canvas.setScene(scene);
	}
		
		

	private void createExample() {
		Task req_123 = new Task("123", TaskType.REQUIREMENT);
		taskManager.mapTask(Day.MONDAY, req_123, 5.0);
		taskManager.addTask(req_123);
		Task des_123 = new Task("123", TaskType.DESIGN);
		taskManager.mapTask(Day.MONDAY, des_123, 5.0);
		taskManager.addTask(des_123);
		taskManager.addTask(new Task("123", TaskType.CODING));
		taskManager.addTask(new Task("123", TaskType.TESTING));
		
		Task req_456 = new Task("456", TaskType.REQUIREMENT);
		taskManager.mapTask(Day.TUESDAY, req_456, 4.0);
		taskManager.addTask(req_456);
		taskManager.addTask(new Task("456", TaskType.DESIGN));
		taskManager.addTask(new Task("456", TaskType.CODING));
		taskManager.addTask(new Task("456", TaskType.TESTING));
		
		
		Task req_789 = new Task("789", TaskType.REVIEW);
		taskManager.mapTask(Day.WEDNESDAY, req_789, 4.0);
		taskManager.addTask(req_789);
		taskManager.addTask(new Task("789", TaskType.DESIGN));
		taskManager.addTask(new Task("789", TaskType.CODING));
		taskManager.addTask(new Task("789", TaskType.REQUIREMENT));
		
		taskManager.addTask(new Task("1011", TaskType.REQUIREMENT));
		taskManager.addTask(new Task("1011", TaskType.DESIGN));
		taskManager.addTask(new Task("1011", TaskType.CODING));
		taskManager.addTask(new Task("1011", TaskType.TESTING));
	}
	
	@Override
	public void setFocus() {

	}
	
	private EventHandler<MouseEvent> createMouseClickListener() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//TODO support unselect cards from other dayActivity.
				VBox contentView = (VBox) event.getSource();
				for (Node node : contentView.getChildren()) {
					node.setEffect(null);
				}
				EventTarget target = event.getTarget();
				if(target instanceof MappedTaskCard) {
					((MappedTaskCard)target).setEffect(new Lighting());
				} else if (target instanceof Text) {
					MappedTaskCard card = (MappedTaskCard) ((Text) target).getParent();
					card.setEffect(new Lighting());
				} else if (target instanceof TextField) {
					MappedTaskCard card = (MappedTaskCard) ((TextField) target).getParent();
					card.setEffect(new Lighting());
				}
			}
		};
	}

}