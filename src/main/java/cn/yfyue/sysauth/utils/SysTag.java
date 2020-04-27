//package cn.yfyue.sysauth.utils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.BodyTagSupport;
//
//public class SysTag extends BodyTagSupport{
//	public int doEndTag() throws JspException {
//		// TODO Auto-generated method stub
//		String content = this.getBodyContent().getString();
//		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
//		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
//		JspWriter out = pageContext.getOut();
//		try {
//			if (content.trim().equalsIgnoreCase("funcList")) {
//				funcList(out, request, response);
//			} else if (content.trim().equalsIgnoreCase("isLogin")) {
//				isLogin(out, request, response);
//			}
//			else if (content.trim().equalsIgnoreCase("buttonList")) {
//				buttonList(out, request, response);
//			}
//			else if (content.trim().equalsIgnoreCase("isPage")) {
//				isPage(out, request, response);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return super.doEndTag();
//	}
//
////功能列表
//public void funcList(JspWriter out, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//	String userId=null;
//	String adminLevel=null;
//	String roleCode=null;
//	if (request.getSession().getAttribute("bjUserId") != null
//			&& !request.getSession().getAttribute("bjUserId").toString().equals("")) {
//		userId=request.getSession().getAttribute("bjUserId").toString();
//		adminLevel=request.getSession().getAttribute("bjAdminLevel").toString();
//		roleCode=request.getSession().getAttribute("bjRoleCode").toString();
//		try{
//			out.print(TagBll.funcTagHtml(userId, adminLevel));
//			return;
//		}catch(Exception e){e.printStackTrace();}
//	} else{
//		return;
//	}
//}
////验证是否登录
//public void isLogin(JspWriter out, HttpServletRequest request,
//	HttpServletResponse response) throws Exception {
//	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
//	if (request.getSession().getAttribute("bjUserId") == null
//			|| request.getSession().getAttribute("bjUserId").toString().equals("")) {
//		response.sendRedirect(basePath+"error.html");
//		return;
//	}
//}
//
////页面权限
//public void isPage(JspWriter out, HttpServletRequest request,
//		HttpServletResponse response) throws Exception {
//	String resourceId=null,bjUserId=null,bjAdminLevel=null;
//	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
//	if (request.getSession().getAttribute("bjUserId") != null
//			 && !request.getSession().getAttribute("bjUserId").toString().equals("")) {
//
//		   bjAdminLevel=request.getSession().getAttribute("bjAdminLevel").toString().trim();
//		   bjUserId=request.getSession().getAttribute("bjUserId").toString().trim();
//			//L.p("bjAdminLevel["+bjAdminLevel+"] bjUserId["+bjUserId+"] resourceId["+resourceId+"]");
//		if(request.getParameter("resourceId")!=null
//				&& !request.getParameter("resourceId").toString().trim().equals("")){
//			resourceId=request.getParameter("resourceId").toString().trim();
//			if(TagBll.isPage(bjUserId, resourceId,bjAdminLevel)){
//				request.getSession().setAttribute("resourceId", resourceId);
//				return;
//			}else{
//				response.sendRedirect(basePath+"invalid.html");
//				return;
//			}
//		}else{
//			if (request.getSession().getAttribute("resourceId") != null
//					 && !request.getSession().getAttribute("resourceId").toString().equals("")) {
//
//				resourceId=request.getSession().getAttribute("resourceId").toString().trim();
//				if(TagBll.isPage(bjUserId, resourceId,bjAdminLevel)){
//					request.getSession().setAttribute("resourceId", resourceId);
//					return;
//				}else{
//					response.sendRedirect(basePath+"invalid.html");
//					return;
//				}
//			}else{
//				//L.p("3333333");
//				response.sendRedirect(basePath+"invalid.html");
//				return;
//			}
//		}
//	}else{
//		response.sendRedirect(basePath+"invalid.html");
//		return;
//	}
//}
//
////用户页面按钮
//public void buttonList(JspWriter out, HttpServletRequest request,
//	HttpServletResponse response) throws Exception {
//	String resourceId=null,bjUserId=null,bjAdminLevel=null;
//    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
//    String buttonHtmlStr="";
//    if (request.getSession().getAttribute("bjUserId") != null
//		&& !request.getSession().getAttribute("bjUserId").toString().isEmpty()) {
//    	bjAdminLevel=request.getSession().getAttribute("bjAdminLevel").toString().trim();
//		   bjUserId=request.getSession().getAttribute("bjUserId").toString().trim();
//			if(request.getParameter("resourceId")!=null
//					&& !request.getParameter("resourceId").toString().trim().isEmpty()){
//				resourceId=request.getParameter("resourceId").toString().trim();
//				buttonHtmlStr=TagBll.getButtonHtml(bjUserId, resourceId, bjAdminLevel);
//				out.print(buttonHtmlStr);
//				return;
//			}else{
//				if (request.getSession().getAttribute("resourceId") != null
//						 && !request.getSession().getAttribute("resourceId").toString().equals("")) {
//					resourceId=request.getSession().getAttribute("resourceId").toString().trim();
//					buttonHtmlStr=TagBll.getButtonHtml(bjUserId, resourceId, bjAdminLevel);
//					out.print(buttonHtmlStr);
//					return;
//				}else{
//					out.print("");
//					return;
//				}
//			}
//		}else{
//			out.print("");
//			return;
//		}
//}
//}
