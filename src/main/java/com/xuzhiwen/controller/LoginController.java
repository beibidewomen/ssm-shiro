package com.xuzhiwen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xuzhiwen.entity.UserInfo;
import com.xuzhiwen.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping("/login")
	public String login(Model model,HttpServletRequest request) {
		UserInfo user1 = new UserInfo();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		loginService.validateUser(username,password);
		user1.setUserId("user1");
		System.out.println("login...");
		List<UserInfo> user = loginService.queryAllUser();
		model.addAttribute("user", JSON.toJSONString(user));
		HttpSession session = request.getSession();
		session.setAttribute("user", user1);
		return "mainFrame";
	}
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public @ResponseBody String doLogin(UserInfo userInfo,HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//1.获取当前用户
		Subject currentUser = SecurityUtils.getSubject();
		//2.获取token
		UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword(),true);
		try {
			currentUser.login(token);
			if(currentUser.isAuthenticated()) {
				 request.getSession().setAttribute("user",currentUser);  
				 resultMap.put("status", 200);
		         resultMap.put("message", "login success ! ");
			}
		} catch (Exception e) {
			e.printStackTrace();
            request.getSession().setAttribute("user",currentUser);  
            resultMap.put("status", 500);
            resultMap.put("message", "username or password error...");
		}
		return JSON.toJSONString(resultMap);
	}
	
	
	@RequestMapping(value="/checkRoles/{roleName}",method=RequestMethod.GET)
	public String checkRoles(@PathVariable("roleName") String roleName) {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.checkRole(roleName);
		} catch (Exception e) {
			e.printStackTrace();
			return "unauthorized";
		}
		return "success";
	}
	
	@RequestMapping(value="/checkPermission/{menuName}",method=RequestMethod.GET)
	public String checkPermission(@PathVariable("menuName") String menuName) {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.checkPermission(menuName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@RequestMapping(value="/testLogin",method=RequestMethod.POST)
	public String testLogin(@RequestBody UserInfo userInfo) {
		//1.获取当前用户
		Subject currentUser = SecurityUtils.getSubject();
		//2.获取token
		UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
		currentUser.login(token);
		if(currentUser.isAuthenticated()) {
			return "success";
		}else {
			return "unauthorized";
		}
	}
	
}
