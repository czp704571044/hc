package cn.yfyue.sysauth.service;

import cn.yfyue.sysauth.model.BjRoleFun;
import cn.yfyue.sysauth.mapper.BjRoleFunMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleFun {
@Autowired
private BjRoleFunMapper bjRoleFunMapper;
//取所有的权限 all
public List<BjRoleFun> getAllFuncList() {
	return bjRoleFunMapper.getAllFuncList();
}	
//取接收角色已分配权限 sendeRole
public List<BjRoleFun> getRoleFuncList(List<String> roleIds) {
	return bjRoleFunMapper.getRoleFuncList(roleIds);
}
//添加分配权限
public int addRoleFun(BjRoleFun bjRoleFun) {
	return bjRoleFunMapper.addRoleFun(bjRoleFun);
}	
//按功能ID清掉权限
public int delFunIdToRoleFun(String funcId) {
	return bjRoleFunMapper.delFunIdToRoleFun(funcId);
}
//按角色清掉权限
public int delRoleIdToRoleFun(String roleId) {
	return bjRoleFunMapper.delRoleIdToRoleFun(roleId);
}
//验证是否有权使用
public int isRoleFunc(List<String> roleIds,String funcId) {
	return bjRoleFunMapper.isRoleFunc(roleIds, funcId);
}
}
