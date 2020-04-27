package cn.yfyue.sysauth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class StartController {


    @RequestMapping("/login")
    public String demo() {
        return "login";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/timeOut")
    public String timeOut() {
        return "timeOut";
    }

    @RequestMapping("/page/{path}")
    public String dispatchJsp(@PathVariable("path") String path) {
        if (path.contains("-")) {
            StringBuilder newPath = new StringBuilder();
            for (int i = 0; i < path.split("-").length; i++) {
                if (i == path.split("-").length - 1) {
                    newPath.append(path.split("-")[i]);
                } else {
                    newPath.append(path.split("-")[i]).append("/");
                }
            }
            return newPath.toString();

        } else {
            return path;
        }

    }
}
