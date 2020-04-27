package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;

import cn.yfyue.sysauth.model.BjRoleFun;
import cn.yfyue.sysauth.service.RoleFun;
import cn.yfyue.sysauth.utils.PowerFunc;
import cn.yfyue.sysauth.utils.PowerFuncUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/roleFunAjax")
public class RoleFunAjax {

    @Autowired
    private RoleFun rolefun;

    @RequestMapping("/getRoleFuncTree")
    public String getRoleFuncTree(@RequestBody String roleId, ServletRequest request) {
        L.p("getRoleFuncTree");
        L.printRequest(null, request);
        List<BjRoleFun> allRoleFunList = null;
        List<BjRoleFun> empowerRoleFunList = null;
        List<PowerFunc> pfList = null;
        ReturnJson returnJson = new ReturnJson();
        boolean bfalg = false;
        roleId = F.isNull(roleId);
        try {
            if (Auth.isAuth(request)) {
                if (roleId != null) {
                    //取自己的权限
                    List<String> roleList = new ArrayList<String>();
                    roleList.add(roleId);

                    allRoleFunList = rolefun.getAllFuncList();
                    //empowerRoleFunList=rolefun.getRoleFuncList(roleCode);
                    empowerRoleFunList = rolefun.getRoleFuncList(roleList);
                    //取自己按钮
                    if (allRoleFunList != null && !allRoleFunList.isEmpty()) {
                        pfList = new ArrayList<PowerFunc>();
                        for (int i = 0; i < allRoleFunList.size(); i++) {
                            BjRoleFun allRoleFun = (BjRoleFun) allRoleFunList.get(i);
                            if (allRoleFun != null) {
                                if (empowerRoleFunList != null && !empowerRoleFunList.isEmpty()) {
                                    for (int j = 0; j < empowerRoleFunList.size(); j++) {
                                        BjRoleFun empowerRoleFunc = (BjRoleFun) empowerRoleFunList.get(j);
                                        if (empowerRoleFunc != null) {
                                            if (allRoleFun.getFunId().equals(empowerRoleFunc.getFunId())) {
                                                bfalg = true;
                                            }
                                        }
                                    }
                                }

                                PowerFunc pf = new PowerFunc();
                                pf.setChecked(bfalg);
                                pf.setFuncAlias(allRoleFun.getFuncAlias());
                                pf.setFuncId(allRoleFun.getFunId());
                                pf.setFuncName(allRoleFun.getFuncName());
                                pf.setFuncUrl(allRoleFun.getFuncUrl());
                                pf.setStyleImg(allRoleFun.getStyleImg());
                                pf.setSupperFuncId(allRoleFun.getSupperFuncId());
                                pfList.add(pf);
                                bfalg = false;
                            }
                        }
                        if (pfList != null && !pfList.isEmpty()) {
                            returnJson.setState(0);
                            returnJson.setMessage("操作成功");
                            returnJson.setObject(PowerFuncUtil.getPowerFunc(pfList));
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起,您暂未获得可分配权限");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起,您暂未获得可分配权限,不能分配");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起!数据格式错误");
                }
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //添加分配
    @RequestMapping("/addRoleFunc")
    public String addRoleFunc(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String funcIds = (String) param.get("funcIds");
        String roleCode = (String) param.get("roleCode");
        L.printRequest(null, request);
        BjRoleFun empowerRoleFunc = null;
        ReturnJson returnJson = new ReturnJson();
        funcIds = F.isNull(funcIds);
        String roleId = F.isNull(roleCode);
        String funcArr[] = null;
        String funcId = "";
        try {
            if (Auth.isAuth(request)) {
                if (roleId != null) {
                    if (funcIds != null && !funcIds.isEmpty()) {
                        funcArr = funcIds.split(",");
                        if (funcArr != null) {
                            if (rolefun.delRoleIdToRoleFun(roleId) == 0) {
                                L.p("清掉角色功能分配表数据为0");
                            }
                            for (int i = 0; i < funcArr.length; i++) {
                                funcId = funcArr[i];
                                if (funcId != null && !funcId.isEmpty()) {
                                    empowerRoleFunc = new BjRoleFun();
                                    empowerRoleFunc.setFunId(funcId);
                                    empowerRoleFunc.setRoleId(roleId);
                                    empowerRoleFunc.setRegMan(F.strToLong(Auth.getAuthUserId(request)));
                                    if (rolefun.addRoleFun(empowerRoleFunc) == 0) {
                                        L.p("添加角色权限失败[" + empowerRoleFunc.toString() + "]");
                                    }
                                }
                            }
                            returnJson.setState(0);
                            returnJson.setMessage("角色权限已全新分配");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!数据解析错误");
                        }
                    } else {
                        if (rolefun.delRoleIdToRoleFun(roleId) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("角色所有权限已收回");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("收回角色权限失败");
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
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "设置角色功能", returnJson.getMessage());
        return returnJson.getJson();
    }
}
