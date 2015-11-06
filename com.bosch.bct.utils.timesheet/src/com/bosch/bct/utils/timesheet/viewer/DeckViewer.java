package com.bosch.bct.utils.timesheet.viewer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

import com.bosch.bct.utils.timesheet.model.Day;
import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.provider.CardContentProvider;
import com.bosch.bct.utils.timesheet.provider.CardLabelProvider;
import com.bosch.bct.utils.timesheet.widget.Card;
import com.bosch.bct.utils.timesheet.widget.Deck;
import com.bosch.bct.utils.timesheet.widget.MappedCard;
import com.bosch.bct.utils.timesheet.widget.MappingCard;

public class DeckViewer extends StructuredViewer{

	private Deck deck;
	private boolean busy;
	private boolean ismapped;
	
	public DeckViewer(Deck deck) {
		super();
		this.deck = deck;
		hookControl(deck);
	}


	public DeckViewer(Deck deck, boolean ismapped) {
		super();
		this.deck = deck;
		this.ismapped = ismapped;
		hookControl(deck);
	}


	class UpdateItemSafeRunnable extends SafeRunnable {
		private Widget widget;

		private Object element;

		UpdateItemSafeRunnable(Widget widget, Object element) {
			this.widget = widget;
			this.element = element;
		}

		@Override
		public void run() {
			doUpdateItem(widget, element);
		}
	}


	@Override
	protected Widget doFindInputItem(Object element) {
		Object root = getRoot();
		if (root == null) {
			return null;
		}

		if (equals(root, element)) {
			return getControl();
		}
		return null;
	}

	@Override
	protected Widget doFindItem(Object element) {
		// compare with root
		Object root = getRoot();
		if (root == null) {
			return null;
		}

		List<Card> cards = getChildren(getControl());
		if (cards != null) {
			for (int i = 0; i < cards.size(); i++) {
				Widget widget = internalFindItem(cards.get(i), element);
				if (widget != null) {
					return widget;
				}
			}
		}
		return null;
	}

	@Override
	protected void doUpdateItem(Widget item, Object element, boolean fullMap) {
		boolean oldBusy = isBusy();
		setBusy(true);
		try {
			if (item instanceof Card) {
				Card card = (Card) item;

				// ensure that back pointer is correct
				if (fullMap) {
					associate(element, card);
				} else {
					Object data = card.getData();
					if (data != null) {
						unmapElement(data, card);
					}
					card.setData(element);
					mapElement(element, card);
				}

				// update icon and label
				SafeRunnable.run(new UpdateItemSafeRunnable(card, element));
			}
		} finally {
			setBusy(oldBusy);
		}		
	}

	@Override
	protected List<Object> getSelectionFromWidget() {
		List<Card> items = getSelection(getControl());
		List<Object> list = new ArrayList<>(items.size());
		for (int i = 0; i < items.size(); i++) {
			Widget item = items.get(i);
			Object e = item.getData();
			if (e != null) {
				list.add(e);
			}
		}
		return list;
	}

	protected List<Card> getSelection(Control widget) {
		return ((Deck) widget).getSelection();
	}

	@Override
	protected void internalRefresh(Object element) {
		internalRefresh(element, false);	
	}

	@Override
	protected void internalRefresh(Object element, boolean updateLabels) {
		// If element is null, do a full refresh.
		if (element == null) {
			internalRefresh(getControl(), getRoot(), updateLabels);
			return;
		}
		Widget[] items = findItems(element);
		if (items.length != 0) {
			for (int i = 0; i < items.length; i++) {
				// pick up structure changes too
				internalRefresh(items[i], element, updateLabels);
			}
		}
	}

