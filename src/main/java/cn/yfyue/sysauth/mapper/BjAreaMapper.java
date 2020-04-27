package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjArea;
import org.apache.ibatis.annotations.Param;


import java.util.List;



public interface BjAreaMapper {
//取地区列表
List<BjArea> getAreaList(@Param("areaCode") String areaCode);
//添加
int addArea(BjArea bjArea);
//取单个类型
BjArea getAreaInfo(String areaCode);
//更新
int upArea(BjArea bjArea);
//删除
int delArea(String areaCode);
//验证是否有下级数据
int getAreaIsChild(String areaCode);
//验证编码是否存在
int isAreaCode(String areaCode);
//取省目录列表
List<BjArea> getFirstAreaList();
}