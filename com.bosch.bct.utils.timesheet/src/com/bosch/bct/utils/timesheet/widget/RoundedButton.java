package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.bosch.bct.utils.timesheet.dialog.CreateTaskDialog;
import com.bosch.bct.utils.timesheet.listener.DialogCloseListener;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;

public class RoundedButton extends Canvas implements PaintListener, MouseListener{

	private TaskManager taskManager;
	private CreateTaskDialog createTaskDialog;
	private DeckViewer deckViewer;

	public RoundedButton(Composite parent, int style, TaskManager taskManager, DeckViewer deckViewer) {
		super(parent, SWT.NONE);
		addPaintListener(this);
		addMouseListener(this);
		this.taskManager = taskManager;
		this.deckViewer = deckViewer;
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

	@Override
	public void mouseDoubleClick(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDown(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseUp(MouseEvent e) {
		createTaskDialog = new CreateTaskDialog(getShell(), taskManager);
		if(createTaskDialog.open() == IDialogConstants.OK_ID){
			deckViewer.refresh();
		}
	}
	
	public CreateTaskDialog getCreateTaskDialog() {
		return createTaskDialog;
	}
	
	public void addDialogCloseListener(DialogCloseListener listener) {
		checkWidget ();
		if (listener == null) SWT.error(SWT.ERROR_NULL_ARGUMENT);
		addListener(SWT.Close, listener);
	}
}
