package com.xuzhiwen.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xuzhiwen.entity.UserInfoExample;
import com.xuzhiwen.entity.UserInfoExample.Criteria;
import com.xuzhiwen.entity.UserInfo;
import com.xuzhiwen.mapper.UserInfoMapper;

@Repository
public class LoginDao extends BaseDao{
	
	/**
	 *获取所有用户信息 
	 * @return
	 */
	public List<UserInfo> queryAllUser() {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		List<UserInfo> users = mapper.selectByExample(null);
		for (UserInfo user : users) {
			System.out.println(user);
		}
		return users;
	}
	
	/**
	 * 验证用户信息
	 * @param username
	 * @param password
	 */
	public void validateUser(String username, String password) {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		UserInfoExample example = new UserInfoExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andUserIdEqualTo(username);
		createCriteria.andUserIdEqualTo(password);
		mapper.selectByExample(example );
	}

	public UserInfo getUserByUsername(String username) {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		UserInfo user = mapper.getUserByUsename(username);
		return user;
	}

	public Set<String> getRoles(String username) {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		Set<String> roles = mapper.getRoles(username);
		return roles;
	}

	public Set<String> getPermissions(String username) {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		Set<String> permissions = mapper.getPermissions(username);
		return permissions;
	}
}
