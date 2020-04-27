package cn.yfyue.comm;


import cn.SysSpringBootApplication;
import cn.yfyue.sysauth.model.BjLog;
import cn.yfyue.sysauth.service.Log;
import org.springframework.web.context.ContextLoader;
//import org.apache.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class L {
   /* private static Logger logger = Logger.getLogger(L.class);

    public static void debug(String str1) {
        logger.debug(str1);
    }

    public static void info(String str1) {
        logger.info(str1);
    }

    public static void error(String str1) {
        logger.error(str1);
    }

    public static void warn(String str1) {
        logger.warn(str1);
    }

    public static void fatal(String str1) {
        logger.fatal(str1);
    }*/

    /**
     * L.printRequest(Thread.currentThread() .getStackTrace(), request);
     */
//登录日志
    public static void loginLog(ServletRequest request, String loginName,
                                String opFlag) {
        Log log = ContextLoader.getCurrentWebApplicationContext().getBean(Log.class);
        String envi = null;
        String ip = null;
        BjLog bjLog = null;
        try {
            ip = request.getRemoteAddr().toString();
            envi = ((HttpServletRequest) request).getHeader("user-agent").toString();
            bjLog = new BjLog();
            bjLog.setClassMethod("LoginAjax.isLogin");
            bjLog.setLoginName(loginName);
            bjLog.setLogType(1);
            bjLog.setOpFlag(opFlag);
            bjLog.setOpIp(ip);
            bjLog.setOpPara("login_name=" + loginName);
            bjLog.setOpTitle("登录");
            bjLog.setUserEnvi(envi);
            if (log.addLog(bjLog) == 0) {
                System.out.println("loginLog-->[" + F.getLogTime() + "] 登录日志记录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注销
    public static void outLog(ServletRequest request) {
        Log log = SysSpringBootApplication.ac.getBean(Log.class);
        String envi = null;
        String ip = null;
        BjLog bjLog = null;
        HttpSession session = null;
        String loginName = null;
        try {
            if (Auth.isAuth(request)) {
                session = ((HttpServletRequest) request).getSession(false);
                if (session != null && null != session.getAttribute("bjLoginName")
                        && !session.getAttribute("bjLoginName").toString().trim().isEmpty()) {
                    loginName = session.getAttribute("bjLoginName").toString().trim();
                }
                ip = request.getRemoteAddr().toString();
                envi = ((HttpServletRequest) request).getHeader("user-agent").toString();
                bjLog = new BjLog();
                bjLog.setClassMethod("LoginAjax.outLog");
                bjLog.setLoginName(loginName);
                bjLog.setLogType(1);
                bjLog.setOpFlag("注销成功");
                bjLog.setOpIp(ip);
                bjLog.setOpPara(L.getRequestStr(request));
                bjLog.setOpTitle("注销");
                bjLog.setUserEnvi(envi);
                if (log.addLog(bjLog) == 0) {
                    System.out.println("loginLog-->[" + F.getLogTime() + "] 注销日志记录失败");
                }
                session.removeAttribute("bjUserId");
                session.removeAttribute("bjLoginName");
                session.removeAttribute("bjUserName");
                session.removeAttribute("bjAdminLevel");
                session.removeAttribute("bjOrgeId");
                session.removeAttribute("bjOrgeName");
                session.removeAttribute("bjRoleCode");
                session.removeAttribute("bjDataExt");
                session.removeAttribute("bjDataExt2");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //操作日志 Thread.currentThread() .getStackTrace()
    public static void opLog(StackTraceElement[] stackTraceElement, ServletRequest request, String opTitle,
                             String opFlag) {
        Log log = SysSpringBootApplication.ac.getBean(Log.class);
        String envi = null;
        String ip = null;
        BjLog bjLog = null;
        String loginName = null;
        HttpSession session = null;
        try {
            ip = request.getRemoteAddr().toString();
            envi = ((HttpServletRequest) request).getHeader("user-agent").toString();
            session = ((HttpServletRequest) request).getSession(false);
            if (session != null && null != session.getAttribute("bjLoginName")
                    && !session.getAttribute("bjLoginName").toString().trim().isEmpty()) {
                loginName = session.getAttribute("bjLoginName").toString().trim();
            }
            bjLog = new BjLog();
            bjLog.setClassMethod(L.getClassMethodName(stackTraceElement));
            bjLog.setLoginName(loginName);
            bjLog.setLogType(0);
            bjLog.setOpFlag(opFlag);
            bjLog.setOpIp(ip);
            bjLog.setOpPara(L.getRequestStr(request));
            bjLog.setOpTitle(opTitle);
            bjLog.setUserEnvi(envi);
            if (log.addLog(bjLog) == 0) {
                System.out.println("opLog-->[" + F.getLogTime() + "] 操作日志记录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //打印提交参数
    public static void printRequest(StackTraceElement[] stackTraceElement, ServletRequest request) {
        if (stackTraceElement != null) {
            System.out.println(F.getLogTime() + " [" + L.getClassMethodName(stackTraceElement) + "] requestMap:" + getRequestStr(request));
        } else {
            System.out.println(F.getLogTime() + " requestMap:" + getRequestStr(request));
        }

    }

    public static void p(String printStr) {
        System.out.println(F.getLogTime() + "-->" + printStr);
    }

    //取提交参数
    public static String getRequestStr(ServletRequest request) {
        String requestPara = "";
        try {
            if (request != null) {
                Map<String, String[]> params = request.getParameterMap();
                if (params != null) {
                    for (String key : params.keySet()) {
                        String[] values = params.get(key);
                        for (int i = 0; i < values.length; i++) {
                            String value = values[i];
                            requestPara += "[" + key + "=" + F.logStr(value) + "] ";
                        }
                    }
                    // 去掉最后一个空格
                    if (requestPara != null && !requestPara.trim().isEmpty()) {
                        requestPara = requestPara.substring(0, requestPara.length() - 1);
                    }
                } else {
                    requestPara = "无提交参数";
                }
            } else {
                requestPara = "request is null";
            }

        } catch (Exception e) {
            requestPara = e.toString();
            e.printStackTrace();
        }
        return requestPara;
    }

    //获取类名和方法名 Thread.currentThread() .getStackTrace()
    public static String getClassMethodName(StackTraceElement[] stackTraceElement) {
        String classMethodName = "";
        try {
            //Thread.currentThread() .getStackTrace();
            if (stackTraceElement != null && stackTraceElement.length > 0) {
                classMethodName = stackTraceElement[1].getClassName() + "." + stackTraceElement[1].getMethodName();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classMethodName;
    }

}
