/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.bct.utils.timesheet.widget;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;

import com.bosch.bct.utils.timesheet.dialog.CreateTaskDialog;
import com.bosch.bct.utils.timesheet.listener.DialogCloseListener;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;


/**
 * @author she5cob
 */
public class AddRoundedButton extends RoundedButton {

  public AddRoundedButton(final Composite parent, final int style, final TaskManager taskManager,
      final DeckViewer deckViewer) {
    super(parent, SWT.NONE, taskManager, deckViewer);
  }

  @Override
  public void paintControl(final PaintEvent paintEvent) {

    GC gc = paintEvent.gc;
    Composite parent = getParent();
    Rectangle clientArea = parent.getClientArea();

    gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_GREEN));

    int radius = (clientArea.width > clientArea.height) ? clientArea.height : clientArea.width;
    gc.fillOval(0, 0, radius / 2, radius / 2);

    gc.setBackground(paintEvent.display.getSystemColor(SWT.COLOR_BLACK));

    gc.drawLine(radius / 4, radius / 8, radius / 4, radius / 3);
    gc.drawLine(radius / 8, radius / 4, radius / 3, radius / 4);
  }

  @Override
  public void mouseUp(final MouseEvent e) {
    CreateTaskDialog createTaskDialog = new CreateTaskDialog(getShell(), this.taskManager);
    if (createTaskDialog.open() == IDialogConstants.OK_ID) {
      this.deckViewer.refresh();
    }
  }


  @Override
  public void addDialogCloseListener(final DialogCloseListener listener) {
    checkWidget();
    if (listener == null) {
      SWT.error(SWT.ERROR_NULL_ARGUMENT);
    }
    addListener(SWT.Close, listener);
  }
}
