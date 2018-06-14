package com.xuzhiwen.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import com.xuzhiwen.entity.UserInfo;

public class AuthorizeTag implements Tag{

	private Tag parent;
    protected PageContext pageContext;
	private String access;
	
	public boolean authorize(){
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		if(AuthorityManager.check(user,access)) {
			return true;
		}
		return false;
	}
	public void setAccess(String access){
		this.access = access;
	}
	
	@Override
	public int doEndTag() throws JspException{
        return 6;
	}

	@Override
	public int doStartTag() throws JspException{
		if(authorize()){
			return 1;
		}
		return 0;
	}
	
	@Override
	public void release(){
		this.parent = null;
	}

	@Override
	public void setPageContext(PageContext pageContext){
		this.pageContext = pageContext;
	}
	
	@Override
	public void setParent(Tag parent){
		this.parent = parent;
	}
	
	@Override
	public Tag getParent(){
		return parent;
	}
}
