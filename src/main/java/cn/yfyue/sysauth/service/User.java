package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjUser;
import cn.yfyue.sysauth.mapper.BjUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User {


    @Autowired
    private BjUserMapper bjUserMapper;

    //用户列表
    public List<BjUser> getUserList(String userId, String adminLevel, String qkey, int startRow, int rowNum) {
        return bjUserMapper.getUserList(userId, adminLevel, qkey, startRow, rowNum);
    }

    // 用户列表统计
    public int getUserListCount(String userId, String adminLevel, String qkey) {
        return bjUserMapper.getUserListCount(userId, adminLevel, qkey);
    }

    //自己更新密码
    public int upMyPasswd(String userId, String oldPasswd, String passwd) {
        return bjUserMapper.upMyPasswd(userId, oldPasswd, passwd);
    }

    //初始化密码
    public int initPasswd(String userId, String passwd) {
        return bjUserMapper.initPasswd(userId, passwd);

    }

    //验证密码是否正确
    public int isMyPasswd(String userId, String oldPasswd) {
        return bjUserMapper.isMyPasswd(userId, oldPasswd);
    }

    /*管理平台用户操作*/

    //用户名是否存在
    public int isLoginName(String loginName) {
        return bjUserMapper.isLoginName(loginName);

    }

    //启用账户
    public int enabledUser(String userId) {
        return bjUserMapper.enabledUser(userId);
    }

    //禁用账户
    public int disabledUser(String userId) {
        return bjUserMapper.disabledUser(userId);
    }

    //取自己的信息
    public BjUser getMyInfo(String userId) {
        return bjUserMapper.getMyInfo(userId);

    }

    //更新账户信息
    public int upUserInfo(BjUser bjUser) {
        return bjUserMapper.upUserInfo(bjUser);

    }

    //添加账户
    public int addUser(BjUser bjUser) {
        return bjUserMapper.addUser(bjUser);

    }

}
