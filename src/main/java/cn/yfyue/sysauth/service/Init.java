package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjInitData;
import cn.yfyue.sysauth.model.BjInitType;
import cn.yfyue.sysauth.mapper.BjInitDataMapper;
import cn.yfyue.sysauth.mapper.BjInitTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Init {
@Autowired	
private BjInitTypeMapper bjInitTypeMapper;
@Autowired	
private BjInitDataMapper bjInitDataMapper;
//配置类型列表
public List<BjInitType> getInitTypeList(String qkey, int startRow, int rowNum) {
	return bjInitTypeMapper.getInitTypeList(qkey, startRow, rowNum);
}			
//配置类型统计
public int getInitTypeCount(String qkey) {
	return bjInitTypeMapper.getInitTypeCount(qkey);
}	
//配置数据列表
public List<BjInitData> getInitDataList(String typeCode, String qkey, int startRow, int rowNum) {
	return bjInitDataMapper.getInitDataList(typeCode, qkey, startRow, rowNum);
}
//配置数据统计
public int getInitDataCount(String typeCode,String qkey) {
	return bjInitDataMapper.getInitDataCount(typeCode, qkey);
}

//缓存加载初始化数据 从数据库中加载
@Cacheable("catchLoadInitData")
public List<BjInitData> catchLoadInitData() {
	return bjInitDataMapper.catchLoadInitData();
}

//添加类型
public int addInitType(BjInitType bjInitType ) {
	return bjInitTypeMapper.addInitType(bjInitType);
}	
//取单个类型
public BjInitType getInitTypeInfo(String initTypeId) {
	return bjInitTypeMapper.getInitTypeInfo(initTypeId);
}
//更新
public int upInitType(BjInitType bjInitType) {
	return bjInitTypeMapper.upInitType(bjInitType);
}
//删除
public int delInitType(String initTypeId) {
	return bjInitTypeMapper.delInitType(initTypeId);
}
//验证是否有下级数据
public int getInitTypeIsChild(String typeCode) {
	return bjInitTypeMapper.getInitTypeIsChild(typeCode);
}
//验证编码是否存在
public int isInitTypeCode(String initTypeCode) {
	return bjInitTypeMapper.isInitTypeCode(initTypeCode);
}




//添加数据
public int addInitData(BjInitData bjInitData) {
	return bjInitDataMapper.addInitData(bjInitData);
}
//取单个数据
public BjInitData getInitDataInfo(String initDataId) {
	return bjInitDataMapper.getInitDataInfo(initDataId);
}
//更新数据
public int upInitData(BjInitData bjInitData) {
	return bjInitDataMapper.upInitData(bjInitData);
}
//禁用
public int delInitData(String initDataId) {
	return bjInitDataMapper.delInitData(initDataId);
}
//验证编码是否存在
public int isInitDataCode(String initDataCode) {
	return bjInitDataMapper.isInitDataCode(initDataCode);
}





}
