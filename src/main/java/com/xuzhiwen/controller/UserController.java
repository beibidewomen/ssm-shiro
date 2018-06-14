package com.xuzhiwen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xuzhiwen.entity.UserInfo;
import com.xuzhiwen.service.UserService;
import com.xuzhiwen.untils.MD5Until;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/queryAll",method=RequestMethod.GET)
	public @ResponseBody List<UserInfo> queryAllUser(Map<String, Object> map) {
		List<UserInfo> users = userService.getAllUser();
		return users;
	}
	
	@RequestMapping(value="/query/{id}",method=RequestMethod.GET)
	public @ResponseBody UserInfo queryUser(@PathVariable("id") String id) {
		UserInfo user = userService.getUserById(id);
		return user;
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public @ResponseBody UserInfo addUser(@RequestBody UserInfo user) {
		String autoId = UUID.randomUUID().toString();
		String password = MD5Until.getPassword(user);
		System.out.println(autoId);
		System.out.println(password);
		user.setUserId(autoId);
		user.setPassword(password);
		userService.addUser(user);
		return user;
	}
	
	@RequestMapping(value="/del/{id}",method=RequestMethod.DELETE)
	public @ResponseBody String delUser(@PathVariable("id") String id) {
		userService.delUserById(id);
		return id;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public @ResponseBody UserInfo updateUser(@RequestBody UserInfo user) {
		userService.updateUser(user);
		return user;
	}
	
	@RequestMapping(value="/returnJSON",method=RequestMethod.GET)
	public @ResponseBody String returnJSON() {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		map.put("status",false);
		return JSON.toJSONString(map);
	}
	
	
	@RequestMapping(value="/add")
//	@RequiresPermissions("menu1")
	public @ResponseBody String add() {
		System.out.println("add...");
		return "add...";
	}
	
	@RequestMapping(value="/up")
//	@RequiresPermissions("menu2")
	public @ResponseBody String up() {
		System.out.println("up...");
		return "up...";
	}
	
	@RequestMapping(value="/del")
//	@RequiresPermissions("menu3")
	public  @ResponseBody String del() {
		System.out.println("del...");
		return "del...";
	}
	
	@RequestMapping(value="/add/{menuName}")
	public @ResponseBody String add(@PathVariable("menuName") String menuName) {
		Map<String,Boolean> map = new HashMap<String,Boolean>();
		Subject subject = SecurityUtils.getSubject();
		boolean permitted = subject.isPermitted(menuName);
		map.put("status", permitted);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping(value="/unauthorized")
	public @ResponseBody String unauthorized() {
		Map<String,String> map = new HashMap<>();
		map.put("unauthorized", "unauthorized");
		return JSON.toJSONString(map);
	}
	
	@RequestMapping(value="/validate/{menuName}")
	public @ResponseBody String validate(@PathVariable("menuName") String menuName) {
		Map <String,Boolean> map = new HashMap<String,Boolean>();
		Subject subject = SecurityUtils.getSubject();
		boolean permitted = subject.isPermitted(menuName);
		map.put("status", permitted);
		return JSON.toJSONString(map);
	}
	
	@RequestMapping(value="/getURL")
	public @ResponseBody String getURL(HttpServletRequest request) {
		System.out.println("/getURL");
		String requestUri = WebUtils.getRequestUri(request);
		System.out.println(requestUri);
		return requestUri;
	}
	
}