	//TODO generalize the logic
	//TODO there is bug here. could you please solve it. refresh is not handled properly. 
	protected void internalRefresh(Widget widget, Object element, boolean updateLabels) {

		if (widget instanceof Card) {
			if (updateLabels || !equals(element, widget.getData())) {
				doUpdateItem(widget, element, true);
			} else {
				associate(element, (Card) widget); 
			}
		}else if(widget instanceof Deck){
			
			Deck deck = (Deck) widget;
			deck.setRedraw(false);
			try{
				Widget[] items = deck.getChildren();
				Object[] children = getSortedChildren(getRoot());
				if (children.length > items.length) {

					for (int i = items.length; i < children.length; i++) {
						createTreeItem(deck, children[i]);
					}
				} else if(children.length < items.length) {

					for (int i = children.length - 1; i < items.length; i++) {
						disassociate(items[i]);
					}
				}
				List<Card> cards = getChildren(deck);
				for (int i = 0; i < children.length; i++) {
					Card item = cards.get(i);
					item.setData(children[i]);
					Object data = item.getData();
					item.setTask((Task) data);
					if (data != null) {
						internalRefresh(item, data, updateLabels);
					}
				}
			} finally {
				deck.setSize(deck.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				deck.layout();
				deck.setRedraw(true);
			}
		}
	}


	protected void associate(Object element, Card item) {
		Object data = item.getData();
		if (data != element) {
			if (data != null) {
				disassociate(item);
			}
			item.setData(element);
			mapElement(element, item);
		} else {
			// Always map the element, even if data == element,
			// since unmapAllElements() can leave the map inconsistent
			// See bug 2741 for details.
			mapElement(element, item);
		}
		//		if (associateListener != null)
		//			associateListener.associate(element, item);
	}

	protected void disassociate(Widget item) {
		//		if (associateListener != null)
		//			associateListener.disassociate(item);
		Object element = item.getData();
		Assert.isNotNull(element);
		//Clear the map before we clear the data
		unmapElement(element, item);
		item.setData(null);
	}


	@Override
	public void reveal(Object element) {
		Widget w = doFindInputItem(element);
		if (w instanceof Card) {
			showItem((Card) w);
		}
	}

	@Override
	protected void setSelectionToWidget(List list, boolean reveal) {
		if (list == null) {
			setSelection(new ArrayList<Widget>(0));
			return;
		}
		int size = list.size();
		List<Widget> newSelection = new ArrayList<Widget>(size);
		for (int i = 0; i < size; ++i) {
			Object elementOrTreePath = list.get(i);
			// Use internalExpand since item may not yet be created. See
			// 1G6B1AR.
			Widget widget = doFindInputItem(elementOrTreePath);
			if (widget instanceof Card) {
				newSelection.add(widget);
			}
		}
		setSelection(newSelection);

		// Although setting the selection in the control should reveal it,
		// setSelection may be a no-op if the selection is unchanged,
		// so explicitly reveal items in the selection here.
		// See bug 100565 for more details.
		if (reveal && newSelection.size() > 0) {
			// Iterate backwards so the first item in the list
			// is the one guaranteed to be visible
			for (int i = (newSelection.size()-1); i >= 0; i--) {
				showItem((Card) newSelection.get(i));
			}
		}
	}

	protected void showItem(Card item) {
		deck.showItem((Card) item);
	}

	protected void setSelection(List<Widget> items) {
		List<Card> selection = new ArrayList<>(items.size());
		for (Widget widget : items) {
			selection.add((Card) widget);
		}
		deck.setSelection(selection);
	}

	@Override
	public Control getControl() {
		return deck;
	}


	@Override
	protected void inputChanged(Object input, Object oldInput) {
		preservingSelection(new Runnable() {
			@Override
			public void run() {
				Control deck = getControl();
				deck.setRedraw(false);
				try {
					removeAll(deck);
					deck.setData(getRoot());
					createChildren(deck, true);
				} finally {
					deck.setRedraw(true);
				}
			}
		});
	}

	protected void removeAll(Control widget) {
		((Deck) widget).removeAll();
	}

	protected List<Card> getChildren(Widget widget) {
		if (widget instanceof Deck) {
			return ((Deck) widget).getCards();
		} 
		return null;
	}
		
	protected List<Task> getChildren(Object element) {
		if (element instanceof TaskManager) {
			return ((TaskManager) element).tasks();
		}
		return null;
	}

	private Widget internalFindItem(Card card, Object element) {
		// compare with node
		Object data = card.getData();
		if (data != null) {
			if (equals(data, element)) {
				return card;
			}
		}
		return null;
	}

	protected void doUpdateItem(final Widget card, Object element) {
		if (card.isDisposed()) {
			unmapElement(element, card);
			return;
		}
		//TODO update card to element
	}


	protected void setBusy(boolean busy) {
		this.busy = busy;
	}

	public boolean isBusy() {
		return busy;
	}
	
	private boolean isCardContentProvider() {
		return getContentProvider() instanceof CardContentProvider;
	}
	
	void createChildren(final Widget widget, boolean materialize) {
		boolean oldBusy = isBusy();
		setBusy(true);
		try {
			final List<Card> tis = getChildren(widget);
			if (tis != null && tis.size() > 0) {
				Object data = tis.get(0).getData();
				if (data != null) {
					return; // children already there!
				}
			}

			BusyIndicator.showWhile(widget.getDisplay(), new Runnable() {
				@Override
				public void run() {
					// fix for PR 1FW89L7:
					// don't complain and remove all "dummies" ...
					if (tis != null) {
						for (int i = 0; i < tis.size(); i++) {
							if (tis.get(i).getData() != null) {
								disassociate(tis.get(i));
								Assert.isTrue(tis.get(i).getData() == null,
										"Second or later child is non -null");//$NON-NLS-1$

							}
							tis.get(i).dispose();
						}
					}
					Object rootObject = widget.getData();
					if (rootObject != null) {
						Object parentElement = rootObject;
						Object[] children;
						if (isCardContentProvider() && widget instanceof Deck) {
//							List<Task> path = getContentProvider().getElements(parentElement);
//							children = new Object[path.size()];
//							for (int i = 0; i < path.size(); i++) {
//								children[i] = path.get(i);
//							}
							children = getSortedChildren(parentElement);
						} else {
							children = getSortedChildren(parentElement);
						}
						for (int i = 0; i < children.length; i++) {
							createTreeItem(widget, children[i]);
						}
					}
				}

			});
		} finally {
			setBusy(oldBusy);
		}
	}

	protected List<Task> getTasksFromDeck(Object element) {
		List<Task> tasks = new ArrayList<>();
		List<Task> children = getChildren(element);
		for (Task task : children) {
			tasks.add(task);	
		}
		return tasks;
	}
	
	protected void createTreeItem(Widget parent, Object element) {
		Card card = newItem(parent, SWT.NULL, element);
		updateItem(card, element);
	}
	
	protected Card newItem(Widget parent, int flags, Object element) {
		Card card = null;
		boolean oldBusy = isBusy();
		setBusy(true);
		try {
			if (busy) {
				Task task = (Task) element;
				CardLabelProvider labelProvider = (CardLabelProvider) getLabelProvider();
				Color color = labelProvider.getColor(task);
				if (ismapped) {
					card = new MappedCard((Composite) parent, flags, task, color, this); 
					//TODO remove this logic of passing viewer to card
				} else {
					card = new MappingCard((Composite) parent, flags, task, color, this);
				}
				card.setLayoutData(new GridData(GridData.FILL_BOTH));
			}
		} finally {
			setBusy(oldBusy);
		}
		return card;
	}
}
