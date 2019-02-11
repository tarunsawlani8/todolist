package com.deloitte.todo.helper;

import com.deloitte.todo.dao.Task;
import com.deloitte.todo.model.TaskModel;

public class TaskHelper {

	public static Task modelToEntity(TaskModel taskModel) {
		Task task = new Task();
		task.setId(taskModel.getId());
		task.setTaskName(taskModel.getTaskName());
		if (taskModel.getTaskStatus()) {
			task.setTaskStatus("1");
		} else {
			task.setTaskStatus("0");
		}
		task.setTaskDesc(taskModel.getTaskDescription());
		task.setTaskUpdateDate(taskModel.getLastUpdatedDate());
		task.setUserId(taskModel.getUserId());

		return task;

	}

	public static TaskModel entityToModel(Task task) {

		TaskModel taskModel = new TaskModel();
		taskModel.setId(task.getId());
		taskModel.setTaskName(task.getTaskName());
		taskModel.setTaskDescription(task.getTaskDesc());
		if ("1".equals(task.getTaskStatus())) {
			taskModel.setTaskStatus(true);
		} else {
			taskModel.setTaskStatus(false);
		}
		taskModel.setLastUpdatedDate(task.getTaskUpdateDate());
		taskModel.setUserId(task.getUserId());

		return taskModel;
	}

}
