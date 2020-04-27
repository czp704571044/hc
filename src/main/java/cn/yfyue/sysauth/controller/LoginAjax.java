package cn.yfyue.sysauth.controller;


import cn.yfyue.comm.*;
import cn.yfyue.sysauth.model.BjUser;
import cn.yfyue.sysauth.service.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;


@RestController
@RequestMapping("/loginAjax")
public class LoginAjax {

    @Autowired
    private Login login;

    //登录
    @RequestMapping("/isLogin")
    public String isLogin(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        L.printRequest(null, request);
        HttpSession session = null;
        BjUser bjUser = null;
        ReturnJson returnJson = new ReturnJson();
        String login_name = (String) param.get("login_name");
        String pass_word = (String) param.get("pass_word");
        try {
            session = ((HttpServletRequest) request).getSession(true);
            if (true) {
                //if (session!=null && session.getAttribute("DynamicValidateNum").toString().equals(vcode)) {
                if (login_name != null && pass_word != null) {
                    bjUser = login.getUserLoginInfo(login_name);
                    if (bjUser != null) {
                        if (bjUser.getErrNum() < SetSys.passwdErrNum) {
                            //正常登录
                            if (login.isLogin(login_name, F.md5Passwd(pass_word)) == 1) {
                                session.setAttribute("bjUserId", "" + bjUser.getUserId());
                                session.setAttribute("bjLoginName", "" + bjUser.getLoginName());
                                session.setAttribute("bjUserName", "" + bjUser.getUserName());
                                session.setAttribute("bjAdminLevel", "" + bjUser.getAdminLevel());
                                session.setAttribute("bjOrgeId", "" + bjUser.getOrgeId());
                                session.setAttribute("bjOrgeName", "" + bjUser.getOrgeName());
                                session.setAttribute("bjRoleCode", "" + bjUser.getRoleCode());
                                session.setAttribute("bjDataExt", "" + bjUser.getDataExt());
                                session.setAttribute("bjDataExt2", "" + bjUser.getDataExt2());
                                //清掉错误
                                if (login.loginErrClear(login_name) == 0) {
                                    L.p("清除登录错误次数失败");
                                }
                                returnJson.setState(0);
                                returnJson.setMessage("登录成功");
                            } else {
                                if (login.loginErr(login_name) == 0) {
                                    L.p("登录错误次数加1失败");
                                }
                                returnJson.setState(-1);
                                returnJson.setMessage("用户名或密码错误");
                            }
                        } else {
                            //判断时间
                            Date dbTime = login.getDbTime();
                            if (F.getDateDifferMin(dbTime, bjUser.getErrTime()) > 60) {
                                //登录
                                if (login.isLogin(login_name, F.md5Passwd(pass_word)) == 1) {
                                    session.setAttribute("bjUserId", "" + bjUser.getUserId());
                                    session.setAttribute("bjLoginName", "" + bjUser.getLoginName());
                                    session.setAttribute("bjUserName", "" + bjUser.getUserName());
                                    session.setAttribute("bjAdminLevel", "" + bjUser.getAdminLevel());
                                    session.setAttribute("bjOrgeId", "" + bjUser.getOrgeId());
                                    session.setAttribute("bjOrgeName", "" + bjUser.getOrgeName());
                                    session.setAttribute("bjRoleCode", "" + bjUser.getRoleCode());
                                    session.setAttribute("bjDataExt", "" + bjUser.getDataExt());
                                    session.setAttribute("bjDataExt2", "" + bjUser.getDataExt2());
                                    //清掉错误
                                    if (login.loginErrClear(login_name) == 0) {
                                        L.p("清除登录错误次数失败");
                                    }
                                    returnJson.setState(0);
                                    returnJson.setMessage("登录成功");
                                } else {
                                    if (login.loginErr(login_name) == 0) {
                                        L.p("登录错误次数加1失败");
                                    }
                                    returnJson.setState(-1);
                                    returnJson.setMessage("用户名或密码错误");
                                }
                            } else {
                                returnJson.setState(-1);
                                returnJson.setMessage("对不起！账号锁定，请60分钟后再登录");
                            }
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起！用户名或密码错误");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("请输入用户名和密码");
                }
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("验证码错误");
            }
        } catch (Exception e) {
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        //记录日志
        //L.loginLog(request, login_name, returnJson.getMessage());
        return returnJson.getJson();
    }

    //退出
    @RequestMapping("/logout")
    public void logout(ServletRequest request, HttpServletResponse response) {
        try {
            L.outLog(request);
            response.sendRedirect("/index/login");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //取当前用户名称
    @RequestMapping("/getLoginUserName")
    public String getLoginUserName(ServletRequest request) {
        //L.printRequest(Thread.currentThread() .getStackTrace(), request);
        ReturnJson returnJson = new ReturnJson();
        try {
            if (Auth.isAuth(request)) {
                returnJson.setState(0);
                returnJson.setObject(Auth.getAuthUserName(request));
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请重新登录");
            }
        } catch (Exception e) {
            returnJson.setState(-999);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        //记录日志
        return returnJson.getJson();
    }
}
