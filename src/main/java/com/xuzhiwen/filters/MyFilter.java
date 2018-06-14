package com.xuzhiwen.filters;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuzhiwen.service.LoginService;

public class MyFilter extends AccessControlFilter {
	public static Set<String> permissionsSet = new HashSet<>();
	
	@Autowired
	private LoginService loginService;
	
	private String errorUrl;

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object arg2) throws Exception {
		Subject subject = getSubject(servletRequest, servletResponse);
		String url = getPathWithinApplication(servletRequest);
		String username = (String) subject.getPrincipal();
		System.out.println("当前用户名:"+subject.getPrincipal());
		setErrorUrl(url);
		System.out.println(("当前用户正在访问的 url => " + url));
		Set<String> permissions = null;
		if(permissionsSet != null && permissionsSet.size() > 0) {
			permissions = permissionsSet;
			System.out.println("从缓存中获取数据");
		}else {
			permissions = loginService.getPermissions(username);
			permissionsSet = permissions;
			System.out.println("从数据库中获取数据");
		}
//		Set<String> permissions = new HashSet<>();
//		permissions.add("/add");
//		permissions.add("/up");
		return permissions.contains(url);
//		return subject.isPermitted(url);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
		System.out.println("无权限运行该方法... ");
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        response.sendRedirect(request.getContextPath() + "/unauthorized");
        System.out.println(request.getContextPath() + this.errorUrl);
        // 返回 false 表示已经处理，例如页面跳转啥的，表示不在走以下的拦截器了（如果还有配置的话）
        return false;
	}

}
