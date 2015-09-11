package com.bosch.bct.utils.timesheet.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.bosch.bct.utils.timesheet.model.Day;
import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.model.TaskType;
import com.bosch.bct.utils.timesheet.provider.CardContentProvider;
import com.bosch.bct.utils.timesheet.provider.CardLabelProvider;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;
import com.bosch.bct.utils.timesheet.widget.Deck;
import com.bosch.bct.utils.timesheet.widget.RoundedButton;

public class TimesheetView extends ViewPart {

	TaskManager taskManager = new TaskManager();
	public TimesheetView() {
	}

	@Override
	public void createPartControl(Composite parent) {

		createExample();
		
		parent.setLayout(new GridLayout(2, false));
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		SashForm form = new SashForm(parent, SWT.HORIZONTAL);
		form.setLayout(new GridLayout(2, false));
		form.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		
		createMappingCardDeck(form);
		createRightComposite(form);
		
		form.setWeights(new int[]{1,2});
	}
	
	private void createExample() {
		Task req_123 = new Task("123", TaskType.REQUIREMENT);
		req_123.addTaskMapping(Day.MONDAY, 5.0);
		taskManager.addTask(req_123);
		Task des_123 = new Task("123", TaskType.DESIGN);
		des_123.addTaskMapping(Day.MONDAY, 5.0);
		taskManager.addTask(des_123);
		taskManager.addTask(new Task("123", TaskType.CODING));
		taskManager.addTask(new Task("123", TaskType.TESTING));
		
		Task req_456 = new Task("456", TaskType.REQUIREMENT);
		req_456.addTaskMapping(Day.TUESDAY, 4.0);
		taskManager.addTask(req_456);
		taskManager.addTask(new Task("456", TaskType.DESIGN));
		taskManager.addTask(new Task("456", TaskType.CODING));
		taskManager.addTask(new Task("456", TaskType.TESTING));
		
		
		taskManager.addTask(new Task("789", TaskType.REQUIREMENT));
		taskManager.addTask(new Task("789", TaskType.DESIGN));
		taskManager.addTask(new Task("789", TaskType.CODING));
		taskManager.addTask(new Task("789", TaskType.TESTING));
	}

	private Composite createMappingCardDeck(Composite parent) {
		
		final Composite rootComposite = new Composite(parent, SWT.BORDER);
		rootComposite.setLayout(new GridLayout(1, true));
		
		rootComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		final ScrolledComposite scrolledComposite = new ScrolledComposite(rootComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayout(new GridLayout(1, true));
		scrolledComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Deck deck = new Deck(scrolledComposite, SWT.BORDER);

		DeckViewer deckViewer = new DeckViewer(deck);
		deckViewer.setContentProvider(new CardContentProvider(null));
		deckViewer.setLabelProvider(new CardLabelProvider());
		deckViewer.setInput(taskManager);
		
		scrolledComposite.setMinSize(deck.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setContent(deck);
		
		deck.setSize(deck.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		
		Composite bottomComposite = new Composite(rootComposite, SWT.NONE);
		bottomComposite.setLayout(new GridLayout(8, false));
		bottomComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
//		new RoundedButton(rootComposite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_VERTICAL));
		new RoundedButton(bottomComposite, SWT.NONE);
		return rootComposite;
	}
	
	
	private void createRightComposite(Composite parent){
		
		final Composite rootComposite = new Composite(parent, SWT.BORDER);
		rootComposite.setLayout(new GridLayout(1, true));
		rootComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		final ScrolledComposite scrolledComposite = new ScrolledComposite(rootComposite, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayout(new GridLayout(1, true));
		scrolledComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		final Composite cardSelectionComposite = new Composite(scrolledComposite, SWT.BORDER);
		cardSelectionComposite.setLayout(new GridLayout(5, true));
		cardSelectionComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		scrolledComposite.setMinSize(cardSelectionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setContent(cardSelectionComposite);
		
		Day[] days = {Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY , Day.FRIDAY};
		
		for (int i = 0; i < days.length; i++) {
			createMappedCardDeck(cardSelectionComposite, days[i]);
		}
		cardSelectionComposite.setSize(cardSelectionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	

	private Composite createMappedCardDeck(Composite parent, Day day){
		Deck deck = new Deck(parent, SWT.BORDER);
		DeckViewer deckViewer = new DeckViewer(deck, true);
		deckViewer.setContentProvider(new CardContentProvider(day));
		deckViewer.setLabelProvider(new CardLabelProvider());
		deckViewer.setInput(taskManager);
		return deck;
	}

	
	@Override
	public void setFocus() {

	}

}
