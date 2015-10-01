package com.bosch.bct.utils.timesheet.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import com.bosch.bct.utils.timesheet.dialog.ConfigurePathDialog;

public class ConfigurePathHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		ConfigurePathDialog configurePathDialog = new ConfigurePathDialog(shell);
		return configurePathDialog.open();
	}

}
