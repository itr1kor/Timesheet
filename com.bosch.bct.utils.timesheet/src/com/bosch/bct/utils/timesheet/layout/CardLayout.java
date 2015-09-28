package com.bosch.bct.utils.timesheet.layout;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;

public class CardLayout extends Layout {

	/** Horizontal gaps between items. */
	public static final int GAP = 5;
	private int widthHint;
	private int heightHint;
	private boolean flushRequired;
	
	@Override
	protected Point computeSize(Composite composite, int wHint, int hHint, boolean flushCache) {
		Control children[] = composite.getChildren();
		int maxWidth = 0;
		int maxHeight = 0;
		int totalHeight = 0;
		
		widthHint = wHint;
		heightHint = hHint;
		flushRequired = flushCache;
		
		for (int i = 0; i < children.length; i++) {
			Point size = children[i].computeSize(wHint, hHint, flushCache);
			maxWidth = Math.max(size.x, maxWidth);
			maxHeight = Math.max(size.y, maxHeight);
			totalHeight += maxHeight + GAP;
		}
		return new Point(maxWidth , totalHeight);
	}
	
	@Override
	protected boolean flushCache(Control control) {
		return true;
	}

	@Override
	protected void layout(Composite composite, boolean flushCache) {
		Rectangle clientArea = composite.getClientArea();
		Control[] children = composite.getChildren();
		for (int i = 0; i < children.length; i++) {
			Control card = children[i];
			Point size = card.computeSize(widthHint, heightHint, flushRequired);
			card.setBounds(GAP, (GAP + size.y) * i, clientArea.width, size.y);
		}
	}
}
