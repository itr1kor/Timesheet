/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.bct.utils.timesheet.dragdrop;

import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TransferData;

import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;
import com.bosch.bct.utils.timesheet.widget.Deck;


/**
 * @author PPA7KOR
 */
public class CardDropListner extends ViewerDropAdapter {


  /**
   * @param deckViewer
   */
  public CardDropListner(final DeckViewer deckViewer) {
    super(deckViewer);
    this.deckViewer = deckViewer;
  }

  DeckViewer deckViewer;

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean validateDrop(final Object target, final int operation, final TransferData transferType) {
    return (CardTransfer.getInstance().isSupportedType(transferType));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean performDrop(final Object data) {
    Task task = (Task) data;
    System.out.println(task.getName());
    System.out.println(task.getTaskType());
    // this.droppedGroup.addViewDescriptors(viewDescriptor);
    final Deck deck = (Deck)deckViewer.getControl();
	task.addTaskMapping(deck.day, 0.0);
    this.deckViewer.refresh();
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void drop(final DropTargetEvent event) {
    performDrop(event.data);
  }
}
