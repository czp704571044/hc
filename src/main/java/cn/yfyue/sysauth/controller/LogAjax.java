package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.ReturnJson;

import cn.yfyue.sysauth.service.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/logAjax")
public class LogAjax {

    @Autowired
    private Log log;

    //列表
    @RequestMapping("/logList")
    public String logList(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String query_key = (String) param.get("query_key");
        String log_type = (String) param.get("log_type");
        String b_time = (String) param.get("b_time");
        String e_time = (String) param.get("e_time");
        String page = (String) param.get("page");
        String rows = (String) param.get("rows");
        List dataList = null;
        ReturnJson returnJson = new ReturnJson();
        int nPage = F.fPage(page);
        int nRowNum = F.fRow(rows);
        String q_b_time = null;
        String q_e_time = null;
        query_key = F.isNull(query_key);
        log_type = F.isNull(log_type);
        b_time = F.isNull(b_time);
        e_time = F.isNull(e_time);
        try {
            if (b_time != null) {
                q_b_time = b_time.trim() + " 00:00:00";
            }
            if (e_time != null) {
                q_e_time = e_time.trim() + " 23:59:59";
            }
            if (Auth.isAuth(request)) {

                // 取最大行数
                dataList = log.logList(Auth.getAuthUserLevel(request), query_key, Auth.getAuthLoginName(request),
                        log_type, q_b_time, q_e_time, (nPage - 1) * nRowNum, nRowNum);
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
    @RequestMapping("/logListCount")
    public String logListCount(@RequestBody HashMap<String, Object> param, ServletRequest request) {
        String query_key = (String) param.get("query_key");
        String log_type = (String) param.get("log_type");
        String b_time = (String) param.get("b_time");
        String e_time = (String) param.get("e_time");
        ReturnJson returnJson = new ReturnJson();
        int dataTotalRowNum = 0;
        query_key = F.isNull(query_key);
        log_type = F.isNull(log_type);
        b_time = F.isNull(b_time);
        e_time = F.isNull(e_time);
        String q_b_time = null;
        String q_e_time = null;
        try {
            if (b_time != null) {
                q_b_time = b_time.trim() + " 00:00:00";
            }
            if (e_time != null) {
                q_e_time = e_time.trim() + " 23:59:59";
            }
            if (Auth.isAuth(request)) {

                // 取最大行数
                dataTotalRowNum = log.logListCount(Auth.getAuthUserLevel(request), query_key, Auth.getAuthLoginName(request),
                        log_type, q_b_time, q_e_time);
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
}
