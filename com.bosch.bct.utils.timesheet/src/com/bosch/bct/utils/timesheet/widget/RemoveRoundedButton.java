/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

import com.bosch.bct.utils.timesheet.dialog.DeleteTaskDialog;
import com.bosch.bct.utils.timesheet.listener.DialogCloseListener;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;


/**
 * @author she5cob
 */
public class RemoveRoundedButton extends RoundedButton {


  /**
   * @param parent
   * @param style
   * @param taskManager
   * @param deckViewer
   */
  public RemoveRoundedButton(final Composite parent, final int style, final TaskManager taskManager,
      final DeckViewer deckViewer) {
    super(parent, SWT.NONE, taskManager, deckViewer);

  }

  @Override
  public void paintControl(final PaintEvent paintEvent) {

    GC gc = paintEvent.gc;
    Composite parent = getParent();
    Rectangle clientArea = parent.getClientArea();

    gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_RED));

    int radius = (clientArea.width > clientArea.height) ? clientArea.height : clientArea.width;
    gc.fillOval(0, 0, radius / 2, radius / 2);

    gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_BLACK));


    gc.drawLine(radius / 8, radius / 4, radius / 3, radius / 4);
  }

  @Override
  public void mouseUp(final MouseEvent e) {
    DeleteTaskDialog createTaskDialog = new DeleteTaskDialog(getShell(), this.taskManager, this.deckViewer);
    if (createTaskDialog.open() == IDialogConstants.OK_ID) {
      this.deckViewer.refresh();
    }
  }

  /**
   * @param listener
   */
  @Override
  public void addDialogCloseListener(final DialogCloseListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(SWT.ERROR_NULL_ARGUMENT);
    }
    addListener(SWT.Close, listener);
  }
}
