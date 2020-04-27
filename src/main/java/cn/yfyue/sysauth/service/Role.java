package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjRole;
import cn.yfyue.sysauth.mapper.BjRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Role {
@Autowired
private BjRoleMapper bjRoleMapper;
//添加
public int addRole(BjRole bjRole){
	return bjRoleMapper.addRole(bjRole);
}
//分配时取出所有角色 //取出全部角色
public List <BjRole> getAllRoleList(String qkey){
	return bjRoleMapper.getAllRoleList(qkey);
}
//列表
public List <BjRole> getRoleList(String qkey,int startRow,int rowNum){
	return bjRoleMapper.getRoleList(qkey, startRow, rowNum);
}
//列表统计
public int getRoleListNum(String qkey){
	return bjRoleMapper.getRoleListNum(qkey);
}
//更新
public int upJiaoSe(BjRole bjRole){
	return bjRoleMapper.upJiaoSe(bjRole);
}
//删除
public int delRole(String roleId){
	return bjRoleMapper.delRole(roleId);
}
//是否分配角色功能
public int isFuncToRole(String roleId){
	return bjRoleMapper.isFuncToRole(roleId);
}
//是否分配到用户
public int isRoleToUser(String roleId){
	return bjRoleMapper.isRoleToUser(roleId);
}
//取单个	
public BjRole getRoleInfo(String roleId){
	return bjRoleMapper.getRoleInfo(roleId);
}
}
