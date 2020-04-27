package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjHelpDoc;
import cn.yfyue.sysauth.mapper.BjHelpDocMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpDoc {

@Autowired
private BjHelpDocMapper bjHelpDocMapper;
//列表
public List<BjHelpDoc> getHelpDocList(String query_key,
									  String b_time, String e_time, int startRow, int rowNum) {
	return bjHelpDocMapper.getHelpDocList(query_key, b_time, e_time, startRow, rowNum);
}	
//统计
public int getHelpDocListCount(String query_key,
		String b_time,String e_time) {
	return bjHelpDocMapper.getHelpDocListCount(query_key, b_time, e_time);
}
//取单个	
public BjHelpDoc getHelpDocInfo(String docId) {
	return bjHelpDocMapper.getHelpDocInfo(docId);
}	
//添加
public int addHelpDoc(BjHelpDoc bjHelpDoc) {
	return bjHelpDocMapper.addHelpDoc(bjHelpDoc);
}	
//删除
public int delHelpDoc(String docId) {
	return bjHelpDocMapper.delHelpDoc(docId);
}
//更新
public int upHelpDoc(BjHelpDoc bjHelpDoc) {
	return bjHelpDocMapper.upHelpDoc(bjHelpDoc);
}
//验证编码是否存在
public int isDocCode(String docCode) {
	return bjHelpDocMapper.isDocCode(docCode);
}
}
