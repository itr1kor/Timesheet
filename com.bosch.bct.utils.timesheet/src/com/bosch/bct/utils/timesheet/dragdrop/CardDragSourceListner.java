package com.bosch.bct.utils.timesheet.dragdrop;

/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

import com.bosch.bct.utils.timesheet.viewer.DeckViewer;


/**
 * @author PPA7KOR
 */
public class CardDragSourceListner implements DragSourceListener {

  DeckViewer deckViewer;

  /**
   * @param deckViewer
   */
  public CardDragSourceListner(final DeckViewer deckViewer) {
    this.deckViewer = deckViewer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dragStart(final DragSourceEvent event) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dragSetData(final DragSourceEvent event) {

    ISelection selection = this.deckViewer.getSelection();
    if (selection instanceof IStructuredSelection) {
      IStructuredSelection treeSelection = (IStructuredSelection) selection;
      event.data = treeSelection.getFirstElement();
    }
    else {
      event.doit = false;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dragFinished(final DragSourceEvent event) {
  }
}
