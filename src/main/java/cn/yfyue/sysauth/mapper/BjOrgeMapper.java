package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjOrge;
import cn.yfyue.sysauth.model.BjOrgeLayUi;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjOrgeMapper {
//取所有部门
List<BjOrge> getOrgeList(@Param("orgeState") String orgeState);
//添加
int addOrge(BjOrge bjOrge);
//取单个
BjOrge getOrgeInfo(String orgeId);
//更新
int upOrge(BjOrge bjOrge);
//删除
int delOrge(String orgeId);
//是否下级
int getOrgeIsChild(String orgeId); 
//是否已使用
int getOrgeIsUse(String orgeId);

List<BjOrgeLayUi> getLayUiOrgeList(Integer pid);

}