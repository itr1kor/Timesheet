package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class Card extends Canvas implements PaintListener{

	private Text textWidget;

	public Card(Composite parent, int style) {
		super(parent, style);
		addPaintListener(this);
	}	

	@Override
	public void paintControl(PaintEvent paintEvent) {
		
		GC gc = paintEvent.gc;
		Composite parent = getParent();
		Rectangle clientArea = parent.getClientArea();
		
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_GRAY));
		gc.fillRectangle(clientArea.x + 10, clientArea.y + 10, clientArea.width - 20, clientArea.height - 20);
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_WHITE));
		gc.fillRoundRectangle(clientArea.x + 15, clientArea.y + 15, clientArea.width - 30, clientArea.height - 30, 7, 7);
		
		Font font = new Font(getDisplay(), new FontData("Ariel", 100, SWT.BOLD));
		gc.setFont(font);
		
		String taskType = "CODING"; 
		Point taskTypeSize = gc.stringExtent(taskType); 
		int gcFontHeight = gc.getFontMetrics().getHeight();
		int taskTypeWidth  = taskTypeSize.x;

		String taskName = "BIOSX-2874"; 

		gc.drawLine(clientArea.x + 15, clientArea.y + gcFontHeight + 15, clientArea.width - 15, clientArea.y + gcFontHeight + 15);
		
		gc.drawText(taskName, 20, 15); 
		gc.drawText(taskType, clientArea.width - 20 - taskTypeWidth, 15); 
		
		gc.drawLine(clientArea.width - 30 - taskTypeWidth, clientArea.y + 15, clientArea.width - 30 - taskTypeWidth, clientArea.y + gcFontHeight + 15);
		
		if(textWidget == null){
			textWidget = new Text(this, SWT.BORDER);
		}
		
		String effortText = "EFFORT :";
		gc.drawText(effortText, 20, clientArea.y + gcFontHeight + 25); 
		int effortTextLength = gc.stringExtent(effortText).x;
		textWidget.setBounds(30 + effortTextLength, clientArea.y + gcFontHeight + 25, clientArea.width - 55 - effortTextLength, clientArea.height - 50 - gcFontHeight);
	}
}
