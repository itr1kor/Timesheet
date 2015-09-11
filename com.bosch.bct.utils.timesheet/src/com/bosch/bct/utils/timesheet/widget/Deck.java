package com.bosch.bct.utils.timesheet.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class Deck extends Composite{

	List<Card> cards = new ArrayList<>();

	public Deck(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, true));
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		setLayoutData(layoutData);
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
		return cards;
	}

	public void removeAll() {
		cards.clear();
	}

	public void setSelection(Card[] newItems) {
	}

	public void showItem(Card item) {
	}
}
