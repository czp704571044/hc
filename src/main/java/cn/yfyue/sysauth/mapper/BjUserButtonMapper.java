package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjUserButton;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BjUserButtonMapper {
//取自己的按钮
List<BjUserButton> getUserButtonList(@Param("userId") String userId, @Param("adminLevel") String adminLevel);
//添加分配按钮
int addUserButton(BjUserButton bjUserButton);
//按功能ID清掉
int delFunIdToUserButton(String funcId);
//按按钮ID清掉
int delButtonIdToUserButton(String buttonId);
//按用户清掉
int delUserIdToUserButton(String userId);
//按功能ID和用户取按钮
List<BjUserButton> getUserIdAndFuncIdButtonList(@Param("userId") String userId,
                                                @Param("funcId") String funcId, @Param("adminLevel") String adminLevel);
}