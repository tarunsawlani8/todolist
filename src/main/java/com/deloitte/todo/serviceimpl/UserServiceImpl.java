package com.deloitte.todo.serviceimpl;

import java.security.NoSuchAlgorithmException;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.todo.dao.User;
import com.deloitte.todo.dao.UserRepository;
import com.deloitte.todo.helper.CacheMap;
import com.deloitte.todo.helper.UserHelper;
import com.deloitte.todo.model.Error;
import com.deloitte.todo.model.UserModel;
import com.deloitte.todo.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	public UserModel login(UserModel userModel) {

		boolean authenticated = false;

		User user = userRepository.findByUser(userModel.getUsername());
		if (user != null) {
			authenticated = userModel.getPassword().equals(user.getPassword());
		}

		if (!authenticated) {
			userModel.setError(new Error(1, "Invalid login details.Please try again."));
			return userModel;
		}

		userModel = UserHelper.entityToModel(user);
		try

		{
			userModel.setToken(UserHelper.getToken(user));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		LOGGER.info("new Token generated={}", userModel.getToken());
		CacheMap.addEntry(userModel.getToken(), userModel);
		return userModel;
	}
	

}
