package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjUserRole;
import cn.yfyue.sysauth.mapper.BjUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRole {
@Autowired	
private BjUserRoleMapper bjUserRoleMapper;
//添加
public int addUserRole(BjUserRole bjUserRole){
	return bjUserRoleMapper.addUserRole(bjUserRole);
}
//取出列表
public List<BjUserRole> getUserRoleList(String userId){
	return bjUserRoleMapper.getUserRoleList(userId);
}
//删除
public int delUserRole(String userId){
	return bjUserRoleMapper.delUserRole(userId);
}
}
