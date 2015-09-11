package com.bosch.bct.utils.timesheet.provider;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.bosch.bct.utils.timesheet.model.Task;

public class CardLabelProvider extends LabelProvider{
	
	Color requirementColor = new Color(Display.getDefault(), 255, 150 , 150);
	Color designtColor = new Color(Display.getDefault(), 150, 250, 150);
	Color codingColor = new Color(Display.getDefault(), 250, 150, 150);
	Color testingColor = new Color(Display.getDefault(), 15, 150, 150);
	Color defaultColor = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
	
	@Override
	public Image getImage(Object element) {
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof Task){
			Task task = (Task) element;
			return task.getName();
		}
		return super.getText(element);
	}
	
	public Color getColor(Object element) {
		if(element instanceof Task){
//			Task task = (Task) element;
//			TaskType taskType = task.getTaskType();
//			if(taskType == TaskType.REQUIREMENT){
//				return requirementColor;
//			}else if(taskType == TaskType.DESIGN){
//				return designtColor;
//			}else if(taskType == TaskType.CODING){
//				return codingColor;
//			}else if(taskType == TaskType.TESTING){
//				return testingColor;
//			}else{
				return defaultColor;
//			}
		}
		return null;
	}
}
