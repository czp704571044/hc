package cn.yfyue.yyzx.mapper;


import cn.yfyue.yyzx.model.OpOrge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpOrgeMapper {
//取所有部门
List<OpOrge> getOrgeList(@Param("qkey") String qkey,
                         @Param("startRow") int startRow,
                         @Param("rowNum") int rowNum);
//统计
int getOrgeListCount(@Param("qkey") String qkey);
//添加
int addOrge(OpOrge opOrge);
//门店名称是否存在
int isOrgeName(String orgeName);
//取单个
OpOrge getOrgeInfo(String orgeId);
//更新
int upOrge(OpOrge opOrge);
//删除
int delOrge(String orgeId);
//是否下级
int getOrgeIsChild(String orgeId); 
//是否已使用
int getOrgeIsUse(String orgeId);
}