package com.bosch.bct.utils.timesheet.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.bosch.bct.utils.timesheet.widget.Card;

public class TimesheetView extends ViewPart {

	public TimesheetView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {

		parent.setLayout(new GridLayout(1, false));
		Card card = new Card(parent, SWT.NONE);
		
		card.setLayoutData(new GridData(GridData.FILL_BOTH));
		
	}

	@Override
	public void setFocus() {

	}

}
