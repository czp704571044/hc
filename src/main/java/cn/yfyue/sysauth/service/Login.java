package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjUser;
import cn.yfyue.sysauth.mapper.BjUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Login {

    @Autowired
    private BjUserMapper bjUserMapper;

    //登录
    public int isLogin(String login_name, String passwd) {
        return bjUserMapper.isLogin(login_name, passwd);
    }

    //取登录数据
    public BjUser getUserLoginInfo(String login_name) {
        return bjUserMapper.getUserLoginInfo(login_name);
    }

    //取数据库的时间
    public Date getDbTime() {
        return bjUserMapper.getDbTime();
    }

    //更新错误+1
    public int loginErr(String loginName) {
        return bjUserMapper.loginErr(loginName);
    }

    //清空错误
    public int loginErrClear(String loginName) {
        return bjUserMapper.loginErrClear(loginName);
    }

}
