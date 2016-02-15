package com.bosch.utils.timesheet.widgets;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FloatingPointButton extends Circle {

	public FloatingPointButton(double radius) {
		super(radius);
		setFill(Color.TOMATO);
//		Circle circle = new Circle();
//		circle.setFill(Color.TOMATO);
//		Text text = new Text("+");
//		text.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 15));
//		text.setBoundsType(TextBoundsType.VISUAL); 
//		getChildren().addAll(circle, text);
		
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
			}
		});
	}
	
	
	
}
