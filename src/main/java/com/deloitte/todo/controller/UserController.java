package com.deloitte.todo.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.security.MD5Encoder;
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
import com.deloitte.todo.model.UserModel;
import com.deloitte.todo.service.UserService;

@RequestMapping("/users")
@RestController
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserModel> login(@RequestBody UserModel userModel) {
		LOGGER.info("Logging in={}", userModel.getUsername());

		userModel = userService.login(userModel);

		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);

	}

	@RequestMapping(value = "/logoff", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserModel> logoff(HttpServletRequest request) {
		UserModel userModel = (UserModel) CacheMap.readEntry(request.getHeader("token"));
		CacheMap.deleteEntry(userModel.getToken());
		LOGGER.info("Logged off={}", userModel.getUsername());
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);

	}

}
