package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.bosch.bct.utils.timesheet.dragdrop.CardDragSourceListner;
import com.bosch.bct.utils.timesheet.dragdrop.CardTransfer;
import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;


public abstract class Card extends Canvas implements PaintListener, MouseListener, DragDetectListener {
	
	Task task;
	Color color;
	public boolean selection;
	public boolean isdragged;

	public Card(Composite parent, int style, Task initTask, Color initColor, DeckViewer deckViewer) {
		super(parent, /*SWT.BORDER |*/ style);
		addPaintListener(this);
		addMouseListener(this);
		addDragDetectListener(this);
		task = initTask;
		color = initColor;
		if(parent instanceof Deck){
			((Deck)parent).addCard(this);
		}

		//TODO uncomment below code in production phase
		setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));

		final DragSource dragSource = new DragSource(this, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK);
		dragSource.setTransfer(new Transfer[] { CardTransfer.getInstance() });
		dragSource.addDragListener(new CardDragSourceListner(deckViewer));
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
	
	public void setTask(Task task){
		this.task = task;
	}
	
	@Override
	public void dragDetected(DragDetectEvent e) {
		selection = !selection;
		((Deck)getParent()).setSingleSelection(this);
		return;
	}
	
	@Override
	public void mouseDoubleClick(MouseEvent e){}

	@Override
	public void mouseDown(MouseEvent event){
	}

	@Override
	public void mouseUp(MouseEvent event){
		selection = !selection;
		((Deck)getParent()).setSingleSelection(this);
		return;
	}
	
	public boolean getSelection() {
		return selection;
	}

	public void setSelection(boolean selection) {
		this.selection = selection;
		redraw();
		update();
	}
	
//	@Override
//	public Point computeSize(final int wHint, final int hHint, final boolean changed) {
//		int width = 0, height = 0;
//		for (final BreadcrumbItem item : this.items) {
//			width += item.getWidth();
//			height = Math.max(height, item.getHeight());
//		}
//		return new Point(Math.max(width, wHint), Math.max(height, hHint));
//	}
}