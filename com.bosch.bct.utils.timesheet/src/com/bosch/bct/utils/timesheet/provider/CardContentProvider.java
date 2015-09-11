package com.bosch.bct.utils.timesheet.provider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.bosch.bct.utils.timesheet.model.Day;
import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.model.TaskManager;

public class CardContentProvider implements IStructuredContentProvider{

	Day day ;
	public CardContentProvider(Day initDay) {
		day = initDay;
	}
	
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {

		List<Task> daystask = new ArrayList<>();
		if(inputElement instanceof TaskManager){
			TaskManager taskManager = (TaskManager)inputElement;
			List<Task> tasks = taskManager.tasks();
			if(day == null){
				return tasks.toArray();
			}else{
				for (Task task : tasks) {
					if(task.getTaskMapping().containsKey(day)){
						daystask.add(task);
					}
				}
			}
		}
		return daystask.toArray();
	}
}
