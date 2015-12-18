package com.bosch.bct.utils.timesheet.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.bosch.bct.utils.timesheet.dragdrop.CardDropListner;
import com.bosch.bct.utils.timesheet.dragdrop.CardTransfer;
import com.bosch.bct.utils.timesheet.model.Day;
import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.model.TaskType;
import com.bosch.bct.utils.timesheet.provider.CardContentProvider;
import com.bosch.bct.utils.timesheet.provider.CardLabelProvider;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;
import com.bosch.bct.utils.timesheet.widget.AddRoundedButton;
import com.bosch.bct.utils.timesheet.widget.Deck;
import com.bosch.bct.utils.timesheet.widget.RemoveRoundedButton;
import com.bosch.bct.utils.timesheet.widget.RoundedButton;

public class TimesheetView extends ViewPart {

	TaskManager taskManager = TaskManager.getInstance();
	private Font headerFont;
	public TimesheetView() {
	}

	@Override
	public void createPartControl(Composite parent) {

		//TODO remove this code while deploying
		if (taskManager.tasks().isEmpty()) {
			createExample();
		}
		parent.setLayout(new GridLayout(1, false));
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		firstLevelLayout(parent);
	}
	
	private void firstLevelLayout(Composite parent) {

		SashForm parentForm = new SashForm(parent, SWT.VERTICAL);
		parentForm.setLayout(new GridLayout(1, false));
		parentForm.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		SashForm formAbove = new SashForm(parentForm, SWT.HORIZONTAL);
		formAbove.setLayout(new GridLayout(2, false));
		formAbove.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		createMappingCardDeck(formAbove);
		createRightComposite(formAbove);
		
//		SashForm formBelow = new SashForm(parentForm, SWT.HORIZONTAL);
//		formBelow.setLayout(new GridLayout(1, false));
//		formBelow.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(parentForm, SWT.BORDER);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 2, 1));
		
		formAbove.setWeights(new int[]{ 1, 2 });
		parentForm.setWeights(new int[]{ 7, 1});
	}

	private void createExample() {
		Task req_123 = new Task("123", TaskType.REQUIREMENT);
		taskManager.mapTask(Day.MONDAY, req_123, 5.0);
		taskManager.addTask(req_123);
		Task des_123 = new Task("123", TaskType.DESIGN);
		taskManager.mapTask(Day.MONDAY, des_123, 5.0);
		taskManager.addTask(des_123);
		taskManager.addTask(new Task("123", TaskType.CODING));
		taskManager.addTask(new Task("123", TaskType.TESTING));
		
		Task req_456 = new Task("456", TaskType.REQUIREMENT);
		taskManager.mapTask(Day.TUESDAY, req_456, 4.0);
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
		
		Deck deck = new Deck(scrolledComposite, SWT.BORDER, null);

		final DeckViewer deckViewer = new DeckViewer(deck);
		deckViewer.setContentProvider(new CardContentProvider(null));
		deckViewer.setLabelProvider(new CardLabelProvider());
		deckViewer.setInput(taskManager);
		
		scrolledComposite.setMinSize(deck.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setContent(deck);
		
		deck.setSize(deck.computeSize(SWT.DEFAULT, SWT.DEFAULT));

//		deckViewer.addDragSupport(DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK,
//			       new Transfer[] { ViewDecriptionTransfer.getInstance() }, new ViewPartDragSourceListner(deckViewer));
		
		Composite bottomComposite = new Composite(rootComposite, SWT.NONE);
		bottomComposite.setLayout(new GridLayout(8, false));
		bottomComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		RoundedButton addRoundedButton = new AddRoundedButton(bottomComposite, SWT.NONE, taskManager, deckViewer);
		RoundedButton removeRoundedButton = new RemoveRoundedButton(bottomComposite, SWT.NONE, taskManager, deckViewer);
		
		return rootComposite;
	}
	
	
	private void createRightComposite(Composite parent){
		
		Day[] days = {Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY , Day.FRIDAY};
		FontData fontData = new  FontData();
		fontData.setHeight(12);
		fontData.setStyle(SWT.BOLD);
		headerFont = new Font(Display.getDefault(), fontData);

		final Composite rootParent = new Composite(parent, SWT.NONE);
		rootParent.setLayout(new GridLayout(5, true));
		rootParent.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		for (int i = 0; i < days.length; i++) {

			final Composite rootComposite = new Composite(rootParent, SWT.BORDER);
			rootComposite.setLayout(new GridLayout(1, true));
			rootComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
			
			Label deckHeader = new Label(rootComposite, SWT.CENTER | SWT.BORDER);
			deckHeader.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			deckHeader.setText(days[i].name());
			deckHeader.setBackground(new Color(Display.getDefault(), 200, 200, 200));
			deckHeader.setFont(headerFont);

			final ScrolledComposite scrolledComposite = new ScrolledComposite(rootComposite, SWT.NONE | SWT.H_SCROLL | SWT.V_SCROLL);
			scrolledComposite.setLayout(new GridLayout(1, true));
			scrolledComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
			
			final Composite deck = createMappedCardDeck(scrolledComposite, days[i]);
			scrolledComposite.setMinSize(deck.computeSize(SWT.DEFAULT, SWT.DEFAULT));
			scrolledComposite.setExpandHorizontal(true);
			scrolledComposite.setContent(deck);
			
			Label deckFooter = new Label(rootComposite, SWT.CENTER | SWT.BORDER);
			deckFooter.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			deckFooter.setText("Total Effort: " + taskManager.dayEffort(days[i]));
			deckFooter.setBackground(new Color(Display.getDefault(), 200, 200, 200));
			deckFooter.setFont(headerFont);
			
			deck.setSize(scrolledComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		}
	}
	

	private Composite createMappedCardDeck(Composite parent, Day day){
		Deck deck = new Deck(parent, SWT.BORDER, day);
		DeckViewer deckViewer = new DeckViewer(deck, true);
		deckViewer.setContentProvider(new CardContentProvider(day));
		deckViewer.setLabelProvider(new CardLabelProvider());
		deckViewer.setInput(taskManager);
		
		deckViewer.addDropSupport(DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_LINK,
			       new Transfer[] { CardTransfer.getInstance() }, new CardDropListner(deckViewer));
		return deck;
	}

	
	@Override
	public void setFocus() {

	}
	
	@Override
	public void dispose() {
//		headerFont.dispose();
		super.dispose();
	}

}
