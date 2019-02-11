package com.deloitte.todo.service;

import java.util.List;

import com.deloitte.todo.model.TaskModel;
import com.deloitte.todo.model.UserModel;

public interface TaskService {

	public TaskModel createTask(TaskModel taskModel);

	public List<TaskModel> loadTask(UserModel request);

	public TaskModel updateTask(TaskModel request);

	public TaskModel deleteTask(TaskModel request);

}