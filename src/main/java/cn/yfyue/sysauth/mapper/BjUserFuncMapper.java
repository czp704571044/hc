package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjUserFunc;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjUserFuncMapper {
//取用户的权限
List<BjUserFunc> getUserFuncList(@Param("userId") String userId, @Param("adminLevel") String adminLevel);
//添加分配权限
int addUserFunc(BjUserFunc bjUserFunc);
//按功能ID清掉权限
int delFunIdToUserFunc(String funcId);
//按用户ID清掉权限
int delUserIdToUserFunc(String userId);
//验证是否有权使用
int isUserFunc(@Param("userId") String userId, @Param("funcId") String funcId);
}