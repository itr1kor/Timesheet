package com.bosch.bct.utils.timesheet.widget;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.bosch.bct.utils.timesheet.model.Day;

public class Deck extends Composite {

	private List<Card> cards = new ArrayList<>();
	private Day day;

	public Deck(Composite parent, int style, Day initDay) {
		super(parent, style);
		setLayout(new GridLayout(1, true));
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		setLayoutData(layoutData);
		day = initDay;
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
	
	public Day getDay() {
		return day;
	}

}
