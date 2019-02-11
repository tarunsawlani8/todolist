package com.deloitte.todo.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.todo.dao.Task;
import com.deloitte.todo.dao.TaskRepository;
import com.deloitte.todo.helper.TaskHelper;
import com.deloitte.todo.model.TaskModel;
import com.deloitte.todo.model.UserModel;
import com.deloitte.todo.service.TaskService;

@Transactional
@Service
public class TaskServiceImpl implements TaskService {

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private TaskRepository taskRepository;

	public TaskModel createTask(TaskModel taskModel) {
		taskModel.setLastUpdatedDate(sdf.format(new Date()));
		Task task = taskRepository.save(TaskHelper.modelToEntity(taskModel));
		TaskModel response = null;
		if (task != null) {
			response = TaskHelper.entityToModel(task);
		}

		return response;

	}

	public List<TaskModel> loadTask(UserModel userModel) {
		List<Task> taskList = taskRepository.findByUser(userModel.getId());
		List<TaskModel> taskModelList = new ArrayList<>();

		taskList.forEach(task -> {
			taskModelList.add(TaskHelper.entityToModel(task));
		});

		return taskModelList;
	}

	public TaskModel updateTask(TaskModel request) {
		request.setLastUpdatedDate(sdf.format(new Date()));

		Task task = taskRepository.save(TaskHelper.modelToEntity(request));
		TaskModel response = null;
		if (task != null) {
			response = TaskHelper.entityToModel(task);
		}

		return response;

	}

	public TaskModel deleteTask(TaskModel request) {
		taskRepository.delete(TaskHelper.modelToEntity(request));
		return request;
	}

}
