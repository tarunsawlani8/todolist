package com.deloitte.todo.helper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import com.deloitte.todo.dao.User;
import com.deloitte.todo.model.UserModel;

public class UserHelper {
	
	public static UserModel entityToModel(User user) {
		UserModel model = new UserModel();
		model.setId(user.getId());
		model.setName(user.getName());
		model.setPassword(user.getPassword());
		model.setUsername(user.getUsername());
		return model;
	}
	
	public static User modelToEntity(UserModel userModel) {
		User user = new User();
		user.setId(userModel.getId());
		user.setName(userModel.getName());
		user.setPassword(userModel.getPassword());
		user.setUsername(userModel.getUsername());
	return	user;
		
	}
	
	public static String getToken(User user) throws NoSuchAlgorithmException {
		
	MessageDigest md=	MessageDigest.getInstance("SHA-1");
	StringBuilder message = new StringBuilder();
	message.append(user.getUsername()).append(user.getPassword())
	.append(Long.toString(System.currentTimeMillis()));
	md.update(message.toString().getBytes());
	return DatatypeConverter
		      .printHexBinary(md.digest()).toUpperCase();
		
	}

}
