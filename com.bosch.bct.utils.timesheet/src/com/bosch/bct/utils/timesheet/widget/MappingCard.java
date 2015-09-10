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

public class MappingCard extends Canvas implements PaintListener{

	private Font font;
	
	private int colorLineWidth = 5;

	public MappingCard(Composite parent, int style) {
		super(parent, style);
		addPaintListener(this);
	}	

	@Override
	public void paintControl(PaintEvent paintEvent) {
		
		GC gc = paintEvent.gc;
		font = new Font(getDisplay(), new FontData("Ariel", 9, SWT.BOLD));
		gc.setFont(font);
		int gcFontHeight = gc.getFontMetrics().getHeight();

		int cardHeight = gcFontHeight + colorLineWidth*2;
		
		Composite parent = getParent();
		Rectangle clientArea = parent.getClientArea();
		
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_GRAY));
		gc.fillRectangle(0, 0, clientArea.width, cardHeight);
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_WHITE));
		gc.fillRoundRectangle(colorLineWidth, colorLineWidth, clientArea.width - (colorLineWidth*2), cardHeight - (colorLineWidth*2), 7, 7);
		
//		paintEvent.display.getSystemFont();
		String taskType = "CODING"; 
		Point taskTypeSize = gc.stringExtent(taskType); 
		int taskTypeWidth  = taskTypeSize.x;

		String taskName = "BIOSX-2874"; 

		//vertical line
//		gc.drawLine(5, gcFontHeight + 5, clientArea.width - (colorLineWidth*2), gcFontHeight + 5);
		
		gc.drawText(taskName, 10, 5); 
		font = new Font(getDisplay(), new FontData("Ariel", 9, SWT.ITALIC));
		gc.drawText(taskType, clientArea.width - (colorLineWidth*2) - taskTypeWidth, 5); 
		
		gc.drawLine(clientArea.width - 20 - taskTypeWidth, 5, clientArea.width - 20 - taskTypeWidth, gcFontHeight + 5);
	}
	
	
	@Override
	public void dispose() {
		font.dispose();
		super.dispose();
	}

}
