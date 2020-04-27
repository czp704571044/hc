package cn.yfyue.yyzx.service;


import cn.yfyue.yyzx.model.OpOrge;
import cn.yfyue.yyzx.mapper.OpOrgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OOrge {
@Autowired	
private OpOrgeMapper opOrgeMapper;
//取所有部门
public List<OpOrge> getOrgeList(String qkey, int startRow, int rowNum) {
	return opOrgeMapper.getOrgeList(qkey, startRow, rowNum);
}
//统计
public int getOrgeListCount(String qkey) {
	return opOrgeMapper.getOrgeListCount(qkey);
}	
//添加
public int addOrge(OpOrge opOrge) {
	return opOrgeMapper.addOrge(opOrge);
}
//门店名称是否存在
public int isOrgeName(String orgeName) {
	return opOrgeMapper.isOrgeName(orgeName);
}
//取单个
public OpOrge getOrgeInfo(String orgeId) {
	return opOrgeMapper.getOrgeInfo(orgeId);
}
//更新
public int upOrge(OpOrge opOrge) {
	return opOrgeMapper.upOrge(opOrge);
}
//删除
public int delOrge(String orgeId) {
	return opOrgeMapper.delOrge(orgeId);
}
//是否下级
public int getOrgeIsChild(String orgeId) {
	return opOrgeMapper.getOrgeIsChild(orgeId);
}
//是否已使用
public int getOrgeIsUse(String orgeId) {
	return opOrgeMapper.getOrgeIsUse(orgeId);
}
}