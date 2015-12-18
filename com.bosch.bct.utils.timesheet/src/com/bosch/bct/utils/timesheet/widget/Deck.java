package com.bosch.bct.utils.timesheet.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.bosch.bct.utils.timesheet.model.Day;

public class Deck extends Composite {

	private List<Card> cards = new ArrayList<>();
	//TODO please change this logic
	private Day day;

	public static final String IS_BUTTON_PRESSED = Deck.class.toString() + "_pressed";
	
	StructuredSelection selection;
	
	public Deck(Composite parent, int style, Day initDay) {
		super(parent, style);
		setLayout(new GridLayout(1, true));
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		setLayoutData(layoutData);
		day = initDay;
//		setLayout(new GridLayout(1, true));
		setBackground(getDisplay().getSystemColor(SWT.COLOR_WHITE));
		FillLayout fillLayout = new FillLayout(SWT.VERTICAL);
		fillLayout.spacing = 10;
		fillLayout.marginWidth = 5;
		fillLayout.marginHeight = 5;
		setLayout(fillLayout);
		
//		GridData layoutData = new GridData(GridData.FILL_BOTH);
//		setLayoutData(layoutData);
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public void addCard(Card card) {
		cards.add(card);
	}
	
	public List<Card> getSelection() {
		List<Card> selection = new ArrayList<>();
		for (Card card : cards) {
			if (card.getSelection()) {
				selection.add(card);
			}
		}
		return selection;
	}
	
	public void removeAll() {
		cards.clear();
	}

	public void setSelection(List<Card> selections) {
		for (Card card : cards) {
			if (selections.contains(card)) {
				card.setSelection(true);
			}else {
				card.setSelection(false);
			}
		}
	}

	public void showItem(Card item) {
	}
	
	public Day getDay() {
		return day;
	}

	public void setSingleSelection(Card selectionCard){
		for (Card card : cards) {
			card.setSelection(false);
		}
		selectionCard.setSelection(true);
	}
}
