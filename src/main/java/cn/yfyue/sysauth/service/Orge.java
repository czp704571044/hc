package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjOrge;
import cn.yfyue.sysauth.mapper.BjOrgeMapper;
import cn.yfyue.sysauth.model.BjOrgeLayUi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Orge {
    @Autowired
    private BjOrgeMapper bjOrgeMapper;

    //取所有部门
    public List<BjOrge> getOrgeList(String orgeState) {
        return bjOrgeMapper.getOrgeList(orgeState);
    }

    //取所有部门
    public List<BjOrgeLayUi> getLayUiOrgeList(Integer pid) {
        return bjOrgeMapper.getLayUiOrgeList(pid);
    }

    //添加
    public int addOrge(BjOrge bjOrge) {
        return bjOrgeMapper.addOrge(bjOrge);
    }

    //取单个
    public BjOrge getOrgeInfo(String orgeId) {
        return bjOrgeMapper.getOrgeInfo(orgeId);
    }

    //更新
    public int upOrge(BjOrge bjOrge) {
        return bjOrgeMapper.upOrge(bjOrge);
    }

    //删除
    public int delOrge(String orgeId) {
        return bjOrgeMapper.delOrge(orgeId);
    }

    //是否下级
    public int getOrgeIsChild(String orgeId) {
        return bjOrgeMapper.getOrgeIsChild(orgeId);
    }

    //是否已使用
    public int getOrgeIsUse(String orgeId) {
        return bjOrgeMapper.getOrgeIsUse(orgeId);
    }
}
