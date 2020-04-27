package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjUserRole;

import java.util.List;

public interface BjUserRoleMapper {
    //添加
    int addUserRole(BjUserRole bjUserRole);

    //取出列表
    List<BjUserRole> getUserRoleList(String userId);

    //删除
    int delUserRole(String userId);
}