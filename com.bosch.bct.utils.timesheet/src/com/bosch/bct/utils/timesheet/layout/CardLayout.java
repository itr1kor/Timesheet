package com.bosch.bct.utils.timesheet.layout;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class CardLayout extends Layout {

	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {

		Rectangle clientArea = composite.getClientArea();
		Control[] children = composite.getChildren();
		
		for (int i = 0; i < children.length; i++) {
			
			Control card = children[i];
			
//			card.setBounds(5 * i + card., 5, clientArea.width - 10, height);
		}
	}
	
	
	private Point layout(Composite composite){
		return null;
	}

}
