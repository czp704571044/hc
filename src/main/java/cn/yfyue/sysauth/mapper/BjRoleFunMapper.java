package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjRoleFun;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjRoleFunMapper {
//取所有的权限 all
List<BjRoleFun> getAllFuncList();
//取接收角色已分配权限 sendeeRole List<String> roleId
List<BjRoleFun> getRoleFuncList(List<String> roleIds);
//添加分配权限
int addRoleFun(BjRoleFun bjRoleFun);
//按功能ID清掉权限
int delFunIdToRoleFun(String funcId);
//按角色清掉权限
int delRoleIdToRoleFun(String roleId); 
//验证是否有权使用
int isRoleFunc(@Param("roleIds") List<String> roleIds, @Param("funcId") String funcId);
}