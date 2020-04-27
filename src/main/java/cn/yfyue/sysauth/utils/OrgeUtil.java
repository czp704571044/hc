package cn.yfyue.sysauth.utils;



import cn.yfyue.sysauth.model.BjOrge;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrgeUtil {
//找当前结点以下	
public static List <OrgeWarp> getOrgeChildNodes(List<BjOrge> list) {
List <BjOrge> list_1 = null;
List <BjOrge> list_2 = null;
List <BjOrge> list_3 = null;
List <BjOrge> list_4 = null;
BjOrge orge_1 = null;
BjOrge orge_2 = null;
BjOrge orge_3 = null;
BjOrge orge_4 = null;
OrgeWarp pWarp = null;
OrgeWarp cWarp = null;
OrgeWarp xWarp = null;
OrgeWarp zWarp = null;
List <OrgeWarp> xWarpList = null;
List <OrgeWarp> cWarpList = null;
List <OrgeWarp> zWarpList = null;
List <OrgeWarp> returnList = new ArrayList <OrgeWarp> ();
String code = "0";
if (list != null && !list.isEmpty()) {
	//省子节点
list_1 = getChildList(list, code);
if (list_1 != null && !list_1.isEmpty()) {
	for (int i = 0; i < list_1.size(); i++) {
		orge_1 = (BjOrge)list_1.get(i);
		if (orge_1 != null) {
			//取所有城市
			list_2 = getChildList(list, orge_1.getOrgeId());
			if (list_2 != null && !list_2.isEmpty()) {
				cWarpList = new ArrayList < OrgeWarp > ();
				for (int j = 0; j < list_2.size(); j++) {
					orge_2 = (BjOrge)list_2.get(j);
					if (orge_2 != null) {
						list_3 = getChildList(list, orge_2.getOrgeId());
						if (list_3 != null && !list_3.isEmpty()) {
							xWarpList = new ArrayList < OrgeWarp > ();
							for (int k = 0; k < list_3.size(); k++) {
								orge_3 = (BjOrge)list_3.get(k);
								if (orge_3 != null) {
									//取镇列表
									list_4 = getChildList(list, orge_3.getOrgeId());
									if (list_4 != null && !list_4.isEmpty()) {
										zWarpList = new ArrayList < OrgeWarp > ();
										for (int z = 0; z < list_4.size(); z++) {
											orge_4 = (BjOrge)list_4.get(z);
											if (orge_4 != null) {
												//添加镇
												zWarp = new OrgeWarp();
												zWarp.setOrgeId(orge_4.getOrgeId());
												zWarp.setOrgeLevel(orge_4.getOrgeLevel());
												zWarp.setOrgeName(orge_4.getOrgeName());
												zWarp.setOrgeState(orge_4.getOrgeState());
												zWarp.setOrgeType(orge_4.getOrgeType());
												zWarp.setOrgeFullName(orge_4.getOrgeFullName());
												zWarp.setSuperiorOrgeId(orge_4.getSuperiorOrgeId());
												zWarpList.add(zWarp);
											}
										}
									}
									//添加县
									xWarp = new OrgeWarp();
									xWarp.setOrgeId(orge_3.getOrgeId());
									xWarp.setOrgeLevel(orge_3.getOrgeLevel());
									xWarp.setOrgeName(orge_3.getOrgeName());
									xWarp.setOrgeState(orge_3.getOrgeState());
									xWarp.setOrgeFullName(orge_3.getOrgeFullName());
									xWarp.setSuperiorOrgeId(orge_3.getSuperiorOrgeId());
									xWarp.setOrgeType(orge_3.getOrgeType());
									xWarp.setChildren(zWarpList);
									xWarpList.add(xWarp);
									zWarpList=null;
								}
							}
						}
						//市
						cWarp = new OrgeWarp();	
						cWarp.setOrgeId(orge_2.getOrgeId());
						cWarp.setOrgeLevel(orge_2.getOrgeLevel());
						cWarp.setOrgeName(orge_2.getOrgeName());
						cWarp.setOrgeState(orge_2.getOrgeState());
						cWarp.setOrgeType(orge_2.getOrgeType());
						cWarp.setOrgeFullName(orge_2.getOrgeFullName());
						cWarp.setSuperiorOrgeId(orge_2.getSuperiorOrgeId());
						cWarp.setChildren(xWarpList);
						cWarpList.add(cWarp);
						xWarpList=null;
					}
				}
			}
			//添加省
				pWarp = new OrgeWarp();
				pWarp.setOrgeId(orge_1.getOrgeId());
				pWarp.setOrgeLevel(orge_1.getOrgeLevel());
				pWarp.setOrgeName(orge_1.getOrgeName());
				pWarp.setOrgeState(orge_1.getOrgeState());
				pWarp.setOrgeType(orge_1.getOrgeType());
				pWarp.setOrgeFullName(orge_1.getOrgeFullName());
				pWarp.setSuperiorOrgeId(orge_1.getSuperiorOrgeId());
				pWarp.setChildren(cWarpList);
				returnList.add(pWarp);
				cWarpList=null;
			}
		}
	}
}
return returnList;
}
   

   // 得到子节点列表
   private static List<BjOrge> getChildList(List<BjOrge> list,String funcId) {
       List<BjOrge> childFuncList = new ArrayList<BjOrge>();
       Iterator<BjOrge> it = list.iterator();
       while (it.hasNext()) {
    	   BjOrge n = (BjOrge) it.next();
    	   if(n!=null){
               if (n.getSuperiorOrgeId().equals(funcId)) {
            	   childFuncList.add(n);
               } 
    	   }
       }
       return childFuncList;
   }   



  //运行
//   public static void main(String arge[])throws Exception{
//	   Func func=new Func();
//	   List<BjOrge> funclist=func.getFuncAllList();
//	   FuncUtil funcUtil=new FuncUtil();
//	   JsonUtil j=new JsonUtil();
//       L.p("-----");
//	   List<OrgeWarp> ffList=funcUtil.getFuncChildNodes(funclist);
//	   L.p("-----");
//	   String aa=j.toJson(ffList);
//	   L.p("-----");
//	   System.out.println(aa);
//	   L.p("-----");
//   }
}
