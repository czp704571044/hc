package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjUserButton;
import cn.yfyue.sysauth.mapper.BjUserButtonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserButton {
@Autowired	
private BjUserButtonMapper bjUserButtonMapper;
//取自己的按钮
public List<BjUserButton> getUserButtonList(String userId, String adminLevel) {
	return bjUserButtonMapper.getUserButtonList(userId, adminLevel);
}	
//添加分配按钮
public int addUserButton(BjUserButton bjUserButton) {
	return bjUserButtonMapper.addUserButton(bjUserButton);
}
//按功能ID清掉
public int delFunIdToUserButton(String funcId) {
	return bjUserButtonMapper.delFunIdToUserButton(funcId);
}
//按按钮ID清掉
public int delButtonIdToUserButton(String buttonId) {
	return bjUserButtonMapper.delButtonIdToUserButton(buttonId);
}
//按用户清掉	
public int delUserIdToUserButton(String userId) {
	return bjUserButtonMapper.delUserIdToUserButton(userId);
}
//按功能ID和用户取按钮
public List<BjUserButton> getUserIdAndFuncIdButtonList(String userId,String funcId,String adminLevel) {
	return bjUserButtonMapper.getUserIdAndFuncIdButtonList(userId, funcId, adminLevel);
}
}
