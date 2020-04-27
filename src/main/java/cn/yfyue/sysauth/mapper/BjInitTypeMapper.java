package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjInitType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjInitTypeMapper {
//配置类型列表
List<BjInitType> getInitTypeList(@Param("qkey") String qkey,
                                 @Param("startRow") int startRow, @Param("rowNum") int rowNum);
//配置类型统计
int getInitTypeCount(@Param("qkey") String qkey);

//添加类型
int addInitType(BjInitType bjInitType);
//取单个类型
BjInitType getInitTypeInfo(String initTypeId);
//更新
int upInitType(BjInitType bjInitType);
//删除
int delInitType(String initTypeId);
//验证是否有下级数据
int getInitTypeIsChild(String typeCode);
//验证编码是否存在
int isInitTypeCode(String initTypeCode);
}