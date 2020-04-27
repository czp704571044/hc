package cn.yfyue.yyzx.mapper;


import cn.yfyue.yyzx.model.OpUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpUserMapper {
//用户列表
List<OpUser> getUserList(@Param("orgeId") String orgeId,
                         @Param("qkey") String qkey,
                         @Param("startRow") int startRow,
                         @Param("rowNum") int rowNum);
//用户列表统计
int getUserListCount(@Param("orgeId") String orgeId,
                     @Param("qkey") String qkey);
//取自己的信息
OpUser getUserInfo(@Param("userId") String userId);
//更新账户信息
int upUser(OpUser opUser);
//用户名是否存在
int isLoginName(@Param("loginName") String loginName);
//初始化密码
int initPasswd(@Param("userId") String userId,
               @Param("passwd") String passwd);
//添加账户
int addUser(OpUser opUser);
//启用账户
int enabledUser(String userId);
//禁用账户
int disabledUser(String userId);
//清空错误
int loginErrClear(String loginName);
}