package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.*;

import cn.yfyue.sysauth.model.BjOrge;
import cn.yfyue.sysauth.model.BjOrgeLayUi;
import cn.yfyue.sysauth.service.Orge;
import cn.yfyue.sysauth.utils.OrgeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/sysOrgeAjax")
public class SysOrgeAjax {

    @Autowired
    private Orge orge;

    // 取列表
    @RequestMapping("/getOrgeList")
    public String getOrgeList(ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        List<BjOrge> dataList = null;
        BjOrge bjOrge = null;
        try {
            if (Auth.isAuth(request)) {

                dataList = orge.getOrgeList(null);
                if (dataList != null && !dataList.isEmpty()) {
                    for (int i = 0; i < dataList.size(); i++) {
                        bjOrge = (BjOrge) dataList.get(i);
                        if (bjOrge != null) {
                            if (bjOrge.getOrgeType() != null) {
                                bjOrge.setOrgeType(SetSys.initCodeToName(bjOrge.getOrgeType()));

//						   if(bjOrge.getOrgeState().equals("1")){
//							   bjOrge.setOrgeState("隐藏");
//						   }else if(bjOrge.getOrgeState().equals("0")){
//							   bjOrge.setOrgeState("显示");
//						   }else{
//							   bjOrge.setOrgeState("--");
//						   }

                            }
                        }
                    }
                }
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(OrgeUtil.getOrgeChildNodes(dataList));
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        return returnJson.getJson();
    }


    @RequestMapping("/getLayUiOrgeList")
    public String getLayUiOrgeList(ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        List<BjOrgeLayUi> dataList = null;
        try {
            if (Auth.isAuth(request)) {
                dataList = queryOrgerList(0);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataList);
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    private List<BjOrgeLayUi> queryOrgerList(Integer pid) {
        List<BjOrgeLayUi> list = orge.getLayUiOrgeList(pid);
        for (BjOrgeLayUi bjOrgeLayUi : list) {
            List<BjOrgeLayUi> layUis = queryOrgerList(bjOrgeLayUi.getId());
            if (layUis != null && !layUis.isEmpty()) {
                bjOrgeLayUi.setChildren(layUis);
            } else {
                break;
            }
        }
        return list;
    }

    //取列表
    @RequestMapping("/getOpenUserOrgeList")
    public String getOpenUserOrgeList(
            ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        List<BjOrge> dataList = null;
        try {
            if (Auth.isAuth(request)) {

                dataList = orge.getOrgeList("0");
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(OrgeUtil.getOrgeChildNodes(dataList));
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //添加
    @RequestMapping("/addOrge")
    public String addOrge(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String superiorOrgeId = (String) param.get("superiorOrgeId");
        String orgeName = (String) param.get("orgeName");
        String orgeFullName = (String) param.get("orgeFullName");
        String orgeAddr = (String) param.get("orgeAddr");
        String orgeTel = (String) param.get("orgeTel");
        String email = (String) param.get("email");
        String linkMan = (String) param.get("linkMan");
        ReturnJson returnJson = new ReturnJson();
        superiorOrgeId = F.isNull(superiorOrgeId);
        orgeName = F.isNull(orgeName);
        //orgeState=F.isNull(orgeState);
        orgeFullName = F.isNull(orgeFullName);
        orgeAddr = F.isNull(orgeAddr);
        orgeTel = F.isNull(orgeTel);
        email = F.isNull(email);
        linkMan = F.isNull(linkMan);
        BjOrge bjOrge = null;
        BjOrge superiorOrge = null;
        String levelStr = "1";
        int nOpFlag = 0;
        try {
            if (Auth.isAuth(request)) {
                if (superiorOrgeId != null && orgeName != null) {

                    if (superiorOrgeId != null && superiorOrgeId.equals("0")) {
                        superiorOrgeId = "0";
                        levelStr = "1";
                        nOpFlag = 0;
                    } else {
                        superiorOrge = orge.getOrgeInfo(superiorOrgeId);
                        if (superiorOrge != null) {
                            if (superiorOrge.getOrgeLevel() != null) {
                                //L.p(""+(superiorOrge.getOrgeLevel()+1));
                                if ((superiorOrge.getOrgeLevel() + 1) <= 4) {
                                    levelStr = "" + (superiorOrge.getOrgeLevel() + 1);
                                    superiorOrgeId = superiorOrge.getOrgeId();
                                    nOpFlag = 0;
                                } else {
                                    nOpFlag = -1;
                                    returnJson.setState(-1);
                                    returnJson.setMessage("对不起!只支持4层结构");
                                }
                            } else {
                                nOpFlag = -1;
                                returnJson.setState(-1);
                                returnJson.setMessage("对不起!获取数据异常");
                            }
                        } else {
                            nOpFlag = -1;
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!获取数据失败");
                        }
                    }
                    if (nOpFlag == 0) {
                        //入库
                        bjOrge = new BjOrge();
                        bjOrge.setOrgeLevel(F.StrToInt(levelStr));
                        bjOrge.setOrgeName(orgeName);
                        bjOrge.setOrgeState("0");
                        bjOrge.setSuperiorOrgeId(superiorOrgeId);
                        bjOrge.setUserId(Auth.getAuthUserId(request));
                        bjOrge.setOrgeType(superiorOrge.getOrgeType());
                        //bjOrge.setOrgeFullName(superiorOrgeFullName+orgeName);
                        bjOrge.setEmail(email);
                        bjOrge.setLinkMan(linkMan);
                        bjOrge.setOrgeAddr(orgeAddr);
                        bjOrge.setOrgeFullName(orgeFullName);
                        bjOrge.setOrgeTel(orgeTel);
                        if (orge.addOrge(bjOrge) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("添加成功");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!数据保存失败");
                        }
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "添加组织", returnJson.getMessage());
        return returnJson.getJson();
    }

    //添加一级组织
    @RequestMapping("/addOrgeOne")
    public String addOrgeOne(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String orgeName = (String) param.get("orgeName");
        String orgeFullName = (String) param.get("orgeFullName");
        String orgeAddr = (String) param.get("orgeAddr");
        String orgeTel = (String) param.get("orgeTel");
        String orgeType = (String) param.get("orgeType");
        String email = (String) param.get("email");
        String linkMan = (String) param.get("linkMan");
        ReturnJson returnJson = new ReturnJson();
        orgeName = F.isNull(orgeName);
        orgeFullName = F.isNull(orgeFullName);
        orgeAddr = F.isNull(orgeAddr);
        orgeTel = F.isNull(orgeTel);
        email = F.isNull(email);
        linkMan = F.isNull(linkMan);
        orgeType = F.isNull(orgeType);
        BjOrge bjOrge = null;
        int levelStr = 1;
        String superiorOrgeId = "0";
        try {
            if (Auth.isAuth(request)) {
                if (orgeName != null && orgeFullName != null && orgeType != null) {

                    //入库
                    bjOrge = new BjOrge();
                    bjOrge.setOrgeLevel(levelStr);
                    bjOrge.setOrgeName(orgeName);
                    bjOrge.setSuperiorOrgeId(superiorOrgeId);
                    bjOrge.setUserId(Auth.getAuthUserId(request));
                    bjOrge.setOrgeFullName(orgeFullName);
                    bjOrge.setOrgeState("0");
                    bjOrge.setEmail(email);
                    bjOrge.setLinkMan(linkMan);
                    bjOrge.setOrgeAddr(orgeAddr);
                    bjOrge.setOrgeType(orgeType);
                    bjOrge.setOrgeTel(orgeTel);
                    if (orge.addOrge(bjOrge) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("添加成功");
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!数据保存失败");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "添加一级组织", returnJson.getMessage());
        return returnJson.getJson();
    }

    //取单个
    @RequestMapping("/getOrgeInfo")
    public String getOrgeInfo(@RequestBody String orgeId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        orgeId = F.isNull(orgeId);
        BjOrge bjOrge = null;
        try {
            if (Auth.isAuth(request)) {
                if (orgeId != null) {

                    bjOrge = orge.getOrgeInfo(orgeId);
                    if (bjOrge != null) {
                        returnJson.setState(0);
                        returnJson.setMessage("操作成功");
                        returnJson.setObject(bjOrge);
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!获取数据失败");
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
        return returnJson.getJson();
    }

    //更新
    @RequestMapping("/upOrge")
    public String upOrge(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String orgeId = (String) param.get("orgeId");
        String orgeName = (String) param.get("orgeName");
        String orgeFullName = (String) param.get("orgeFullName");
        String orgeAddr = (String) param.get("orgeAddr");
        String orgeTel = (String) param.get("orgeTel");
        String email = (String) param.get("email");
        String linkMan = (String) param.get("linkMan");

        L.printRequest(null, request);
        ReturnJson returnJson = new ReturnJson();
        orgeId = F.isNull(orgeId);
        orgeName = F.isNull(orgeName);
        //orgeState=F.isNull(orgeState);
        orgeFullName = F.isNull(orgeFullName);
        orgeAddr = F.isNull(orgeAddr);
        orgeTel = F.isNull(orgeTel);
        email = F.isNull(email);
        linkMan = F.isNull(linkMan);
        BjOrge bjOrge = null;
        BjOrge myOrge = null;
        String superiorName = "";
        try {
            if (Auth.isAuth(request)) {
                if (orgeId != null && orgeName != null) {

                    myOrge = orge.getOrgeInfo(orgeId);
                    if (myOrge != null) {
                        bjOrge = new BjOrge();
                        bjOrge.setOrgeId(orgeId);
                        bjOrge.setOrgeName(orgeName);
                        bjOrge.setEmail(email);
                        bjOrge.setLinkMan(linkMan);
                        bjOrge.setOrgeAddr(orgeAddr);
                        bjOrge.setOrgeFullName(orgeFullName);
                        bjOrge.setOrgeTel(orgeTel);
                        if (orge.upOrge(bjOrge) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("数据更新成功");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!数据更新失败");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!获取组织信息失败");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("对不起！数据格式错误");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "更新组织", returnJson.getMessage());
        return returnJson.getJson();
    }

    //删除
    @RequestMapping("/delOrge")
    public String delOrge(@RequestBody String orgeId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        orgeId = F.isNull(orgeId);
        try {
            if (Auth.isAuth(request)) {
                if (orgeId != null) {

                    if (orge.getOrgeIsUse(orgeId) == 0) {
                        if (orge.getOrgeIsChild(orgeId) == 0) {
                            if (orge.delOrge(orgeId) > 0) {
                                returnJson.setState(0);
                                returnJson.setMessage("数据已删除");
                            } else {
                                returnJson.setState(-1);
                                returnJson.setMessage("对不起!删除失败");
                            }
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!有下级组织,不能删除");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!在使用中,不能删除");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "删除组织", returnJson.getMessage());
        return returnJson.getJson();
    }


}
