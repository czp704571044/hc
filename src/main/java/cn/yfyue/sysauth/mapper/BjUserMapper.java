package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface BjUserMapper {
    //登录
    int isLogin(@Param("login_name") String login_name, @Param("passwd") String passwd);

    //取登录数据
    BjUser getUserLoginInfo(@Param("login_name") String login_name);

    //用户列表
    List<BjUser> getUserList(@Param("userId") String userId, @Param("adminLevel") String adminLevel,
                             @Param("qkey") String qkey, @Param("startRow") int startRow, @Param("rowNum") int rowNum);

    //用户列表统计
    int getUserListCount(@Param("userId") String userId, @Param("adminLevel") String adminLevel,
                         @Param("qkey") String qkey);

    //取自己的信息
    BjUser getMyInfo(@Param("userId") String userId);

    //更新账户信息
    int upUserInfo(BjUser bjUser);

    //用户名是否存在
    int isLoginName(@Param("loginName") String loginName);

    //自己更新密码
    int upMyPasswd(@Param("userId") String userId, @Param("oldPasswd") String oldPasswd, @Param("passwd") String passwd);

    //初始化密码
    int initPasswd(@Param("userId") String userId, @Param("passwd") String passwd);

    //验证密码是否正确
    int isMyPasswd(@Param("userId") String userId, @Param("oldPasswd") String oldPasswd);

    //添加账户
    int addUser(BjUser bjUser);

    //启用账户
    int enabledUser(String userId);

    //禁用账户
    int disabledUser(String userId);

    //取数据库的时间
    Date getDbTime();

    //更新错误+1
    int loginErr(String loginName);

    //清空错误
    int loginErrClear(String loginName);

}