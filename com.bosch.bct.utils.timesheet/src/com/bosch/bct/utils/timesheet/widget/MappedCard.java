package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.bosch.bct.utils.timesheet.model.Task;

public class MappedCard extends Card{

	private Text textWidget;
	private Font font;
	
	private int colorLineWidth = 5;

	public MappedCard(Composite parent, int style, Task task, Color color) {
		super(parent, style, task, color);
	}	

	@Override
	public void paintControl(PaintEvent paintEvent) {
		
		GC gc = paintEvent.gc;
//		font = new Font(getDisplay(), new FontData("Ariel", 9, SWT.BOLD));
//		gc.setFont(font);
		int gcFontHeight = gc.getFontMetrics().getHeight();

		int cardHeight = gcFontHeight + colorLineWidth*2 + 50;
		
		Composite parent = getParent();
		Rectangle clientArea = parent.getClientArea();
		
		gc.setBackground(color);
		gc.fillRectangle(0, 0, clientArea.width, cardHeight);
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_WHITE));
		gc.fillRoundRectangle(colorLineWidth, colorLineWidth, clientArea.width - (colorLineWidth*2), cardHeight - (colorLineWidth*2), 7, 7);
		
//		paintEvent.display.getSystemFont();
		
		String taskType = getTask().getTaskType().name(); 
		Point taskTypeSize = gc.stringExtent(taskType); 
		int taskTypeWidth  = taskTypeSize.x;

		String taskName = getTask().getName(); 

		//vertical line
		gc.drawLine(5, gcFontHeight + 5, clientArea.width - (colorLineWidth*2), gcFontHeight + 5);
		
		gc.drawText(taskName, 10, 5); 
		gc.drawText(taskType, clientArea.width - (colorLineWidth*2) - taskTypeWidth, 5); 
		
		gc.drawLine(clientArea.width - 20 - taskTypeWidth, 5, clientArea.width - 20 - taskTypeWidth, gcFontHeight + 5);
		
		if(textWidget == null){
			textWidget = new Text(this, SWT.BORDER);
		}
		
		String effortText = "EFFORT :";
		gc.drawText(effortText, 20, gcFontHeight + 15); 
		int effortTextLength = gc.stringExtent(effortText).x;
		textWidget.setBounds(20 + effortTextLength + 5, gcFontHeight + 15, clientArea.width - 45 - effortTextLength, cardHeight - 40 - gcFontHeight);
	}
	
	
	@Override
	public void dispose() {
		font.dispose();
		super.dispose();
	}
}
