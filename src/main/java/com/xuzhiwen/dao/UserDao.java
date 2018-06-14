package com.xuzhiwen.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xuzhiwen.entity.UserInfo;
import com.xuzhiwen.mapper.UserInfoMapper;

@Repository
public class UserDao extends BaseDao{
	
	public UserInfo getUserById(String userId) {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		UserInfo user = mapper.selectByPrimaryKey(userId);
		return user;
	}

	public void addUser(UserInfo user) {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		mapper.insert(user);
	}

	public void delUserById(String id) {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		mapper.deleteByPrimaryKey(id);
	}

	public void updateUser(UserInfo user) {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		mapper.updateByPrimaryKey(user);
	}

	public List<UserInfo> getAllUser() {
		SqlSession session = getSession();
		UserInfoMapper mapper = session.getMapper(UserInfoMapper.class);
		List<UserInfo> users = mapper.selectByExample(null);
		return users;
	}
}
