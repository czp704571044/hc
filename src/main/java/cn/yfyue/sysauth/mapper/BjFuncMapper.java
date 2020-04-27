package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjFunc;
import cn.yfyue.sysauth.utils.FuncWarp;

import java.util.List;

public interface BjFuncMapper {
//取所有功能
List<BjFunc> getFuncAllList();
//添加
int addFunc(BjFunc bjFunc);
//获取单个
BjFunc getFuncInfo(int funcId);
//更新
int upFunc(BjFunc bjFunc);
//删除
int delFunc(String funcId);
//是否有子节点
int getFuncIsChild(String funcId);

List<FuncWarp> getFuncList(String superId);
}