package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjRoleMapper {
//分配时取出所有角色
List <BjRole> getAllRoleList(@Param("qkey") String qkey);
//添加
int addRole(BjRole bjRole);
//列表
List <BjRole> getRoleList(@Param("qkey") String qkey,
                          @Param("startRow") int startRow, @Param("rowNum") int rowNum);
//列表统计
int getRoleListNum(String qkey);
//更新
int upJiaoSe(BjRole bjRole);
//删除
int delRole(String roleId);
//是否分配角色功能
int isFuncToRole(String roleId);
//是否分配到用户
int isRoleToUser(String roleId);
//取单个	
BjRole getRoleInfo(String roleId); 
}