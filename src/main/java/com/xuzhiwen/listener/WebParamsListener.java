package com.xuzhiwen.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.xuzhiwen.dao.BaseDao;
import com.xuzhiwen.entity.UserMenu;
import com.xuzhiwen.mapper.UserMenuMapper;

@Component
public class WebParamsListener implements ApplicationListener<ContextRefreshedEvent> {
	public static Map<String,List<String>> map = new HashMap<>();
	
	@Autowired
	private BaseDao baseDao;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		UserMenuMapper mapper = baseDao.getSession().getMapper(UserMenuMapper.class);
		List<UserMenu> userMenu = mapper.getUserMenu();
		List<String> list = new ArrayList<>();
		String userId = "";
		if(userMenu != null && userMenu.size() >0) {
			for (UserMenu um : userMenu) {
				userId = um.getUser_id();
				list.add(um.getMenu_name());
			}
		}
		System.out.println(list);
		map.put(userId, list);
		System.out.println(map);
	}
}
