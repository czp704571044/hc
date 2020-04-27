package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;

import cn.yfyue.sysauth.model.BjHelpDoc;
import cn.yfyue.sysauth.service.HelpDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/helpDocAjax")
public class HelpDocAjax {

    @Autowired
    private HelpDoc helpDoc;

    //帮助文档列表
    @RequestMapping("/getHelpDocList")
    public String getHelpDocList(
            @RequestBody HashMap<String, Object> param,
            ServletRequest request) {
        String query_key = (String) param.get("query_key");
        String b_time = (String) param.get("b_time");
        String e_time = (String) param.get("e_time");
        String page = (String) param.get("page");
        String rows = (String) param.get("rows");
        List<BjHelpDoc> dataList = null;
        int nPage = F.fPage(page);
        int nRowNum = F.fRow(rows);
        String q_b_time = null;
        String q_e_time = null;
        query_key = F.isNull(query_key);
        b_time = F.isNull(b_time);
        e_time = F.isNull(e_time);
        ReturnJson returnJson = new ReturnJson();
        try {
            if (Auth.isAuth(request)) {
                if (b_time != null) {
                    q_b_time = b_time.trim() + " 00:00:00";
                }
                if (e_time != null) {
                    q_e_time = e_time.trim() + " 23:59:59";
                }
                dataList = helpDoc.getHelpDocList(query_key, q_b_time, q_e_time, (nPage - 1) * nRowNum, nRowNum);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataList);
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

    //统计
    @RequestMapping("/getHelpDocListCount")
    public String getHelpDocListCount(
            @RequestBody HashMap<String, Object> param,
            ServletRequest request) {
        String query_key = (String) param.get("query_key");
        String b_time = (String) param.get("b_time");
        String e_time = (String) param.get("e_time");
        String q_b_time = null;
        String q_e_time = null;
        int dataTotalRowNum = 0;
        query_key = F.isNull(query_key);
        b_time = F.isNull(b_time);
        e_time = F.isNull(e_time);
        ReturnJson returnJson = new ReturnJson();
        try {
            if (Auth.isAuth(request)) {
                if (b_time != null) {
                    q_b_time = b_time.trim() + " 00:00:00";
                }
                if (e_time != null) {
                    q_e_time = e_time.trim() + " 23:59:59";
                }
                dataTotalRowNum = helpDoc.getHelpDocListCount(query_key, q_b_time, q_e_time);
                returnJson.setState(0);
                returnJson.setMessage("操作成功");
                returnJson.setObject(dataTotalRowNum);
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

    //添加
    @RequestMapping("/addHelpDoc")
    public String addHelpDoc(
            @RequestBody HashMap<String, Object> param,
            ServletRequest request) {
        String docOrder = (String) param.get("docOrder");
        String docCode = (String) param.get("docCode");
        String docTitle = (String) param.get("docTitle");
        String docContent = (String) param.get("docContent");
        docOrder = F.isNull(docOrder);
        docTitle = F.isNull(docTitle);
        docCode = F.isNull(docCode);
        docContent = F.isNull(docContent);
        BjHelpDoc bjHelpDoc = null;
        ReturnJson returnJson = new ReturnJson();
        try {
            if (Auth.isAuth(request)) {
                if (docContent != null && docTitle != null && docCode != null) {
                    if (F.isNumberOrLetter(docCode)) {
                        if (helpDoc.isDocCode(docCode) == 0) {
                            bjHelpDoc = new BjHelpDoc();
                            bjHelpDoc.setDocContent(docContent);
                            bjHelpDoc.setDocTitle(docTitle);
                            bjHelpDoc.setDocCode(docCode.toUpperCase());
                            bjHelpDoc.setDocOrder(F.StrToInt(docOrder));
                            bjHelpDoc.setRegMan(F.strToLong(Auth.getAuthUserId(request)));
                            if (helpDoc.addHelpDoc(bjHelpDoc) > 0) {
                                returnJson.setState(0);
                                returnJson.setMessage("添加成功");
                            } else {
                                returnJson.setState(-1);
                                returnJson.setMessage("对不起!数据保存失败");
                            }
                        } else {
                            returnJson.setState(-1);
                            returnJson.setMessage("对不起!文档编码已存在");
                        }
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!编码只能是数字或字母组合");
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
        L.opLog(Thread.currentThread().getStackTrace(), request, "添加帮助文档", returnJson.getMessage());
        return returnJson.getJson();
    }

    //更新
    @RequestMapping("/upHelpDoc")
    public String upHelpDoc(
            @RequestBody HashMap<String, Object> param,
            ServletRequest request) {
        String docId = (String) param.get("docId");
        String docOrder = (String) param.get("docOrder");
        String docTitle = (String) param.get("docTitle");
        String docContent = (String) param.get("docContent");

        docOrder = F.isNull(docOrder);
        docTitle = F.isNull(docTitle);
        docId = F.isNull(docId);
        docContent = F.isNull(docContent);
        BjHelpDoc bjHelpDoc = null;
        ReturnJson returnJson = new ReturnJson();
        try {
            if (Auth.isAuth(request)) {
                if (docId != null && docTitle != null) {
                    bjHelpDoc = new BjHelpDoc();
                    bjHelpDoc.setDocContent(docContent);
                    bjHelpDoc.setDocTitle(docTitle);
                    bjHelpDoc.setDocOrder(F.StrToInt(docOrder));
                    bjHelpDoc.setDocId(F.strToLong(docId));
                    bjHelpDoc.setRegMan(F.strToLong(Auth.getAuthUserId(request)));

                    if (helpDoc.upHelpDoc(bjHelpDoc) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("更新成功");
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
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "更新帮助文档", returnJson.getMessage());
        return returnJson.getJson();
    }

    //删除
    @RequestMapping("/delHelpDoc")
    public String delHelpDoc(
            @RequestBody String docId,
            ServletRequest request) {
        docId = F.isNull(docId);
        ReturnJson returnJson = new ReturnJson();
        try {
            if (Auth.isAuth(request)) {
                if (docId != null) {
                    if (helpDoc.delHelpDoc(docId) > 0) {
                        returnJson.setState(0);
                        returnJson.setMessage("删除成功");
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
                returnJson.setMessage("请登录,再操作");
            }
        } catch (Exception e) {
            returnJson.setState(-1);
            returnJson.setMessage(e.toString());
            e.printStackTrace();
        }
        L.opLog(Thread.currentThread().getStackTrace(), request, "删除帮助文档", returnJson.getMessage());
        return returnJson.getJson();
    }

    //取单个数据
    @RequestMapping("/getHelpDocInfo")
    public String getHelpDocInfo(
            @RequestBody String docId,
            ServletRequest request) {
        docId = F.isNull(docId);
        ReturnJson returnJson = new ReturnJson();
        BjHelpDoc bjHelpDoc = null;
        try {
            if (Auth.isAuth(request)) {
                if (docId != null) {

                    bjHelpDoc = helpDoc.getHelpDocInfo(docId);
                    if (bjHelpDoc != null) {
                        returnJson.setState(0);
                        returnJson.setMessage("对像获取成功");
                        returnJson.setObject(bjHelpDoc);
                    } else {
                        returnJson.setState(-1);
                        returnJson.setMessage("对不起!获取数据失败");
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
        return returnJson.getJson();
    }
}
