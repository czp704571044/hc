package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjUserArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjUserAreaMapper {
//取用户的地区
List<BjUserArea> getUserAreaList(@Param("userId") String userId,
                                 @Param("adminLevel") String adminLevel);
//添加分配权限
int addUserArea(BjUserArea bjUserArea);
//按地区编码清掉权限
int delAreaCodeToUserArea(String areaCode);
//按用户ID清掉权限
int delUserIdToUserArea(String userId);
}