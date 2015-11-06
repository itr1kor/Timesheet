package com.bosch.bct.utils.timesheet.widget;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.bosch.bct.utils.timesheet.listener.DialogCloseListener;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;

public abstract class RoundedButton extends Canvas implements PaintListener, MouseListener {

  protected final TaskManager taskManager;

  protected final DeckViewer deckViewer;

  public RoundedButton(final Composite parent, final int style, final TaskManager taskManager,
      final DeckViewer deckViewer) {
    super(parent, SWT.NONE);
    addPaintListener(this);
    addMouseListener(this);
    this.taskManager = taskManager;
    this.deckViewer = deckViewer;
  }


  @Override
  public abstract void paintControl(final PaintEvent paintEvent);


  @Override
  public void dispose() {
    super.dispose();
  }

  @Override
  public void mouseDoubleClick(final MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public void mouseDown(final MouseEvent e) {
    // TODO Auto-generated method stub
  }

  @Override
  public abstract void mouseUp(final MouseEvent e);


  public void addDialogCloseListener(final DialogCloseListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(SWT.ERROR_NULL_ARGUMENT);
    }
    addListener(SWT.Close, listener);
  }
}
