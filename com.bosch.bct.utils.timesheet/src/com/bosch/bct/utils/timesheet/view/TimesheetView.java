package com.bosch.bct.utils.timesheet.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.bosch.bct.utils.timesheet.layout.CardLayout;
import com.bosch.bct.utils.timesheet.widget.Card;

public class TimesheetView extends ViewPart {

	public TimesheetView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, true));
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));

		final ScrolledComposite scrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayout(new GridLayout(1, true));
		scrolledComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		final Composite cardSelectionComposite = new Composite(scrolledComposite, SWT.BORDER);
		cardSelectionComposite.setLayout(new GridLayout(5, true));
		cardSelectionComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		scrolledComposite.setMinSize(cardSelectionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setContent(cardSelectionComposite);

		for (int i = 0; i < 5; i++) {
			createDeck(cardSelectionComposite);
		}
		cardSelectionComposite.setSize(cardSelectionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
	}
	
	private Composite createDeck(Composite parent){

		Composite composite = new Composite(parent, SWT.BORDER);
		composite.setLayout(new GridLayout(1, true));
		GridData layoutData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(layoutData);

		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));
		new Card(composite, SWT.BORDER).setLayoutData(new GridData(GridData.FILL_BOTH));


		return composite;
	}

	
	@Override
	public void setFocus() {

	}

}
