package com.bosch.utils.timesheet.wizard;

import com.bosch.utils.timesheet.wizard.impl.CreateTaskWizard;
import com.bosch.utils.timesheet.wizard.impl.CreateTaskWizardPage;
import com.bosch.utils.timesheet.wizard.impl.FourthWizardPage;
import com.bosch.utils.timesheet.wizard.impl.SecondWizardPage;
import com.bosch.utils.timesheet.wizard.impl.ThirdWizardPage;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestWizard extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		WizardDialog dialog  = new WizardDialog();
		Wizard wizard = new CreateTaskWizard();
		wizard.addPage(new CreateTaskWizardPage());
		wizard.addPage(new SecondWizardPage());
		wizard.addPage(new ThirdWizardPage());
		wizard.addPage(new FourthWizardPage());
		dialog.setWizard(wizard);
		dialog.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
