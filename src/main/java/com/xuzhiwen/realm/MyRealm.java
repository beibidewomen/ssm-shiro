package com.xuzhiwen.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuzhiwen.entity.UserInfo;
import com.xuzhiwen.service.LoginService;

public class MyRealm extends AuthorizingRealm{
	@Autowired
	private LoginService loginService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username=(String) principals.getPrimaryPrincipal();  
        SimpleAuthorizationInfo  authorizationInfo=new SimpleAuthorizationInfo();  
//        authorizationInfo.setRoles(loginService.getRoles(username));  
//        authorizationInfo.setStringPermissions(loginService.getPermissions(username));  
        System.out.println("授权认证......");
        Set<String> roles = new HashSet<>();
        roles.add("role1");
        Set<String> permissions = new HashSet<>();
        permissions.add("add");
        permissions.add("up");
        authorizationInfo.setRoles(roles);  
        authorizationInfo.setStringPermissions(permissions);  
        return authorizationInfo;  
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.获取用户名
		String username = (String) token.getPrincipal();
		//2.根据用户名获取用户
		UserInfo user = loginService.getUserByUsername(username);
		if(null != user) {
			AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"MyRealm");
			return authenticationInfo;
		}
		return null;
	}

}
