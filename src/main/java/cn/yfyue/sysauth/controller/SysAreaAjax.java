package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;


import cn.yfyue.sysauth.model.AreaTreeModel;
import cn.yfyue.sysauth.model.BjArea;
import cn.yfyue.sysauth.service.Area;
import cn.yfyue.sysauth.utils.AreaUtil;
import cn.yfyue.sysauth.utils.AreaWarp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/sysAreaAjax")
public class SysAreaAjax {

    @Autowired
    private Area area;

    // 取列表
    @RequestMapping("/getAreaList")
    public String getAreaList(@RequestParam(required = false) String areaCode, ServletRequest request) {
        L.printRequest(null, request);
        //String areaCode = (String) param.get("areaCode");
        ReturnJson returnJson = new ReturnJson();
        List<BjArea> dataList;
        List<AreaWarp> wrapList;
        areaCode = F.isNull(areaCode);
        try {
            if (Auth.isAuth(request)) {
                // 取最大行数
                dataList = area.getAreaList(areaCode);
                wrapList = AreaUtil.getAreaChildNodes(dataList, areaCode);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(wrapList);
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

    // 添加一级
    @RequestMapping("/addOneArea")
    public String addOneArea(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String areaCode = (String) param.get("areaCode");
        String areaName = (String) param.get("areaName");
        L.printRequest(null, request);
        ReturnJson returnJson = new ReturnJson();
        areaCode = F.isNull(areaCode);
        areaName = F.isNull(areaName);
        BjArea bjArea = null;
        String levelStr = "1";
        String fullNameStr = "";
        String supperCode = "0";
        try {
            if (Auth.isAuth(request)) {
                if (areaCode != null && areaName != null) {
                    // 入库
                    if (area.isAreaCode(areaCode) == 0) {
                        bjArea = new BjArea();
                        bjArea.setAreaCode(areaCode);
                        bjArea.setAreaLevel(F.StrToInt(levelStr));
                        bjArea.setAreaName(areaName);
                        bjArea.setFullName(fullNameStr + areaName);
                        bjArea.setSupperCode(supperCode);
                        if (area.addArea(bjArea) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("添加成功");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!数据保存失败");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!地区编码已存在,请编辑其它编码");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "添加省级地区", returnJson.getMessage());
        return returnJson.getJson();
    }

    // 添加
    @RequestMapping("/addArea")
    public String addArea(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String areaCode = (String) param.get("areaCode");
        String supperCode = (String) param.get("supperCode");
        String areaName = (String) param.get("areaName");
        L.printRequest(null, request);
        ReturnJson returnJson = new ReturnJson();
        areaCode = F.isNull(areaCode);
        supperCode = F.isNull(supperCode);
        areaName = F.isNull(areaName);
        BjArea superiorArea = null;
        BjArea bjArea = null;
        String levelStr = "1";
        int nOpFlag = 0;
        String fullNameStr = "";
        try {
            if (Auth.isAuth(request)) {
                if (areaCode != null && supperCode != null && areaName != null) {
                    superiorArea = area.getAreaInfo(supperCode);
                    if (superiorArea != null) {
                        if (superiorArea.getAreaLevel() != null) {
                            if ((superiorArea.getAreaLevel() + 1) < 7) {
                                levelStr = "" + (superiorArea.getAreaLevel() + 1);
                                supperCode = superiorArea.getAreaCode();
                                fullNameStr = superiorArea.getFullName();
                                nOpFlag = 0;
                            } else {
                                nOpFlag = -1;
                                returnJson.setState(-1);
                                returnJson.setMessage("对不起!只支持6层结构[省,市,县,镇,村,组]");
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

                    if (nOpFlag == 0) {
                        // 入库
                        if (area.isAreaCode(supperCode + areaCode) == 0) {
                            bjArea = new BjArea();
                            bjArea.setAreaCode(supperCode + areaCode);
                            bjArea.setAreaLevel(F.StrToInt(levelStr));
                            bjArea.setAreaName(areaName);
                            bjArea.setFullName(fullNameStr + areaName);
                            bjArea.setSupperCode(supperCode);

                            if (area.addArea(bjArea) > 0) {
                                returnJson.setState(0);
                                returnJson.setMessage("添加成功");
                            } else {
                                returnJson.setState(-1);
                                returnJson.setMessage("对不起!数据保存失败");
                            }
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!地区编码已存在,请编辑其它编码");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "添加地区", returnJson.getMessage());
        return returnJson.getJson();
    }

    // 取单个类型
    @RequestMapping("/getAreaInfo")
    public String getAreaInfo(@RequestBody String areaCode, ServletRequest request) {
        L.printRequest(null, request);
        ReturnJson returnJson = new ReturnJson();
        BjArea bjArea = null;
        areaCode = F.isNull(areaCode);
        try {
            if (Auth.isAuth(request)) {
                if (areaCode != null) {
                    bjArea = area.getAreaInfo(areaCode);
                    if (bjArea != null) {
                        returnJson.setState(0);
                        returnJson.setMessage("操作成功");
                        returnJson.setObject(bjArea);
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!数据获取失败");
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

    // 更新
    @RequestMapping("/upArea")
    public String upArea(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String areaCode = (String) param.get("areaCode");
        String areaName = (String) param.get("areaName");
        L.printRequest(null, request);
        ReturnJson returnJson = new ReturnJson();
        areaCode = F.isNull(areaCode);
        areaName = F.isNull(areaName);
        BjArea superiorArea = null;
        BjArea bjArea = null;
        String fullNameStr = "";
        try {
            if (Auth.isAuth(request)) {
                if (areaCode != null && areaName != null) {
                    bjArea = area.getAreaInfo(areaCode);
                    if (bjArea != null) {
                        if (bjArea.getSupperCode() != null && bjArea.getSupperCode().equals("0")) {
                            fullNameStr = "";
                        } else {
                            superiorArea = area.getAreaInfo(bjArea.getSupperCode());
                            if (superiorArea != null) {
                                fullNameStr = superiorArea.getFullName();
                            } else {
                                returnJson.setState(-1);
                                returnJson.setMessage("对不起!获取上级地区信息失败");
                            }
                        }
                        // 更新
                        bjArea = new BjArea();
                        bjArea.setAreaCode(areaCode);
                        bjArea.setAreaName(areaName);
                        bjArea.setFullName(fullNameStr + areaName);
                        if (area.upArea(bjArea) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("地区数据更新成功");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!数据保存失败");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!获取地区信息失败");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "更新地区", returnJson.getMessage());
        return returnJson.getJson();
    }

    // 删除
    @RequestMapping("/delArea")
    public String delArea(@RequestBody String areaCode, ServletRequest request) {
        L.printRequest(null, request);
        ReturnJson returnJson = new ReturnJson();
        areaCode = F.isNull(areaCode);
        try {
            if (Auth.isAuth(request)) {
                if (areaCode != null) {
                    if (area.getAreaIsChild(areaCode) == 0) {
                        if (area.delArea(areaCode) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("地区已删除");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起,地区删除失败");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起,有下级地区,暂不能删除");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "删除地区", returnJson.getMessage());
        return returnJson.getJson();
    }

    // 取省目录列表
    @RequestMapping("/getFirstAreaList")
    public String getFirstAreaList(ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        List<BjArea> dataList = null;
        try {
            if (Auth.isAuth(request)) {
                dataList = area.getFirstAreaList();
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(AreaUtil.getAreaChildNodes(dataList, null));
            } else {
                returnJson.setState(-997);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        return returnJson.getJson();
    }
}
