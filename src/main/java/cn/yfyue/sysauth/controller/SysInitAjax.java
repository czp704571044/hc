package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.*;

import cn.yfyue.sysauth.model.BjInitData;
import cn.yfyue.sysauth.model.BjInitType;
import cn.yfyue.sysauth.service.Init;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/sysInitAjax")
public class SysInitAjax {

    @Autowired
    private Init init;

    //类型列表
    @RequestMapping("/getInitTypeList")
    public String getInitTypeList(@RequestBody HashMap<String, Object> param, ServletRequest request) {
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
                dataList = init.getInitTypeList(qkey, (nPage - 1) * nRowNum, nRowNum);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataList);
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //统计
    @RequestMapping("/getInitTypeCount")
    public String getInitTypeCount(@RequestBody String qkey, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        int dataTotalRowNum = 0;
        qkey = F.isNull(qkey);
        try {
            if (Auth.isAuth(request)) {

                // 取最大行数
                dataTotalRowNum = init.getInitTypeCount(qkey);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataTotalRowNum);
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //配置数据列表
    @RequestMapping("/getInitDataList")
    public String getInitDataList(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String qkey = (String) param.get("qkey");
        String typeCode = (String) param.get("typeCode");
        String page = (String) param.get("page");
        String rows = (String) param.get("rows");
        List dataList = null;
        ReturnJson returnJson = new ReturnJson();
        int nPage = F.fPage(page);
        int nRowNum = F.fRow(rows);
        qkey = F.isNull(qkey);
        typeCode = F.isNull(typeCode);
        //L.printRequest(null, request);
        try {
            if (Auth.isAuth(request)) {

                dataList = init.getInitDataList(typeCode, qkey, (nPage - 1) * nRowNum, nRowNum);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataList);
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //配置数据统计
    @RequestMapping("/getInitDataCount")
    public String getInitDataCount(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String qkey = (String) param.get("qkey");
        String typeCode = (String) param.get("typeCode");
        ReturnJson returnJson = new ReturnJson();
        int dataTotalRowNum = 0;
        qkey = F.isNull(qkey);
        typeCode = F.isNull(typeCode);
        try {
            if (Auth.isAuth(request)) {

                // 取最大行数
                dataTotalRowNum = init.getInitDataCount(typeCode, qkey);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataTotalRowNum);
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //添加类型
    @RequestMapping("/addInitType")
    public String addInitType(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String typeName = (String) param.get("typeName");
        String typeCode = (String) param.get("typeCode");
        ReturnJson returnJson = new ReturnJson();
        typeName = F.isNull(typeName);
        typeCode = F.isNull(typeCode);
        if (typeCode != null) {
            typeCode = typeCode.toUpperCase();
        }
        BjInitType bjInitType = null;
        try {
            if (Auth.isAuth(request)) {

                if (typeName != null && typeCode != null) {
                    if (init.isInitTypeCode(typeCode) == 0) {
                        bjInitType = new BjInitType();
                        bjInitType.setTypeCode(typeCode);
                        bjInitType.setTypeName(typeName);
                        bjInitType.setTypeState("0");
                        if (init.addInitType(bjInitType) > 0) {
                            returnJson.setState(0);
                            returnJson.setMessage("添加成功");
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("添加类型编码,保存失败");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("类型编码已存在");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "添加数据类型", returnJson.getMessage());
        return returnJson.getJson();
    }

    //取单个类型
    @RequestMapping("/getInitTypeInfo")
    public String getInitTypeInfo(@RequestBody String initTypeId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        initTypeId = F.isNull(initTypeId);
        BjInitType bjInitType = null;
        try {
            if (Auth.isAuth(request)) {
                if (initTypeId != null) {

                    bjInitType = init.getInitTypeInfo(initTypeId);
                    if (bjInitType != null) {
                        returnJson.setState(0);
                        returnJson.setMessage("查询成功");
                        returnJson.setObject(bjInitType);
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
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //更新
    @RequestMapping("/upInitType")
    public String upInitType(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String initTypeId = (String) param.get("initTypeId");
        String typeName = (String) param.get("typeName");
        ReturnJson returnJson = new ReturnJson();
        typeName = F.isNull(typeName);
        initTypeId = F.isNull(initTypeId);
        BjInitType bjInitType = null;
        try {
            if (Auth.isAuth(request)) {

                if (typeName != null && initTypeId != null) {
                    bjInitType = new BjInitType();
                    bjInitType.setTypeName(typeName);
                    bjInitType.setInitTypeId(initTypeId);
                    if (init.upInitType(bjInitType) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("更新成功");
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("更新数据,保存失败");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("类型编码已存在");
                }
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "更新数据类型", returnJson.getMessage());
        return returnJson.getJson();
    }

    //删除
    @RequestMapping("/delInitType")
    public String delInitType(@RequestBody String initTypeId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        initTypeId = F.isNull(initTypeId);
        BjInitType bjInitType = null;
        try {
            if (Auth.isAuth(request)) {
                if (initTypeId != null) {

                    bjInitType = init.getInitTypeInfo(initTypeId);
                    if (bjInitType != null) {
                        if (init.getInitTypeIsChild(bjInitType.getTypeCode()) == 0) {
                            if (init.delInitType(initTypeId) > 0) {
                                returnJson.setState(0);
                                returnJson.setMessage("数据删除成功");
                            } else {
                                returnJson.setState(-1);
                                returnJson.setMessage("对不起!删除失败");
                            }
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!还配置有数据,暂不能删除");
                        }
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
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "删除数据类型", returnJson.getMessage());
        return returnJson.getJson();
    }

    //添加数据
    @RequestMapping("/addInitData")
    public String addInitData(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String dataCode = (String) param.get("dataCode");
        String dataName = (String) param.get("dataName");
        String dataTypeCode = (String) param.get("dataTypeCode");
        L.printRequest(null, request);
        ReturnJson returnJson = new ReturnJson();
        //typeCode = F.isNull(typeCode);
        dataCode = F.isNull(dataCode);
        dataName = F.isNull(dataName);
        BjInitData bjInitData = null;
        try {
            if (Auth.isAuth(request)) {
                if (dataTypeCode != null && dataCode != null && dataName != null) {
                    if (dataCode != null) {
                        dataCode = dataCode.toUpperCase();
                    }
                    bjInitData = new BjInitData();
                    bjInitData.setDataCode(dataTypeCode + "_" + dataCode);
                    bjInitData.setDataState("0");
                    bjInitData.setTypeCode(dataTypeCode);
                    bjInitData.setDataName(dataName);

                    if (init.addInitData(bjInitData) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("数据添加成功");
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
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "添加数据", returnJson.getMessage());
        return returnJson.getJson();
    }

    //取单个数据
    @RequestMapping("/getInitDataInfo")
    public String getInitDataInfo(@RequestBody String initDataId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        initDataId = F.isNull(initDataId);
        BjInitData bjInitData = null;
        try {
            if (Auth.isAuth(request)) {
                if (initDataId != null) {
                    bjInitData = init.getInitDataInfo(initDataId);
                    if (bjInitData != null) {
                        returnJson.setState(0);
                        returnJson.setMessage("查询成功");
                        returnJson.setObject(bjInitData);
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
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //更新
    @RequestMapping("/upInitData")
    public String upInitData(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String initDataId = (String) param.get("initDataId");
        String dataName = (String) param.get("dataName");
        ReturnJson returnJson = new ReturnJson();
        dataName = F.isNull(dataName);
        initDataId = F.isNull(initDataId);
        BjInitData bjInitData = null;
        try {
            if (Auth.isAuth(request)) {

                if (initDataId != null && dataName != null) {
                    bjInitData = new BjInitData();
                    bjInitData.setInitDataId(initDataId);
                    bjInitData.setDataName(dataName);
                    if (init.upInitData(bjInitData) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("更新成功");
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("更新数据,保存失败");
                    }
                } else {
                    returnJson.setState(-1);
                    returnJson.setMessage("类型编码已存在");
                }
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "更新数据", returnJson.getMessage());
        return returnJson.getJson();
    }

    //禁用
    @RequestMapping("/delInitData")
    public String delInitData(@RequestBody String initDataId, ServletRequest request) {
        ReturnJson returnJson = new ReturnJson();
        initDataId = F.isNull(initDataId);
        try {
            if (Auth.isAuth(request)) {
                if (initDataId != null) {

                    if (init.delInitData(initDataId) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("数据删除成功");
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!删除失败");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "禁用数据", returnJson.getMessage());
        return returnJson.getJson();
    }

    //类型列表
    @RequestMapping("/getInitTypeAllList")
    public String getInitTypeAllList(ServletRequest request) {
        List dataList = null;
        ReturnJson returnJson = new ReturnJson();
        try {
            if (Auth.isAuth(request)) {
                // 取最大行数
                dataList = init.getInitTypeList(null, 0, 10000);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataList);
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }

    //配置数据列表
    @RequestMapping("/LoadInitData")
    public String LoadInitData(@RequestBody String typeCode, ServletRequest request) {
        List<BjInitData> dataList = null;
        ReturnJson returnJson = new ReturnJson();
        typeCode = F.isNull(typeCode);
        try {
            if (Auth.isAuth(request)) {

                dataList = SetSys.loadCatchInitData(typeCode);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataList);
            } else {
                returnJson.setState(-1);
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnJson.getJson();
    }
}
