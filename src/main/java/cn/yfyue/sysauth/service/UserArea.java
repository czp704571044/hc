package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjUserArea;
import cn.yfyue.sysauth.mapper.BjUserAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserArea {
@Autowired	
private BjUserAreaMapper bjUserAreaMapper;
//取用户的地区
public List<BjUserArea> getUserAreaList(String userId, String adminLevel) {
	return bjUserAreaMapper.getUserAreaList(userId, adminLevel);
}
//添加分配权限
public int addUserArea(BjUserArea bjUserArea ) {
	return bjUserAreaMapper.addUserArea(bjUserArea);
}
//按地区编码清掉权限
public int delAreaCodeToUserArea(String areaCode) {
	return bjUserAreaMapper.delAreaCodeToUserArea(areaCode);
}
//按用户ID清掉权限
public int delUserIdToUserArea(String userId) {
	return bjUserAreaMapper.delUserIdToUserArea(userId);
} 
}
