package com.bosch.bct.utils.timesheet.dialog;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.prefs.BackingStoreException;

import com.bosch.bct.utils.timesheet.Activator;

public class ConfigurePathDialog extends TitleAreaDialog {

	private Text filePath;
	final String SERVER_PATH = "ServerPath";

	public ConfigurePathDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle("Configure File Path");
		setMessage("Please configure server path of the timesheet file.");
		
		parent.setLayout(new GridLayout(1, false)); 
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(3, false)); 
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Server Path : ");
		//label.setFont(new Font(Display.getDefault(), new FontData("Freight Sans", 9, SWT.NORMAL)));
		
		filePath = new Text(composite, SWT.BORDER);
		filePath.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		IDialogSettings dialogSettings = Activator.getDefault().getDialogSettings();
		String serverPath = dialogSettings.get(SERVER_PATH);
		if (serverPath != null) {
			filePath.setText(serverPath);
		}
		Button browseButton = new Button(composite, SWT.NONE);
		browseButton.setText("Browse..");
		
		browseButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
				dialog.setFilterExtensions(new String [] {"*.xlsx"});
				dialog.setFilterPath("c:\\temp");
				String result = dialog.open();
				if (result != null) {
					filePath.setText(result);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				
			}
		});
		
		return super.createDialogArea(parent);
	}
	
	@Override
	protected void buttonPressed(int buttonId) {
		if(buttonId == IDialogConstants.OK_ID){
			IDialogSettings dialogSettings = Activator.getDefault().getDialogSettings();
			dialogSettings.put("ServerPath", filePath.getText());
		}
		super.buttonPressed(buttonId);
	}
}
