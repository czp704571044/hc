package cn.yfyue.comm;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Auth {
// 用户SESSION失败
public static boolean isAuth(ServletRequest request) {
	boolean bflag = false;
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjUserId")
					&& !session.getAttribute("bjUserId").toString().trim()
							.isEmpty()) {
				bflag = true;
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return bflag;
}

// 取用户ID
public static String getAuthUserId(ServletRequest request) {
	String bjUserId = null;
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjUserId")
					&& !session.getAttribute("bjUserId").toString().trim()
							.isEmpty()) {
				bjUserId = session.getAttribute("bjUserId").toString()
						.trim();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return bjUserId;
}
//取用户姓名
public static String getAuthUserName(ServletRequest request) {
	String userName = "";
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjUserName")
					&& !session.getAttribute("bjUserName").toString()
							.trim().isEmpty()) {
				userName = session.getAttribute("bjUserName").toString()
						.trim();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return userName;
}
// 取用户登录名
public static String getAuthLoginName(ServletRequest request) {
	String login_nam = null;
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjLoginName")
					&& !session.getAttribute("bjLoginName").toString()
							.trim().isEmpty()) {
				login_nam = session.getAttribute("bjLoginName").toString()
						.trim();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return login_nam;
}

// 用当前用户级别
public static String getAuthUserLevel(ServletRequest request) {
	String admin_level = null;
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjAdminLevel")
					&& !session.getAttribute("bjAdminLevel").toString().trim().isEmpty()) {
				admin_level = session.getAttribute("bjAdminLevel").toString().trim();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return admin_level;
}

//取角色编码
public static String getAuthRoleCode(ServletRequest request) {
	String roleCode = null;
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjRoleCode")
					&& !session.getAttribute("bjRoleCode").toString().trim().isEmpty()) {
				roleCode = session.getAttribute("bjRoleCode").toString().trim();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return roleCode;
}
//取组织ID
public static String getAuthOrgeId(ServletRequest request) {
	String orgeId = null;
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjOrgeId")
					&& !session.getAttribute("bjOrgeId").toString().trim().isEmpty()) {
				orgeId = session.getAttribute("bjOrgeId").toString().trim();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return orgeId;
}
//取扩展参数1
public static String getAuthDataExt1(ServletRequest request) {
	String dataExt = null;
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjDataExt")
					&& !session.getAttribute("bjDataExt").toString().trim().isEmpty()) {
				dataExt = session.getAttribute("bjDataExt").toString().trim();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return dataExt;
}
public static String getAuthDataExt2(ServletRequest request) {
	String dataExt = null;
	HttpSession session = null;
	try {
		session = ((HttpServletRequest) request).getSession(false);
		if (session != null) {
			if (null != session.getAttribute("bjDataExt2")
					&& !session.getAttribute("bjDataExt2").toString().trim().isEmpty()) {
				dataExt = session.getAttribute("bjDataExt2").toString().trim();
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return dataExt;
}
	
}
