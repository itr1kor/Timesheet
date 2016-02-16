package com.bosch.utils.timesheet.wizard;

import javafx.scene.layout.VBox;

public abstract class WizardPage extends VBox {
	
	private Wizard wizard;

	public WizardPage() {
		super();
		setStyle("-fx-padding: 10px; -fx-background-color: #FFFFFF");
	}

	public abstract void createPageContents();

	public WizardPage getNextPage() {
		if (wizard == null) {
			return null;
		}
		return wizard.getNextPage(this);
	}
	
	public WizardPage getPreviousPage() {
		if (wizard == null) {
			return null;
		}
		return wizard.getPreviousPage(this);
	}
	
	public boolean hasNextPage() {
		return getNextPage() != null;
	}
	
	public boolean canFinish() {
		return false;
	}
	
	public void setWizard(Wizard newWizard) {
        wizard = newWizard;
    }
	
//	public void updateMessage(String message) {
//		wizard.setMessage(message);
//	}
//	
//	public void updateTitle(String title) {
//		wizard.setTitle(title);
//	}
}
