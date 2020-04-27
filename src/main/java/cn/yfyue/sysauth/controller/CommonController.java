package cn.yfyue.sysauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

@Controller
//@SuppressWarnings("all")
public class CommonController {



    @RequestMapping("/pages/carry/{path}")
    public String carryParam(@PathVariable("path") String path, HttpServletRequest request) {
        String newPathStr = path.split("@")[0];//xxx-xxx
        StringBuilder newPath = new StringBuilder();
        if (newPathStr.contains("-")) {
            for (int i = 0; i < newPathStr.split("-").length; i++) {
                if (i == newPathStr.split("-").length - 1) {
                    newPath.append(newPathStr.split("-")[i]);
                } else {
                    newPath.append(newPathStr.split("-")[i]).append("/");
                }
            }
        }
        String lujin = newPath.toString();
        String params = path.split("@")[1];
        if (params.split("&").length > 1) {
            for (int i = 0; i < params.split("&").length; i++) {
                String[] param = params.split("&")[i].split("=");
                request.setAttribute(param[0], param[1]);
            }
        } else {
            String[] param = params.split("=");
            request.setAttribute(param[0], param[1]);
        }

        return lujin;
    }
}
