package cn.yfyue.yyzx.service;


import cn.yfyue.yyzx.mapper.OpUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OUserRole {
@Autowired	
private OpUserRoleMapper opUserRoleMapper;
//添加
public int addUserRole(String userId){
	return opUserRoleMapper.addUserRole(userId);
}
}
