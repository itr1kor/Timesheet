package com.bosch.bct.utils.timesheet.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.bosch.bct.utils.timesheet.model.TaskType;

public class CreateTaskDialog extends TitleAreaDialog implements SelectionListener {

	private Button requirementCheck;
	private Button designCheck;
	private Button codingCheck;
	private Button testingCheck;

	public CreateTaskDialog(Shell parent) {
		super(parent);
	}
	
	@Override
	protected Control createContents(Composite parent) {
		return super.createContents(parent);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		
		setTitle("Create and add tasks");
		setMessage("Please enter task name/number and check buttons to create for the task.");
		
		parent.setLayout(new GridLayout(1, false)); 
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false)); 
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Task Name/Number : ");
		label.setFont(new Font(Display.getDefault(), new FontData("Freight Sans", 9, SWT.NORMAL)));
		
		Text text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Composite checkList = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		checkList.setLayout(layout); 
		layout.marginLeft = 50;
		checkList.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		requirementCheck = new Button(checkList, SWT.CHECK);
		requirementCheck.setText(TaskType.REQUIREMENT.name());
		requirementCheck.addSelectionListener(this);
		
		designCheck = new Button(checkList, SWT.CHECK);
		designCheck.setText(TaskType.DESIGN.name());
		designCheck.addSelectionListener(this);
		
		codingCheck = new Button(checkList, SWT.CHECK);
		codingCheck.setText(TaskType.CODING.name());
		codingCheck.addSelectionListener(this);
		
		testingCheck = new Button(checkList, SWT.CHECK);
		testingCheck.setText(TaskType.TESTING.name());
		testingCheck.addSelectionListener(this);
		
		return super.createDialogArea(parent);
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		getButton(IDialogConstants.OK_ID).setEnabled(false);
	}
	
	private void updateButtons() {

		if(requirementCheck.getSelection() || designCheck.getSelection() 
				|| codingCheck.getSelection() || testingCheck.getSelection()){
			
			getButton(IDialogConstants.OK_ID).setEnabled(true);
		}else{
			getButton(IDialogConstants.OK_ID).setEnabled(false);
		}
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		updateButtons();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
	}
}
