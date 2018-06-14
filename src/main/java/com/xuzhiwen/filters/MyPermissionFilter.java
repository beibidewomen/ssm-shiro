package com.xuzhiwen.filters;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuzhiwen.service.LoginService;

public class MyPermissionFilter extends ShiroFilterFactoryBean {

	/** 配置中的过滤链 */
	public static String definitions;
	@Autowired
	LoginService loginService;

	@Override
	public void setFilterChainDefinitions(String definitions) {
		MyPermissionFilter.definitions = definitions;
		List<String> permissions = new ArrayList<>();
		permissions.add("/add");
		permissions.add("/up");

		for (String po : permissions) {
			definitions = definitions + po + " = " + "perms[" + po + "]";
		}
        Ini ini = new Ini();  
        ini.load(definitions);  
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);  
        if (CollectionUtils.isEmpty(section)) {  
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);  
        }  
		setFilterChainDefinitionMap(section);
	}
}
