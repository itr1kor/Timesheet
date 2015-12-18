package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;

public class MappedCard extends Card{

	private Text textWidget;
	private Font font;
	
	private int colorLineHeigth = 5;

	public MappedCard(Composite parent, int style, Task task, Color color, DeckViewer deckViewer) {
		super(parent, style, task, color, deckViewer);
	}	

	@Override
	public void paintControl(PaintEvent paintEvent) {if(selection){
		color = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
	} else {
		color = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	}
	
	GC gc = paintEvent.gc;
//	font = new Font(getDisplay(), new FontData("Ariel", 9, SWT.BOLD));
//	gc.setFont(font);
	int gcFontHeight = gc.getFontMetrics().getHeight();

	int cardHeight = gcFontHeight + colorLineHeigth*2 + 50;
	
	Composite parent = getParent();
	Rectangle clientArea = parent.getClientArea();
	FillLayout layout = (FillLayout) parent.getLayout();
	
	gc.setBackground(color);
	gc.fillRoundRectangle(0, 0, clientArea.width - (layout.marginWidth * 2), cardHeight, 15, 15);
	gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_WHITE));
	gc.fillRoundRectangle(colorLineHeigth, colorLineHeigth, clientArea.width - (colorLineHeigth*2) - (layout.marginWidth * 2), cardHeight - (colorLineHeigth*2), 7, 7);
	
//	paintEvent.display.getSystemFont();
	
	String taskType = getTask().getTaskType().name(); 
	Point taskTypeSize = gc.stringExtent(taskType); 
	int taskTypeWidth  = taskTypeSize.x;

	String taskName = getTask().getName(); 

	//vertical line
	gc.drawLine(5, gcFontHeight + 5, clientArea.width - (colorLineHeigth*2) - (layout.marginWidth * 2), gcFontHeight + 5);
	
	gc.drawText(taskName, 10, 5); 
	gc.drawText(taskType, clientArea.width - (colorLineHeigth*2) - taskTypeWidth - (layout.marginWidth * 2), 5); 
	
	gc.drawLine(clientArea.width - 20 - taskTypeWidth - (layout.marginWidth * 2), 5, clientArea.width - 20 - taskTypeWidth - (layout.marginWidth * 2), gcFontHeight + 5);
	
	if(textWidget == null){
		textWidget = new Text(this, SWT.BORDER);
	}
	
	String effortText = "EFFORT :";
	gc.drawText(effortText, 20, gcFontHeight + 15); 
	int effortTextLength = gc.stringExtent(effortText).x;
	textWidget.setBounds(20 + effortTextLength + 5, gcFontHeight + 15, clientArea.width - 45 - effortTextLength - (layout.marginWidth * 2), cardHeight - 40 - gcFontHeight);}
	
	
	@Override
	public void dispose() {
		font.dispose();
		super.dispose();
	}
	
	@Override
	public Point computeSize(final int wHint, final int hHint, final boolean changed) {
		Composite parent = getParent();
		Rectangle clientArea = parent.getClientArea();
		FillLayout layout = (FillLayout) parent.getLayout();
		return new Point(clientArea.width - (layout.marginWidth * 2) + 15, 75);
	}
}
