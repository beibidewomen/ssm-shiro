package com.xuzhiwen.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuzhiwen.dao.LoginDao;
import com.xuzhiwen.entity.UserInfo;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	public List<UserInfo> queryAllUser() {
		List<UserInfo> users = loginDao.queryAllUser();
		for (UserInfo user : users) {
			System.out.println(user);
		}
		return users;
	}

	public void validateUser(String username, String password) {
		loginDao.validateUser(username,password);
	}

	public UserInfo getUserByUsername(String username) {
		return loginDao.getUserByUsername(username);
	}

	public Set<String> getRoles(String username) {
		return loginDao.getRoles(username);
	}

	public Set<String> getPermissions(String username) {
		return loginDao.getPermissions(username);
	}
}
