package com.bosch.bct.utils.timesheet.dialog;

import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.viewer.DeckViewer;

/**
 * @author she5cob
 */
public class DeleteTaskDialog extends TrayDialog {

	private final TaskManager taskManager;
	private final DeckViewer deckViewer;
	private Label messageLabel;
	private String message = "Do you really want to delete the task?";

	/**
	 * @param parent
	 * @param taskManager
	 * @param deckViewer
	 */
	public DeleteTaskDialog(final Shell parent, final TaskManager taskManager, final DeckViewer deckViewer) {
		super(parent);
		this.taskManager = taskManager;
		this.deckViewer = deckViewer;
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		// create message
		if (message != null) {
			messageLabel = new Label(parent, SWT.WRAP);
			messageLabel.setText(message);
			GridDataFactory
			.fillDefaults()
			.align(SWT.FILL, SWT.BEGINNING)
			.grab(true, false)
			.hint(
					convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
					SWT.DEFAULT).applyTo(messageLabel);
		}
		return parent;
	}

	@Override
	protected void buttonPressed(final int buttonId) {
		if (buttonId == IDialogConstants.OK_ID) {
			IStructuredSelection selection = (IStructuredSelection) this.deckViewer.getSelection();
			List list = selection.toList();
			for (Object object : list) {
				this.taskManager.removeTasks((Task) object);
			}
		}
		super.buttonPressed(buttonId);
	}
}
