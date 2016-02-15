package com.bosch.utils.timesheet.wizard;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class WizardDialog extends Stage {

	public WizardDialog() {
		super(StageStyle.DECORATED);
		this.initModality(Modality.WINDOW_MODAL);
		this.setTitle("Wizard");
		this.centerOnScreen();
		this.setResizable(false);
	}
	
	public void setWizard(Wizard wizard) {
		setScene(wizard);
		sizeToScene();
	}
}
