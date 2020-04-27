package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjButton;
import cn.yfyue.sysauth.mapper.BjButtonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class Button {
@Autowired	
private BjButtonMapper bBjButtonMapper;
//取按钮列表
	public List<BjButton> getButtonList(String funcId) {
		return bBjButtonMapper.getButtonList(funcId);
	}	
//添加
	public int addButton(BjButton bjButton ) {
		return bBjButtonMapper.addButton(bjButton);
	}	
//取单个
	public BjButton getButtonInfo(String buttonId) {
		return bBjButtonMapper.getButtonInfo(buttonId);
	}	
//修改
	public int upButton(BjButton bjButton) {
		return bBjButtonMapper.upButton(bjButton);
	}
//删除
	public int delButton(String buttonId) {
		return bBjButtonMapper.delButton(buttonId);
	}
//按功能ID清掉按钮
	public int delButtonForFuncId(String funcId) {
		return bBjButtonMapper.delButtonForFuncId(funcId);
	}
	
}
