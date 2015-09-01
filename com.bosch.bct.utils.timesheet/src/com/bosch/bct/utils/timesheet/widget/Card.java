package com.bosch.bct.utils.timesheet.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
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
		
		
//		paintBorder(gc, clientArea);
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_GRAY));
		gc.fillRectangle(clientArea.x + 10, clientArea.y + 10, clientArea.width - 20, clientArea.height - 20);
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_WHITE));
		gc.fillRoundRectangle(clientArea.x + 15, clientArea.y + 15, clientArea.width - 30, clientArea.height - 30, 7, 7);
		
		String taskType = "CODING"; 
		Point taskTypeSize = gc.stringExtent(taskType); 
		int taskTypeHeight = gc.getFontMetrics().getHeight();
		int taskTypeWidth  = taskTypeSize.x;

		String taskName = "BIOSX-2874"; 
		Point taskNameSize = gc.stringExtent(taskName); 
		int taskNameHeight = gc.getFontMetrics().getHeight();
		int taskNameWidth  = taskNameSize.x;

		
		
		gc.drawLine(clientArea.x + 15, clientArea.y + taskTypeHeight + 15, clientArea.width - 15, clientArea.y + taskTypeHeight + 15);
		
		gc.drawText(taskName, 20, 15); 
		gc.drawText(taskType, clientArea.width - 20 - taskTypeWidth, 15); 
		
		gc.drawLine(clientArea.width - 30 - taskTypeWidth, clientArea.y + 15, clientArea.width - 30 - taskTypeWidth, clientArea.y + taskTypeHeight + 15);
		
		if(textWidget == null){
			textWidget = new Text(this, SWT.BORDER);
		}
		
		String effortText = "EFFORT :";
		gc.drawText(effortText, 20, clientArea.y + taskTypeHeight + 25); 
		int effortTextLength = gc.stringExtent(taskType).x;
		textWidget.setBounds(30 + effortTextLength, clientArea.y + taskTypeHeight + 25, clientArea.width - 55 - effortTextLength, clientArea.height - 50 - taskTypeHeight);
	}
	
//	private void paintBorder(GC gc, Rectangle r) {
//		Display disp= getDisplay();
//
//		Color c1 = null;
//		Color c2 = null;
//		
//		int style = getStyle();
//		if ((style & SWT.SHADOW_IN) != 0) {
//			c1 = disp.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);
//			c2 = disp.getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW);
//		}
//		if ((style & SWT.SHADOW_OUT) != 0) {		
//			c1 = disp.getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW);
//			c2 = disp.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW);
//		}
//			
//		if (c1 != null && c2 != null) {
//			gc.setLineWidth(1);
//			drawBevelRect(gc, r.x, r.y, r.width-1, r.height-1, c1, c2);
//		}
//	}
	
//	private void drawBevelRect(GC gc, int x, int y, int w, int h, Color topleft, Color bottomright) {
//		gc.setForeground(bottomright);
//		gc.drawLine(x+w, y,   x+w, y+h);
//		gc.drawLine(x,   y+h, x+w, y+h);
//		
//		gc.setForeground(topleft);
//		gc.drawLine(x, y, x+w-1, y);
//		gc.drawLine(x, y, x,     y+h-1);
//	}
	
	
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell(display);
		
		Card helloWorldTest = new Card(shell, SWT.SHADOW_IN | SWT.SHADOW_OUT);
		helloWorldTest.pack();
		
		shell.pack();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
}
