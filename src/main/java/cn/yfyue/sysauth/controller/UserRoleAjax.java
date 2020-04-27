package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;

import cn.yfyue.sysauth.model.BjRole;
import cn.yfyue.sysauth.model.BjUserRole;
import cn.yfyue.sysauth.service.Role;
import cn.yfyue.sysauth.service.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/userRoleAjax")
public class UserRoleAjax {
    @Autowired
    private Role role;
    @Autowired
    private UserRole userRole;

    //取出用户的拥有的角色
    @RequestMapping("/getUserRoleList")
    public String getUserRoleList(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        String userId = (String) param.get("userId");
        List<UserRoleCheckbox> userRoleCheckBoxList = null;
        List<BjUserRole> userRoleList = null;
        List<BjRole> allRoleList = null;
        try {
            if (Auth.isAuth(request)) {
                if (userId != null) {
                    allRoleList = role.getAllRoleList(null);
                    userRoleList = userRole.getUserRoleList(userId);
                    if (allRoleList != null && !allRoleList.isEmpty()) {
                        userRoleCheckBoxList = new ArrayList<UserRoleCheckbox>();
                        for (int i = 0; i < allRoleList.size(); i++) {
                            BjRole bjRole = (BjRole) allRoleList.get(i);
                            if (bjRole != null) {
                                UserRoleCheckbox userRoleCheckbox = new UserRoleCheckbox();
                                userRoleCheckbox.setCheckboxChecked(checkbox(userRoleList, "" + bjRole.getRoleId()));
                                userRoleCheckbox.setCheckboxId("" + bjRole.getRoleId());
                                userRoleCheckbox.setCheckboxName(bjRole.getRoleName());
                                userRoleCheckbox.setCheckboxValue("" + bjRole.getRoleId());
                                userRoleCheckbox.setOrgeFullName(bjRole.getOrgeFullName());
                                userRoleCheckbox.setLAY_CHECKED(checkbox_1(userRoleList, "" + bjRole.getRoleId()));
                                userRoleCheckBoxList.add(userRoleCheckbox);
                            }
                        }
                    }
                    if (userRoleCheckBoxList != null && !userRoleCheckBoxList.isEmpty()) {
                        returnJson.setState(0);
                        returnJson.setMessage("系统角色获取成功");
                        returnJson.setObject(userRoleCheckBoxList);
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起！暂未获取到角色配置");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起！数据格式错误");
                }
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //更新用户角色
    @RequestMapping("/upUserRole")
    public String upUserRole(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String userId = (String) param.get("userId");
        String userRoles = (String) param.get("userRoles");
        userId = F.isNull(userId);
        userRoles = F.isNull(userRoles);
        ReturnJson returnJson = new ReturnJson();
        L.p("userId[" + userId + "] userRoles[" + userRoles + "]");
        try {
            if (Auth.isAuth(request)) {
                if (userId != null) {
                    if (userRoles != null && !userRoles.isEmpty()) {
                        String roleArr[] = userRoles.split(",");
                        if (roleArr != null && roleArr.length > 0) {
                            if (userRole.delUserRole(userId) == 0) {
                                L.p("删除用户已分配角色失败");
                            }
                            for (int i = 0; i < roleArr.length; i++) {
                                if (roleArr[i] != null && !roleArr[i].isEmpty()) {
                                    BjUserRole bjUserRole = new BjUserRole();
                                    bjUserRole.setUserId(F.strToLong(userId));
                                    bjUserRole.setRoleId(F.strToLong(roleArr[i]));
                                    //存入数据库
                                    if (userRole.addUserRole(bjUserRole) == 0) {
                                        L.p("用户角色分配数据保存失败 userId[" + userId + "] roleId[" + roleArr[i] + "]");
                                    }
                                }
                            }
                            returnJson.setState(0);
                            returnJson.setMessage("用户角色已更新");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起！角色解析失败");
                        }
                    } else {
                        if (userRole.delUserRole(userId) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("用户角色已全部收回");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起！收回用户角色失败");
                        }
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起!数据格式错误");
                }
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //字符串与LIST值相比较
    public String checkbox(List<BjUserRole> userRoleList, String biDuiValue) {
        String checkValue = "";
        String roleId = null;
        try {
            if (userRoleList != null && !userRoleList.isEmpty()) {
                for (int i = 0; i < userRoleList.size(); i++) {
                    BjUserRole bjUserRole = (BjUserRole) userRoleList.get(i);
                    if (bjUserRole != null) {
                        if (bjUserRole.getRoleId() != null) {
                            roleId = "" + bjUserRole.getRoleId();
                            if (roleId.equals(biDuiValue)) {
                                checkValue = "checked";
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkValue;
    }

    //字符串与LIST值相比较
    public boolean checkbox_1(List<BjUserRole> userRoleList, String biDuiValue) {
        boolean checkValue = false;
        String roleId = null;
        try {
            if (userRoleList != null && !userRoleList.isEmpty()) {
                for (int i = 0; i < userRoleList.size(); i++) {
                    BjUserRole bjUserRole = (BjUserRole) userRoleList.get(i);
                    if (bjUserRole != null) {
                        if (bjUserRole.getRoleId() != null) {
                            roleId = "" + bjUserRole.getRoleId();
                            if (roleId.equals(biDuiValue)) {
                                checkValue = true;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkValue;
    }
}
