package com.xuzhiwen.untils;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.xuzhiwen.entity.UserInfo;

public class MD5Until {

	public static String getPassword(UserInfo user) {
		Md5Hash pass = new Md5Hash(user.getPassword(),user.getUsername(),3);
		return pass.toString();
	}

}
