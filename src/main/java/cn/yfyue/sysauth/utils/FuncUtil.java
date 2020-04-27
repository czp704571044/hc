package cn.yfyue.sysauth.utils;

import cn.yfyue.comm.JsonUtil;
import cn.yfyue.comm.L;
import cn.yfyue.sysauth.model.BjFunc;
import cn.yfyue.sysauth.service.Func;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FuncUtil {
//找当前结点以下	
public static List <FuncWarp> getFuncChildNodes(List <BjFunc> list) {
List <BjFunc> list_1 = null;
List <BjFunc> list_2 = null;
List <BjFunc> list_3 = null;
List <BjFunc> list_4 = null;
List <BjFunc> list_5 = null;
List <BjFunc> list_6 = null;
BjFunc func_1 = null;
BjFunc func_2 = null;
BjFunc func_3 = null;
BjFunc func_4 = null;
BjFunc func_5 = null;
BjFunc func_6 = null;
FuncWarp pWarp = null;
FuncWarp cWarp = null;
FuncWarp xWarp = null;
FuncWarp zWarp = null;
FuncWarp sWarp = null;
FuncWarp dWarp = null;
List <FuncWarp> xWarpList = null;
List <FuncWarp> cWarpList = null;
List <FuncWarp> zWarpList = null;
List <FuncWarp> sWarpList = null;
List <FuncWarp> dWarpList = null;
List <FuncWarp> returnList = new ArrayList <FuncWarp> ();
String code = "0";
if (list != null && !list.isEmpty()) {

	//省子节点
	list_1 = getChildList(list, code);
	if (list_1 != null && !list_1.isEmpty()) {
		for (int i = 0; i < list_1.size(); i++) {
			func_1 = (BjFunc)list_1.get(i);
			if (func_1 != null) {
				//取所有城市
				list_2 = getChildList(list, func_1.getFuncId());
				if (list_2 != null && !list_2.isEmpty()) {
					cWarpList = new ArrayList < FuncWarp > ();
					for (int j = 0; j < list_2.size(); j++) {
						func_2 = (BjFunc)list_2.get(j);
						if (func_2 != null) {
							list_3 = getChildList(list, func_2.getFuncId());
							if (list_3 != null && !list_3.isEmpty()) {
								xWarpList = new ArrayList < FuncWarp > ();
								for (int k = 0; k < list_3.size(); k++) {
									func_3 = (BjFunc)list_3.get(k);
									if (func_3 != null) {
										//取镇列表
										list_4 = getChildList(list, func_3.getFuncId());
										if (list_4 != null && !list_4.isEmpty()) {
											zWarpList = new ArrayList < FuncWarp > ();
											for (int z = 0; z < list_4.size(); z++) {
												func_4 = (BjFunc)list_4.get(z);
												if (func_4 != null) {
													//取村列表
													list_5 = getChildList(list, func_4.getFuncId());
													if (list_5 != null && !list_5.isEmpty()) {
														sWarpList = new ArrayList < FuncWarp > ();
														for (int s = 0; s < list_5.size(); s++) {
															func_5 = (BjFunc)list_5.get(s);
															if (func_5 != null) {
																//取队
																list_6 = getChildList(list, func_5.getFuncId());
																if (list_6 != null && !list_6.isEmpty()) {
																	dWarpList = new ArrayList < FuncWarp > ();
																	for (int d = 0; d < list_6.size(); d++) {
																		func_6 = (BjFunc)list_6.get(d);
																		//添加队
																		if (func_6 != null) {
																			dWarp = new FuncWarp();
																			dWarp.setFuncId(func_6.getFuncId());
																			dWarp.setFuncAlias(func_6.getFuncAlias());
																			dWarp.setFuncLevel(func_6.getFuncLevel());
																			dWarp.setFuncName(func_6.getFuncName());
																			dWarp.setFuncOrder(func_6.getFuncOrder());
																			dWarp.setFuncState(func_6.getFuncState());
																			dWarp.setFuncUrl(func_6.getFuncUrl());
																			dWarp.setIsLeaf(func_6.getIsLeaf());
																			dWarp.setSupperFuncId(func_6.getSupperFuncId());
																			dWarpList.add(dWarp);
																		}
																	}
																}
																//添加村
																sWarp = new FuncWarp();
																sWarp.setFuncId(func_5.getFuncId());
																sWarp.setFuncAlias(func_5.getFuncAlias());
																sWarp.setFuncLevel(func_5.getFuncLevel());
																sWarp.setFuncName(func_5.getFuncName());
																sWarp.setFuncOrder(func_5.getFuncOrder());
																sWarp.setFuncState(func_5.getFuncState());
																sWarp.setFuncUrl(func_5.getFuncUrl());
																sWarp.setIsLeaf(func_5.getIsLeaf());
																sWarp.setSupperFuncId(func_5.getSupperFuncId());
																sWarp.setChildren(dWarpList);
																sWarpList.add(sWarp);
																dWarpList=null;
															}
														}
													}
													//添加镇
													zWarp = new FuncWarp();
													zWarp.setFuncId(func_4.getFuncId());
													zWarp.setFuncAlias(func_4.getFuncAlias());
													zWarp.setFuncLevel(func_4.getFuncLevel());
													zWarp.setFuncName(func_4.getFuncName());
													zWarp.setFuncOrder(func_4.getFuncOrder());
													zWarp.setFuncState(func_4.getFuncState());
													zWarp.setFuncUrl(func_4.getFuncUrl());
													zWarp.setIsLeaf(func_4.getIsLeaf());
													zWarp.setSupperFuncId(func_4.getSupperFuncId());
													zWarp.setChildren(sWarpList);
													zWarpList.add(zWarp);
													sWarpList=null;
												}
											}
										}
										//添加县
										xWarp = new FuncWarp();
										xWarp.setFuncId(func_3.getFuncId());
										xWarp.setFuncAlias(func_3.getFuncAlias());
										xWarp.setFuncLevel(func_3.getFuncLevel());
										xWarp.setFuncName(func_3.getFuncName());
										xWarp.setFuncOrder(func_3.getFuncOrder());
										xWarp.setFuncState(func_3.getFuncState());
										xWarp.setFuncUrl(func_3.getFuncUrl());
										xWarp.setIsLeaf(func_3.getIsLeaf());
										xWarp.setSupperFuncId(func_3.getSupperFuncId());
										xWarp.setChildren(zWarpList);
										xWarpList.add(xWarp);
										zWarpList=null;
									}
								}
							}
							//市
							cWarp = new FuncWarp();						
							cWarp.setFuncId(func_2.getFuncId());
							cWarp.setFuncAlias(func_2.getFuncAlias());
							cWarp.setFuncLevel(func_2.getFuncLevel());
							cWarp.setFuncName(func_2.getFuncName());
							cWarp.setFuncOrder(func_2.getFuncOrder());
							cWarp.setFuncState(func_2.getFuncState());
							cWarp.setFuncUrl(func_2.getFuncUrl());
							cWarp.setIsLeaf(func_2.getIsLeaf());
							cWarp.setSupperFuncId(func_2.getSupperFuncId());
							cWarp.setChildren(xWarpList);
							cWarpList.add(cWarp);
							xWarpList=null;
						}
					}
				}
				//添加省
				pWarp = new FuncWarp();
				pWarp.setFuncId(func_1.getFuncId());
				pWarp.setFuncAlias(func_1.getFuncAlias());
				pWarp.setFuncLevel(func_1.getFuncLevel());
				pWarp.setFuncName(func_1.getFuncName());
				pWarp.setFuncOrder(func_1.getFuncOrder());
				pWarp.setFuncState(func_1.getFuncState());
				pWarp.setFuncUrl(func_1.getFuncUrl());
				pWarp.setIsLeaf(func_1.getIsLeaf());
				pWarp.setSupperFuncId(func_1.getSupperFuncId());
				pWarp.setChildren(cWarpList);
				returnList.add(pWarp);
				cWarpList=null;
			}
		}
	}
}
return returnList;
}
	//树结构功能
	public static List <FuncWarp> getFuncTreeChildNodes(List <BjFunc> list) {
		List <BjFunc> list_1 = null;
		List <BjFunc> list_2 = null;
		List <BjFunc> list_3 = null;
		List <BjFunc> list_4 = null;
		List <BjFunc> list_5 = null;
		List <BjFunc> list_6 = null;
		BjFunc func_1 = null;
		BjFunc func_2 = null;
		BjFunc func_3 = null;
		BjFunc func_4 = null;
		BjFunc func_5 = null;
		BjFunc func_6 = null;
		FuncWarp pWarp = null;
		FuncWarp cWarp = null;
		FuncWarp xWarp = null;
		FuncWarp zWarp = null;
		FuncWarp sWarp = null;
		FuncWarp dWarp = null;
		List <FuncWarp> xWarpList = null;
		List <FuncWarp> cWarpList = null;
		List <FuncWarp> zWarpList = null;
		List <FuncWarp> sWarpList = null;
		List <FuncWarp> dWarpList = null;
		List <FuncWarp> returnList = new ArrayList <FuncWarp> ();
		String code = "0";
		if (list != null && !list.isEmpty()) {

			//省子节点
			list_1 = getChildList(list, code);
			if (list_1 != null && !list_1.isEmpty()) {
				for (int i = 0; i < list_1.size(); i++) {
					func_1 = (BjFunc)list_1.get(i);
					if (func_1 != null) {
						//取所有城市
						list_2 = getChildList(list, func_1.getFuncId());
						if (list_2 != null && !list_2.isEmpty()) {
							cWarpList = new ArrayList < FuncWarp > ();
							for (int j = 0; j < list_2.size(); j++) {
								func_2 = (BjFunc)list_2.get(j);
								if (func_2 != null) {
									list_3 = getChildList(list, func_2.getFuncId());
									if (list_3 != null && !list_3.isEmpty()) {
										xWarpList = new ArrayList < FuncWarp > ();
										for (int k = 0; k < list_3.size(); k++) {
											func_3 = (BjFunc)list_3.get(k);
											if (func_3 != null) {
												//取镇列表
												list_4 = getChildList(list, func_3.getFuncId());
												if (list_4 != null && !list_4.isEmpty()) {
													zWarpList = new ArrayList < FuncWarp > ();
													for (int z = 0; z < list_4.size(); z++) {
														func_4 = (BjFunc)list_4.get(z);
														if (func_4 != null) {
															//取村列表
															list_5 = getChildList(list, func_4.getFuncId());
															if (list_5 != null && !list_5.isEmpty()) {
																sWarpList = new ArrayList < FuncWarp > ();
																for (int s = 0; s < list_5.size(); s++) {
																	func_5 = (BjFunc)list_5.get(s);
																	if (func_5 != null) {
																		//取队
																		list_6 = getChildList(list, func_5.getFuncId());
																		if (list_6 != null && !list_6.isEmpty()) {
																			dWarpList = new ArrayList < FuncWarp > ();
																			for (int d = 0; d < list_6.size(); d++) {
																				func_6 = (BjFunc)list_6.get(d);
																				//添加队
																				if (func_6 != null) {
																					dWarp = new FuncWarp();
																					dWarp.setId(func_6.getFuncId());
																					dWarp.setTitle(func_6.getFuncName());
																					dWarp.setSupperFuncId(func_6.getSupperFuncId());
																					dWarpList.add(dWarp);
																				}
																			}
																		}
																		//添加村
																		sWarp = new FuncWarp();
																		sWarp.setId(func_5.getFuncId());
																		sWarp.setTitle(func_5.getFuncName());
																		sWarp.setSupperFuncId(func_5.getSupperFuncId());
																		dWarpList.add(dWarp);
																		sWarp.setChildren(dWarpList);
																		sWarpList.add(sWarp);
																		dWarpList=null;
																	}
																}
															}
															//添加镇
															zWarp = new FuncWarp();
															zWarp.setId(func_4.getFuncId());
															zWarp.setTitle(func_4.getFuncName());
															zWarp.setSupperFuncId(func_4.getSupperFuncId());
															zWarp.setChildren(sWarpList);
															zWarpList.add(zWarp);
															sWarpList=null;
														}
													}
												}
												//添加县
												xWarp = new FuncWarp();
												xWarp.setId(func_3.getFuncId());
												xWarp.setTitle(func_3.getFuncName());
												xWarp.setSupperFuncId(func_3.getSupperFuncId());
												xWarp.setChildren(zWarpList);
												xWarpList.add(xWarp);
												zWarpList=null;
											}
										}
									}
									//市
									cWarp = new FuncWarp();
									cWarp.setId(func_2.getFuncId());
									cWarp.setTitle(func_2.getFuncName());
									cWarp.setSupperFuncId(func_2.getSupperFuncId());
									cWarp.setChildren(xWarpList);
									cWarpList.add(cWarp);
									xWarpList=null;
								}
							}
						}
						//添加省
						pWarp = new FuncWarp();
						pWarp.setId(func_1.getFuncId());
						pWarp.setTitle(func_1.getFuncName());
						pWarp.setSupperFuncId(func_1.getSupperFuncId());
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
   private static List<BjFunc> getChildList(List<BjFunc> list,String funcId) {
       List<BjFunc> childFuncList = new ArrayList<BjFunc>();
       Iterator<BjFunc> it = list.iterator();
       while (it.hasNext()) {
    	   BjFunc n = (BjFunc) it.next();
    	   if(n!=null){
               if (n.getSupperFuncId().equals(funcId)) {
            	   childFuncList.add(n);
               } 
    	   }
       }
       return childFuncList;
   }   



  //运行
   public static void main(String arge[])throws Exception{
	   Func func=new Func();
	   List<BjFunc> funclist=func.getFuncAllList();
	   FuncUtil funcUtil=new FuncUtil();
	   JsonUtil j=new JsonUtil();
       L.p("-----");
	   List<FuncWarp> ffList=funcUtil.getFuncChildNodes(funclist);
	   L.p("-----");
	   String aa=j.toJson(ffList);
	   L.p("-----");
	   System.out.println(aa);
	   L.p("-----");
   }
}
