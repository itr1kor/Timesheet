package com.bosch.bct.utils.timesheet.dialog;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.model.TaskType;

public class CreateTaskDialog extends TitleAreaDialog implements SelectionListener, ModifyListener {

	private Button requirementCheck;
	private Button designCheck;
	private Button codingCheck;
	private Button testingCheck;
	private TaskManager taskManager;
	private Text taskName;
	private Button reviewCheck;

	public CreateTaskDialog(Shell parent, TaskManager taskManager) {
		super(parent);
		this.taskManager = taskManager;
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
//		label.setFont(new Font(Display.getDefault(), new FontData("Freight Sans", 9, SWT.NORMAL)));
		
		taskName = new Text(composite, SWT.BORDER);
		taskName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		taskName.addModifyListener(this);
		
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
		
		reviewCheck = new Button(checkList, SWT.CHECK);
		reviewCheck.setText(TaskType.REVIEW.name());
		reviewCheck.addSelectionListener(this);
		
		return super.createDialogArea(parent);
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		super.createButtonsForButtonBar(parent);
		getButton(IDialogConstants.OK_ID).setEnabled(false);
	}
	
	private void updateButtons() {

		if(!taskName.getText().isEmpty() && (requirementCheck.getSelection() || designCheck.getSelection() 
				|| codingCheck.getSelection() || testingCheck.getSelection() || reviewCheck.getSelection() )) {
			
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
	}
	
	@Override
	public void modifyText(ModifyEvent e) {
		updateButtons();
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if(buttonId == IDialogConstants.OK_ID){
			String name = taskName.getText();
			if(requirementCheck.getSelection()){
				taskManager.addTask(new Task(name, TaskType.REQUIREMENT));
			}
			if(designCheck.getSelection()){
				taskManager.addTask(new Task(name, TaskType.DESIGN));
			}
			if(codingCheck.getSelection()){
				taskManager.addTask(new Task(name, TaskType.CODING));
			}
			if(testingCheck.getSelection()){
				taskManager.addTask(new Task(name, TaskType.TESTING));
			}
			if(reviewCheck.getSelection()){
				taskManager.addTask(new Task(name, TaskType.REVIEW));
			}
		}
		super.buttonPressed(buttonId);
	}
}
