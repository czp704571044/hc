package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;

import cn.yfyue.sysauth.model.BjButton;
import cn.yfyue.sysauth.model.BjUserButton;
import cn.yfyue.sysauth.model.BjUserFunc;
import cn.yfyue.sysauth.service.Button;
import cn.yfyue.sysauth.service.UserButton;
import cn.yfyue.sysauth.service.UserFun;
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
@RequestMapping("/userFunAjax")
public class UserFunAjax {

    @Autowired
    private UserButton ub;
    @Autowired
    private UserFun uf;
    @Autowired
    private Button button;

    //分配权限
    @RequestMapping("/getEmpower")
    public String getEmpower(@RequestBody String empowerUserId, ServletRequest request) {
        L.printRequest(null, request);
        List<BjUserFunc> myFuncList = null;
        List<BjUserFunc> empowerFuncList = null;
        List<BjUserButton> myButtonList = null;
        List<BjUserButton> empowerButtonList = null;
        List<PowerFunc> pfList = null;
        ReturnJson returnJson = new ReturnJson();
        boolean bfalg = false;
        empowerUserId = F.isNull(empowerUserId);
        try {
            if (Auth.isAuth(request)) {
                if (empowerUserId != null) {
                    if (empowerUserId != null && !Auth.getAuthUserId(request).equals(empowerUserId)) {
                        //取自己的权限

                        myFuncList = uf.getUserFuncList(Auth.getAuthUserId(request), Auth.getAuthUserLevel(request));
                        empowerFuncList = uf.getUserFuncList(empowerUserId, null);
                        //取自己按钮

                        myButtonList = ub.getUserButtonList(Auth.getAuthUserId(request), Auth.getAuthUserLevel(request));
                        empowerButtonList = ub.getUserButtonList(empowerUserId, null);
                        if (myFuncList != null && !myFuncList.isEmpty()) {
                            pfList = new ArrayList<PowerFunc>();
                            for (int i = 0; i < myFuncList.size(); i++) {
                                BjUserFunc myFunc = (BjUserFunc) myFuncList.get(i);
                                if (myFunc != null) {
                                    if (empowerFuncList != null && !empowerFuncList.isEmpty()) {
                                        for (int j = 0; j < empowerFuncList.size(); j++) {
                                            BjUserFunc empowerFunc = (BjUserFunc) empowerFuncList.get(j);
                                            if (empowerFunc != null) {
                                                if (myFunc.getFuncId().equals(empowerFunc.getFuncId())) {
                                                    bfalg = true;
                                                }
                                            }
                                        }
                                    }

                                    PowerFunc pf = new PowerFunc();
                                    pf.setChecked(bfalg);
                                    pf.setFuncAlias(myFunc.getFuncAlias());
                                    pf.setFuncId(myFunc.getFuncId());
                                    pf.setFuncName(myFunc.getFuncName());
                                    pf.setFuncUrl(myFunc.getFuncUrl());
                                    pf.setStyleImg(myFunc.getStyleImg());
                                    pf.setSupperFuncId(myFunc.getSupperFuncId());
                                    pfList.add(pf);
                                    bfalg = false;
                                }
                            }
                            //按钮
                            if (myButtonList != null && !myButtonList.isEmpty()) {
                                for (int k = 0; k < myButtonList.size(); k++) {
                                    BjUserButton myButton = (BjUserButton) myButtonList.get(k);
                                    if (myButton != null) {
                                        if (empowerButtonList != null) {
                                            for (int x = 0; x < empowerButtonList.size(); x++) {
                                                BjUserButton empowerButton = (BjUserButton) empowerButtonList.get(x);
                                                if (empowerButton != null) {
                                                    if (myButton.getButtonId().equals(empowerButton.getButtonId())) {
                                                        bfalg = true;
                                                    }
                                                }
                                            }
                                        }
                                        PowerFunc pf = new PowerFunc();
                                        pf.setChecked(bfalg);
                                        pf.setFuncAlias(myButton.getButtonName());
                                        pf.setFuncId(myButton.getButtonId() + "b");
                                        pf.setFuncName(myButton.getButtonName());
                                        pf.setSupperFuncId(myButton.getFunId());
                                        pfList.add(pf);
                                        bfalg = false;
                                    }
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
                        returnJson.setMessage("对不起!不能给自己分配权限");
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
    @RequestMapping("/addUserFunc")
    public String addUserFunc(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String funcIds = (String) param.get("funcIds");
        String empowerUserId = (String) param.get("empowerUserId");
        L.printRequest(null, request);
        BjUserFunc empowerFunc = null;
        BjUserButton empowerButton = null;
        BjButton buttonInfo = null;
        ReturnJson returnJson = new ReturnJson();
        funcIds = F.isNull(funcIds);
        empowerUserId = F.isNull(empowerUserId);
        String funcArr[] = null;
        String funcId = "";
        try {
            if (Auth.isAuth(request)) {
                if (empowerUserId != null && funcIds != null) {
                    funcArr = funcIds.split(",");
                    if (funcArr != null) {

                        if (uf.delUserIdToUserFunc(empowerUserId) == 0) {
                            L.p("清掉用户功能分配表数据为0");
                        }

                        if (ub.delUserIdToUserButton(empowerUserId) == 0) {
                            L.p("清掉用户功能分配按钮表为0");
                        }
                        for (int i = 0; i < funcArr.length; i++) {
                            funcId = funcArr[i];
                            if (funcId != null && !funcId.isEmpty()) {
                                if (funcId.indexOf("b") != -1) {//按钮
                                    funcId = funcId.replaceAll("b", "");
                                    button = new Button();
                                    buttonInfo = button.getButtonInfo(funcId);
                                    L.p(buttonInfo.toString());
                                    if (buttonInfo != null) {
                                        empowerButton = new BjUserButton();
                                        empowerButton.setFunId("" + buttonInfo.getFunId());
                                        empowerButton.setButtonId("" + buttonInfo.getButtonId());
                                        empowerButton.setUserId(F.strToLong(empowerUserId));
                                        L.p(empowerButton.toString());
                                        if (ub.addUserButton(empowerButton) == 0) {
                                            L.p("添加按钮失败[" + empowerFunc.toString() + "]");
                                        }
                                    }
                                } else { //功能
                                    empowerFunc = new BjUserFunc();
                                    empowerFunc.setFunId(funcId);
                                    empowerFunc.setRegMan(F.strToLong(Auth.getAuthUserId(request)));
                                    empowerFunc.setUserId(empowerUserId);
                                    if (uf.addUserFunc(empowerFunc) == 0) {
                                        L.p("添加权限失败[" + empowerFunc.toString() + "]");
                                    }
                                }
                            }
                        }
                        returnJson.setState(0);
                        returnJson.setMessage("操作权限已全新分配");
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!数据解析错误");
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
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "分配功能", returnJson.getMessage());
        return returnJson.getJson();
    }
}
