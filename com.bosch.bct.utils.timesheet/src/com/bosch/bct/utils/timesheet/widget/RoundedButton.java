package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class RoundedButton extends Canvas implements PaintListener{

	public RoundedButton(Composite parent, int style) {
		super(parent, SWT.BORDER);
		addPaintListener(this);
	}	

	@Override
	public void paintControl(PaintEvent paintEvent) {
		
		GC gc = paintEvent.gc;
		Composite parent = getParent();
		Rectangle clientArea = parent.getClientArea();
		
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_RED));
		
		int radius = (clientArea.width > clientArea.height) ?  clientArea.height : clientArea.width;
		gc.fillOval(0, 0, radius/2, radius/2);
		
		gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_BLACK));
		
		gc.drawLine(radius/4, radius/8, radius/4, radius/3);
		gc.drawLine(radius/8, radius/4, radius/3, radius/4);
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
	}
}
