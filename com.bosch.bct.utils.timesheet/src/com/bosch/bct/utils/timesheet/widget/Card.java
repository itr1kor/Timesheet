package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.bosch.bct.utils.timesheet.model.Task;


public abstract class Card extends Canvas implements PaintListener{
	
	Task task;
	Color color;

	public Card(Composite parent, int style, Task initTask, Color initColor) {
		super(parent, style);
		addPaintListener(this);
		task = initTask;
		color = initColor;
		
		if(parent instanceof Deck){
			((Deck)parent).addCard(this);
		}
	}	
	
	@Override
	public abstract void paintControl(PaintEvent paintEvent);
	
	
	@Override
	public void dispose() {
		super.dispose();
		color.dispose();
	}
	
	public Task getTask() {
		return task;
	}
}