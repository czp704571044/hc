package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjButton;

import java.util.List;

public interface BjButtonMapper {
//取按钮列表
List<BjButton> getButtonList(String funcId);
//添加
int addButton(BjButton bjButton);
//取单个
BjButton getButtonInfo(String buttonId);
//修改
int upButton(BjButton bjButton);
//删除
int delButton(String buttonId); 
//按功能ID清掉按钮
int delButtonForFuncId(String funcId);
}