package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjInitData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjInitDataMapper {
//配置数据列表
List<BjInitData> getInitDataList(@Param("typeCode") String typeCode, @Param("qkey") String qkey,
                                 @Param("startRow") int startRow, @Param("rowNum") int rowNum);
//配置数据统计
int getInitDataCount(@Param("typeCode") String typeCode, @Param("qkey") String qkey);


//添加数据
int addInitData(BjInitData bjInitData);
//取单个数据
BjInitData getInitDataInfo(String initDataId);
//更新数据
int upInitData(BjInitData bjInitData);
//删除
int delInitData(String initDataId);
//验证编码是否存在
int isInitDataCode(String initDataCode);
//缓存初始化数据
List<BjInitData> catchLoadInitData();
}