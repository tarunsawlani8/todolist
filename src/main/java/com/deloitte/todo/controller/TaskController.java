package com.deloitte.todo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.todo.helper.CacheMap;
import com.deloitte.todo.model.Error;
import com.deloitte.todo.model.TaskModel;
import com.deloitte.todo.model.UserModel;
import com.deloitte.todo.service.TaskService;

@RequestMapping("/tasks")
@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/createTask", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<TaskModel> createTask(HttpServletRequest request, @RequestBody TaskModel taskModel) {
		LOGGER.info("Creating task for taskName={}", taskModel.getTaskName());
		UserModel userModel = (UserModel) CacheMap.readEntry(request.getHeader("token"));
		TaskModel response = new TaskModel();
		if (userModel == null) {
			response.setError(new Error(2, "Un-Authorized access.Please login again"));
			return new ResponseEntity<TaskModel>(response, HttpStatus.OK);
		}
		taskModel.setUserId(userModel.getId());
		response = taskService.createTask(taskModel);
		return new ResponseEntity<TaskModel>(response, HttpStatus.CREATED);

	}

	@RequestMapping(value = "/loadTask", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<List<TaskModel>> loadTask(HttpServletRequest request) {
		LOGGER.info("Loading tasks for={}", request.getHeader("token"));
		UserModel userModel = (UserModel) CacheMap.readEntry(request.getHeader("token"));
		return new ResponseEntity<List<TaskModel>>(taskService.loadTask(userModel), HttpStatus.OK);

	}

	@RequestMapping(value = "/updateTask", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<TaskModel> updateTask(HttpServletRequest request, @RequestBody TaskModel taskModel) {
		LOGGER.info("Updating task for taskName={}, taskUpdateDate={}", taskModel.getTaskName(),
				taskModel.getLastUpdatedDate());
		UserModel userModel = (UserModel) CacheMap.readEntry(request.getHeader("token"));
		TaskModel response = new TaskModel();
		if (userModel == null) {
			response.setError(new Error(2, "Un-Authorized access.Please login again"));
			return new ResponseEntity<TaskModel>(response, HttpStatus.OK);
		}
		taskModel.setUserId(userModel.getId());
		response = taskService.updateTask(taskModel);
		return new ResponseEntity<TaskModel>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<TaskModel> deleteTask(HttpServletRequest request, @RequestBody TaskModel taskModel) {
		LOGGER.info("Deleting task for taskName={}, taskUpdateDate={}", taskModel.getTaskName(),
				taskModel.getLastUpdatedDate());
		UserModel userModel = (UserModel) CacheMap.readEntry(request.getHeader("token"));
		TaskModel response = new TaskModel();
		if (userModel == null) {
			response.setError(new Error(2, "Un-Authorized access.Please login again"));
			return new ResponseEntity<TaskModel>(response, HttpStatus.OK);
		}
		taskModel.setUserId(userModel.getId());
		taskService.deleteTask(taskModel);
		return new ResponseEntity<TaskModel>(response, HttpStatus.OK);

	}

}
