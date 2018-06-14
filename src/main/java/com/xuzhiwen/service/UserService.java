package com.xuzhiwen.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuzhiwen.dao.UserDao;
import com.xuzhiwen.entity.UserInfo;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public UserInfo getUserById(String id) {
		return userDao.getUserById(id);
	}

	public void addUser(UserInfo user) {
		userDao.addUser(user);
	}

	public void delUserById(String id) {
		userDao.delUserById(id);
	}

	public void updateUser(UserInfo user) {
		userDao.updateUser(user);
	}

	public List<UserInfo> getAllUser() {
		return userDao.getAllUser();
	}
}
