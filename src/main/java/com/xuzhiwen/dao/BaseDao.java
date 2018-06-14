package com.xuzhiwen.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSession getSession() {
		return sqlSessionFactory.openSession();
	}
}
