package cn.yfyue.sysauth.service;


import cn.yfyue.sysauth.model.BjArea;
import cn.yfyue.sysauth.mapper.BjAreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Area {

    @Autowired
    private BjAreaMapper bjAreaMapper;

    //取地区列表
    public List<BjArea> getAreaList(String areaCode) {
        return bjAreaMapper.getAreaList(areaCode);
    }

    //添加
    public int addArea(BjArea bjArea) {
        return bjAreaMapper.addArea(bjArea);
    }

    //取单个类型
    public BjArea getAreaInfo(String areaCode) {
        return bjAreaMapper.getAreaInfo(areaCode);
    }

    //更新
    public int upArea(BjArea bjArea) {
        return bjAreaMapper.upArea(bjArea);
    }

    //删除
    public int delArea(String areaCode) {
        return bjAreaMapper.delArea(areaCode);
    }

    //验证是否有下级数据
    public int getAreaIsChild(String areaCode) {
        return bjAreaMapper.getAreaIsChild(areaCode);
    }

    //验证编码是否存在
    public int isAreaCode(String areaCode) {
        return bjAreaMapper.isAreaCode(areaCode);
    }

    //取省目录列表
    public List<BjArea> getFirstAreaList() {
        return bjAreaMapper.getFirstAreaList();
    }
}
