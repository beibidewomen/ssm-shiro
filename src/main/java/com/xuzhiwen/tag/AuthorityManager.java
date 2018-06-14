package com.xuzhiwen.tag;

import com.xuzhiwen.entity.UserInfo;

public class AuthorityManager{
	public static boolean check(UserInfo user, String access) {
//		if(WebParamsListener.map.get(user.getUserId()).contains(access)) {
//			return true;
//		}
		return true;
	}
}
