package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;

import cn.yfyue.sysauth.model.BjRole;
import cn.yfyue.sysauth.service.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sysRoleAjax")
public class SysRoleAjax {
    @Autowired
    private Role role;

    //列表
    @RequestMapping("/getRoleList")
    public String getRoleList(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String qkey = (String) param.get("qkey");
        String page = (String) param.get("page");
        String rows = (String) param.get("rows");

        List dataList = null;
        ReturnJson returnJson = new ReturnJson();
        int nPage = F.fPage(page);
        int nRowNum = F.fRow(rows);
        qkey = F.isNull(qkey);
        try {
            if (Auth.isAuth(request)) {
                // 取最大行数
                dataList = role.getRoleList(qkey, (nPage - 1) * nRowNum, nRowNum);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataList);
            } else {
                returnJson.setState(-977);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //统计
    @RequestMapping("/getRoleListNum")
    public String getRoleListNum(@RequestBody String qkey, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        int dataTotalRowNum = 0;
        qkey = F.isNull(qkey);
        try {
            if (Auth.isAuth(request)) {
                // 取最大行数
                dataTotalRowNum = role.getRoleListNum(qkey);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataTotalRowNum);
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //添加角色
    @RequestMapping("/addRole")
    public String addRole(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String roleName = (String) param.get("roleName");
        String roleKey = (String) param.get("roleKey");
        ReturnJson returnJson = new ReturnJson();
        roleName = F.isNull(roleName);
        roleKey = F.isNull(roleKey);
        try {
            if (Auth.isAuth(request)) {
                if (roleName != null) {
                    BjRole bjRole = new BjRole();
                    bjRole.setRoleKey(roleKey);
                    bjRole.setRoleName(roleName);
                    if (role.addRole(bjRole) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("角色添加成功");
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("角色数据保存失败");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "添加角色", returnJson.getMessage());
        return returnJson.getJson();
    }

    //删除角色
    @RequestMapping("/delRole")
    public String delRole(@RequestBody String roleId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        roleId = F.isNull(roleId);
        try {
            if (Auth.isAuth(request)) {
                if (roleId != null) {
                    if (role.isRoleToUser(roleId) == 0) {
                        if (role.isFuncToRole(roleId) == 0) {
                            if (role.delRole(roleId) > 0) {
                                returnJson.setState(0);
                                returnJson.setMessage("角色已删除");
                            } else {
                                returnJson.setState(-1);
                                returnJson.setMessage("角色删除失败");
                            }
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("请先删除掉角色分配的功能");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("角色已分配给用户，请先收回");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "删除角色", returnJson.getMessage());
        return returnJson.getJson();
    }

}
