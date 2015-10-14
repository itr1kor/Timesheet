/*
 * Copyright (c) Robert Bosch GmbH. All rights reserved.
 */
package com.bosch.bct.utils.timesheet.dragdrop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.TransferData;

import com.bosch.bct.utils.timesheet.model.Task;
import com.bosch.bct.utils.timesheet.model.TaskManager;
import com.bosch.bct.utils.timesheet.widget.Card;


/**
 * @author PPA7KOR
 */
public class CardTransfer extends ByteArrayTransfer {


  private static final CardTransfer instance = new CardTransfer();
  private static final String TYPE_NAME = "view-descriptor-transfer-format";//$NON-NLS-1$
  private static final int VIEWID = registerType(TYPE_NAME);

  private CardTransfer() {
  }

  public static CardTransfer getInstance() {
    return instance;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected int[] getTypeIds() {

    return new int[] { VIEWID };
  }

  boolean checkViewPart(final Object object) {
    if ((object == null) || !(object instanceof Card)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String[] getTypeNames() {
    return new String[] { TYPE_NAME };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean validate(final Object object) {
    return checkViewPart(object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void javaToNative(final Object object, final TransferData transferData) {

    Task task = ((Task) object);
    
    System.out.println(task.getName());
    System.out.println(task.getTaskType().name());
    try {

      ByteArrayOutputStream out = new ByteArrayOutputStream();
      DataOutputStream dataOut = new DataOutputStream(out);
      dataOut.writeUTF(task.getName());
      dataOut.writeUTF(task.getTaskType().name());
      dataOut.close();
      super.javaToNative(out.toByteArray(), transferData);
    }
    catch (IOException e) {
      // it's best to send nothing if there were problems
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Object nativeToJava(final TransferData transferData) {

    try {

      byte[] bytes = (byte[]) super.nativeToJava(transferData);
      ByteArrayInputStream in = new ByteArrayInputStream(bytes);
      DataInputStream dataIn = new DataInputStream(in);
      String name = dataIn.readUTF();
	  String taskType = dataIn.readUTF();
	  System.out.println(name);
	  System.out.println(taskType);
	  System.out.println();
      TaskManager taskManager = TaskManager.getInstance();
      List<Task> tasks = taskManager.tasks();
      for (Task task : tasks) {
        if (task.getName().equalsIgnoreCase(name) &&
        		task.getTaskType().name().equalsIgnoreCase(taskType)) {
          return task;
        }
      }
      return null;
    }
    catch (IOException e) {

      e.printStackTrace();
    }
    return null;
  }
}
