package cn.yfyue.yyzx.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;


import cn.yfyue.sysauth.controller.CodeToStr;
import cn.yfyue.yyzx.model.OpUser;
import cn.yfyue.yyzx.service.OUser;
import cn.yfyue.yyzx.service.TransOpOrgeUser;
import cn.yfyue.yyzx.utils.OrgeUserWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;


@RequestMapping("/opUserAjax")
@RestController
public class OpUserAjax {

    @Autowired
    private OUser user;

    //查询门店账户列表
    @RequestMapping("/getMdUserList")
    public String getMdUserList(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String orgeId = (String) param.get("orgeId");
        String qkey = (String) param.get("qkey");
        String page = (String) param.get("page");
        String rows = (String) param.get("rows");
        ReturnJson returnJson = new ReturnJson();
        List dataList = null;
        orgeId = F.isNull(orgeId);
        qkey = F.isNull(qkey);
        int nPage = F.fPage(page);
        int nRowNum = F.fRow(rows);
        try {
            if (Auth.isAuth(request)) {
                // 取最大行数
                dataList = user.getUserList(orgeId, qkey, (nPage - 1) * nRowNum, nRowNum);
                if (dataList != null && !dataList.isEmpty()) {
                    for (int i = 0; i < dataList.size(); i++) {
                        OpUser opUser = (OpUser) dataList.get(i);
                        opUser.setUserStatusStr(CodeToStr.bjUserToState("" + opUser.getUserStatus()));
                    }
                }
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataList);
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //门店账户列表统计
    @RequestMapping("/getMdUserListCount")
    public String getMdUserListCount(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String orgeId = (String) param.get("orgeId");
        String qkey = (String) param.get("qkey");
        ReturnJson returnJson = new ReturnJson();
        int dataTotalRowNum = 0;
        qkey = F.isNull(qkey);
        try {
            if (Auth.isAuth(request)) {
                // 取最大行数
                dataTotalRowNum = user.getUserListCount(orgeId, qkey);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataTotalRowNum);
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录后,再操作...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //添加门店账户
    @RequestMapping("/addMdUser")
    public String addMdUser(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String loginName = (String) param.get("loginName");
        String passWord = (String) param.get("passWord");
        String passWord2 = (String) param.get("passWord2");
        String userName = (String) param.get("userName");
        String mobileNo = (String) param.get("mobileNo");
        String orgeFullName = (String) param.get("orgeFullName");
        String orgeName = (String) param.get("orgeName");
        String orgeType = (String) param.get("orgeType");
        String linkMan = (String) param.get("linkMan");
        String orgeTel = (String) param.get("orgeTel");
        String email = (String) param.get("email");
        String orgeAddr = (String) param.get("orgeAddr");
        loginName = F.isNull(loginName);
        passWord = F.isNull(passWord);
        passWord2 = F.isNull(passWord2);
        userName = F.isNull(userName);
        mobileNo = F.isNull(mobileNo);
        orgeFullName = F.isNull(orgeFullName);
        orgeName = F.isNull(orgeName);
        orgeType = F.isNull(orgeType);
        linkMan = F.isNull(linkMan);
        orgeTel = F.isNull(orgeTel);
        email = F.isNull(email);
        orgeAddr = F.isNull(orgeAddr);
        OrgeUserWrap orgeUserWrap = new OrgeUserWrap();
        orgeUserWrap.setLoginName(loginName);
        orgeUserWrap.setPassWord(passWord);
        orgeUserWrap.setPassWord2(passWord2);
        orgeUserWrap.setUserName(userName);
        orgeUserWrap.setMobileNo(mobileNo);
        orgeUserWrap.setOrgeFullName(orgeFullName);
        orgeUserWrap.setOrgeName(orgeName);
        orgeUserWrap.setOrgeType(orgeType);
        orgeUserWrap.setLinkMan(linkMan);
        orgeUserWrap.setOrgeTel(orgeTel);
        orgeUserWrap.setEmail(email);
        orgeUserWrap.setOrgeAddr(orgeAddr);
        TransOpOrgeUser opOrgeUserTrans = ContextLoader.getCurrentWebApplicationContext().getBean(TransOpOrgeUser.class);
        return opOrgeUserTrans.addOrgeUser(orgeUserWrap, request);
    }

    //启用账户
    @RequestMapping("/enabledUser")
    public String enabledUser(@RequestBody String userId, ServletRequest request) {
        L.printRequest(Thread.currentThread().getStackTrace(), request);
        ReturnJson returnJson = new ReturnJson();
        userId = F.isNull(userId);
        OpUser opUser = null;
        try {
            if (Auth.isAuth(request)) {
                opUser = user.getUserInfo(userId);
                if (opUser != null) {
                    if (opUser.getUserStatus() == 1) {
                        if (user.enabledUser(userId) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("账户开启成功");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起,帐户开启失败");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起,帐户是开启状态");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起,获取账户信息失败");
                }
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录后,再操作...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "启动账户", returnJson.getMessage());
        return returnJson.getJson();
    }

    //禁用账户
    @RequestMapping("/disabledUser")
    public String disabledUser(@RequestBody String userId, ServletRequest request) {
        L.printRequest(Thread.currentThread().getStackTrace(), request);
        ReturnJson returnJson = new ReturnJson();
        userId = F.isNull(userId);
        OpUser opUser = null;
        try {
            if (Auth.isAuth(request)) {
                opUser = user.getUserInfo(userId);
                if (opUser != null) {
                    if (opUser.getUserStatus() == 0) {
                        if (user.disabledUser(userId) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("账户已禁止");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起,帐户禁止失败");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起,帐户是禁止状态");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起,获取账户信息失败");
                }
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录后,再操作...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "禁用账户", returnJson.getMessage());
        return returnJson.getJson();
    }

    //初始化密码
    @RequestMapping("/initPasswd")
    public String initPasswd(@RequestBody String userId, ServletRequest request) {
        L.printRequest(Thread.currentThread().getStackTrace(), request);
        ReturnJson returnJson = new ReturnJson();
        userId = F.isNull(userId);
        OpUser opUser = null;
        String initPasswd = "123456";
        try {
            if (Auth.isAuth(request)) {
                opUser = user.getUserInfo(userId);
                if (opUser != null) {
                    if (user.initPasswd(userId, F.md5Passwd(initPasswd)) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("账户:" + opUser.getLoginName() + " 密码初绐化为" + initPasswd);
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起,账户:" + opUser.getLoginName() + " 密码初绐化失败");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起,获取账户信息失败");
                }
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录后,再操作...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "重置密码", returnJson.getMessage());
        return returnJson.getJson();
    }

    //取账户信息
    @RequestMapping("/getUserInfo")
    public String getUserInfo(@RequestBody String userId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        userId = F.isNull(userId);
        OpUser opUser = null;
        try {
            if (Auth.isAuth(request)) {
                opUser = user.getUserInfo(userId);
                if (opUser != null) {
                    returnJson.setState(0);
                    returnJson.setMessage("操作成功");
                    returnJson.setObject(opUser);
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起,获取账户信息失败");
                }
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录后,再操作...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //更新帐户
    @RequestMapping("/upUser")
    public String upUser(@RequestBody HashMap<String, Object> param, ServletRequest request) {

        String userId = (String) param.get("userId");
        String userName = (String) param.get("userName");
        String mobileNo = (String) param.get("mobileNo");
        ReturnJson returnJson = new ReturnJson();
        userId = F.isNull(userId);
        userName = F.isNull(userName);
        mobileNo = F.isNull(mobileNo);
        OpUser opUser = null;
        try {
            if (Auth.isAuth(request)) {
                if (userId != null) {
                    opUser = new OpUser();
                    opUser.setUserId(userId);
                    opUser.setUserName(userName);
                    opUser.setMobileNo(mobileNo);
                    if (user.upUser(opUser) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("账户信息已更新");
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!数据保存失败");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起!数据格式错误");
                }
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录后,再操作...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "更新账户", returnJson.getMessage());
        return returnJson.getJson();
    }

    //账号解锁
    @RequestMapping("/clearErr")
    public String clearErr(@RequestBody String userId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        userId = F.isNull(userId);
        OpUser opUser = null;
        try {
            if (Auth.isAuth(request)) {

                opUser = user.getUserInfo(userId);
                if (opUser != null) {
                    if (user.loginErrClear(opUser.getLoginName()) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("登录名为:" + opUser.getLoginName() + " 账号已解锁");
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起,登录名为:" + opUser.getLoginName() + " 账号解锁失败");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起,获取账户信息失败");
                }
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录后,再操作...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "账号解锁", returnJson.getMessage());
        return returnJson.getJson();
    }

}