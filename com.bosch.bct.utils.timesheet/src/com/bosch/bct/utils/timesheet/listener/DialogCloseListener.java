package com.bosch.bct.utils.timesheet.listener;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public abstract class DialogCloseListener implements Listener{
	
	@Override
	public void handleEvent(Event event) {
		onDialogClose(event);
	}
	
	public abstract void onDialogClose(Event event);
}
