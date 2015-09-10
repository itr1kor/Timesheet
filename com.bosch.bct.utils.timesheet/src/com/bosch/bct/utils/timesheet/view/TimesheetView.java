package com.bosch.bct.utils.timesheet.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.bosch.bct.utils.timesheet.widget.MappedCard;
import com.bosch.bct.utils.timesheet.widget.MappingCard;
import com.bosch.bct.utils.timesheet.widget.RoundedButton;

public class TimesheetView extends ViewPart {

	public TimesheetView() {
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(2, false));
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		SashForm form = new SashForm(parent, SWT.HORIZONTAL);
		form.setLayout(new GridLayout(2, false));
		form.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		
		createMappingCardDeck(form);
		createRightComposite(form);
		
		form.setWeights(new int[]{1,2});
	}
	
	private Composite createMappingCardDeck(Composite parent) {
		
		final Composite rootComposite = new Composite(parent, SWT.BORDER);
		rootComposite.setLayout(new GridLayout(1, true));
		
		rootComposite.setLayoutData(new GridData(GridData.FILL_VERTICAL));

		final ScrolledComposite scrolledComposite = new ScrolledComposite(rootComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayout(new GridLayout(1, true));
		scrolledComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(scrolledComposite, SWT.BORDER);
		composite.setLayout(new GridLayout(1, true));
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(layoutData);

		scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setContent(composite);
		
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappingCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		
		composite.setSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		
		Composite bottomComposite = new Composite(rootComposite, SWT.NONE);
		bottomComposite.setLayout(new GridLayout(8, false));
		bottomComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
//		new RoundedButton(rootComposite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_VERTICAL));
		new RoundedButton(bottomComposite, SWT.NONE);
		return composite;
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
		
		for (int i = 0; i < 5; i++) {
			createMappedCardDeck(cardSelectionComposite);
		}
		cardSelectionComposite.setSize(cardSelectionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}
	

	private Composite createMappedCardDeck(Composite parent){

		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new GridLayout(1, true));
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(layoutData);

		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new MappedCard(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));


		return composite;
	}

	
	@Override
	public void setFocus() {

	}

}
