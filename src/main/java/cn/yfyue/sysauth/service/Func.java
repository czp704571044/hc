package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjFunc;
import cn.yfyue.sysauth.mapper.BjFuncMapper;
import cn.yfyue.sysauth.utils.FuncWarp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Func {
    @Autowired
    private BjFuncMapper bjFuncMapper;

    //取所有功能
    public List<BjFunc> getFuncAllList() {
        return bjFuncMapper.getFuncAllList();
    }

    //缓存按钮
    @Cacheable("getFuncList")
    public List<FuncWarp> getFuncList(String superId) {
        return bjFuncMapper.getFuncList(superId);
    }


    //添加
    public int addFunc(BjFunc bjFunc) {
        return bjFuncMapper.addFunc(bjFunc);
    }

    //获取单个
    public BjFunc getFuncInfo(int funcId) {
        return bjFuncMapper.getFuncInfo(funcId);
    }

    //更新
    public int upFunc(BjFunc bjFunc) {
        return bjFuncMapper.upFunc(bjFunc);
    }

    //删除
    public int delFunc(String funcId) {
        return bjFuncMapper.delFunc(funcId);
    }

    //是否有子节点
    public int getFuncIsChild(String funcId) {
        return bjFuncMapper.getFuncIsChild(funcId);
    }


//删除用户功能表void
//删除角色功能表void
//删除人员按钮表void
//删除按钮表void

}
