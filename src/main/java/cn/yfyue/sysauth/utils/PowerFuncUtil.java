package cn.yfyue.sysauth.utils;

import cn.yfyue.comm.JsonUtil;
import cn.yfyue.comm.L;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PowerFuncUtil {
//找当前结点以下	
public static List <PowerFuncWarp> getPowerFunc(List <PowerFunc> list) {
List <PowerFunc> list_1 = null;
List <PowerFunc> list_2 = null;
List <PowerFunc> list_3 = null;
List <PowerFunc> list_4 = null;
List <PowerFunc> list_5 = null;
List <PowerFunc> list_6 = null;
List <PowerFunc> list_7 = null;
PowerFunc obj_1 = null;
PowerFunc obj_2 = null;
PowerFunc obj_3 = null;
PowerFunc obj_4 = null;
PowerFunc obj_5 = null;
PowerFunc obj_6 = null;
PowerFunc obj_7 = null;
PowerFuncWarp warp_1 = null;
PowerFuncWarp warp_2 = null;
PowerFuncWarp warp_3 = null;
PowerFuncWarp warp_4 = null;
PowerFuncWarp warp_5 = null;
PowerFuncWarp warp_6 = null;
PowerFuncWarp warp_7 = null;
List <PowerFuncWarp> warpList_2 = null;
List <PowerFuncWarp> warpList_3 = null;
List <PowerFuncWarp> warpList_4 = null;
List <PowerFuncWarp> warpList_5 = null;
List <PowerFuncWarp> warpList_6 = null;
List <PowerFuncWarp> warpList_7 = null;
List <PowerFuncWarp> returnList = new ArrayList <PowerFuncWarp> ();

String code = "0";
if (list != null && !list.isEmpty()) {
	list_1 = getChildList(list, code);
	if (list_1 != null && !list_1.isEmpty()) {
	   for (int i = 0; i < list_1.size(); i++) {
		  obj_1 = (PowerFunc)list_1.get(i);	
		  if(obj_1!=null){
			 list_2 = getChildList(list, obj_1.getFuncId());   
			 if (list_2 != null && !list_2.isEmpty()) {
				 warpList_2 = new ArrayList <PowerFuncWarp>(); 
				 for (int j = 0; j < list_2.size(); j++) {
					 obj_2 = (PowerFunc)list_2.get(j);
				   if (obj_2 != null) {
					 list_3 = getChildList(list, obj_2.getFuncId());
					 if (list_3 != null && !list_3.isEmpty()) {
					   warpList_3 = new ArrayList <PowerFuncWarp>(); 
					   for (int k = 0; k < list_3.size(); k++) {
						   obj_3 = (PowerFunc)list_3.get(k);
						   if (obj_3 != null) {
							  list_4 = getChildList(list, obj_3.getFuncId()); 
							  if (list_4 != null && !list_4.isEmpty()) {
								  warpList_4 = new ArrayList <PowerFuncWarp>();  
								  for (int z = 0; z < list_4.size(); z++) {
									  obj_4 = (PowerFunc)list_4.get(z);
									  if(obj_4!=null){
										  list_5 = getChildList(list, obj_4.getFuncId());
											if (list_5 != null && !list_5.isEmpty()) {
											  warpList_5 = new ArrayList <PowerFuncWarp>();
											  for (int s = 0; s < list_5.size(); s++) {
												obj_5 = (PowerFunc)list_5.get(s);  
												if (obj_5 != null) {
												   list_6 = getChildList(list, obj_5.getFuncId());
												   if (list_6 != null && !list_6.isEmpty()) {
													   warpList_6 = new ArrayList <PowerFuncWarp> ();
													   for (int d = 0; d < list_6.size(); d++) {
														 obj_6 = (PowerFunc)list_6.get(d);  
														 if(obj_6!=null){
														    list_7 = getChildList(list, obj_6.getFuncId()); 
														    if (list_7 != null && !list_7.isEmpty()) {
														      warpList_7 = new ArrayList <PowerFuncWarp>();	
														      for (int x = 0; x < list_7.size(); x++) {
														    	obj_7 = (PowerFunc)list_7.get(x);
														    	if(obj_7!=null){
																	/********************************/
																 warp_7 = new PowerFuncWarp();
																 warp_7.setChecked(obj_7.isChecked());
																 warp_7.setFuncAlias(obj_7.getFuncAlias());
																 warp_7.setFuncId(obj_7.getFuncId());
																 warp_7.setIsLeaf(obj_7.getIsLeaf());
																 warp_7.setFuncName(obj_7.getFuncName());
																 warp_7.setFuncUrl(obj_7.getFuncUrl());
																 warp_7.setStyleImg(obj_7.getStyleImg());
																 warp_7.setSupperFuncId(obj_7.getSupperFuncId());
																 warpList_7.add(warp_7);
													    	}
													      }
													    }
													/********************************/
													 warp_6 = new PowerFuncWarp();
													 warp_6.setChecked(obj_6.isChecked());
													 warp_6.setFuncAlias(obj_6.getFuncAlias());
													 warp_6.setFuncId(obj_6.getFuncId());
													 warp_6.setFuncName(obj_6.getFuncName());
													 warp_6.setFuncUrl(obj_6.getFuncUrl());
													 warp_6.setIsLeaf(obj_6.getIsLeaf());
													 warp_6.setStyleImg(obj_6.getStyleImg());
													 warp_6.setSupperFuncId(obj_6.getSupperFuncId());
													 warp_6.setChildren(warpList_7);
													 warpList_6.add(warp_6);
													 warpList_7=null;		  
													 }
												   }
											   }	
											/********************************/
											 warp_5 = new PowerFuncWarp();
											 warp_5.setChecked(obj_5.isChecked());
											 warp_5.setFuncAlias(obj_5.getFuncAlias());
											 warp_5.setFuncId(obj_5.getFuncId());
											 warp_5.setFuncName(obj_5.getFuncName());
											 warp_5.setFuncUrl(obj_5.getFuncUrl());
											 warp_5.setIsLeaf(obj_5.getIsLeaf());
											 warp_5.setStyleImg(obj_5.getStyleImg());
											 warp_5.setSupperFuncId(obj_5.getSupperFuncId());
											 warp_5.setChildren(warpList_6);
											 warpList_5.add(warp_5);
											 warpList_6=null;	  	
											}
										  }
										}
									/********************************/
									 warp_4 = new PowerFuncWarp();
									 warp_4.setChecked(obj_4.isChecked());
									 warp_4.setFuncAlias(obj_4.getFuncAlias());
									 warp_4.setFuncId(obj_4.getFuncId());
									 warp_4.setFuncName(obj_4.getFuncName());
									 warp_4.setIsLeaf(obj_4.getIsLeaf());
									 warp_4.setFuncUrl(obj_4.getFuncUrl());
									 warp_4.setStyleImg(obj_4.getStyleImg());
									 warp_4.setSupperFuncId(obj_4.getSupperFuncId());
									 warp_4.setChildren(warpList_5);
									 warpList_4.add(warp_4);
									 warpList_5=null;	  
								  }
							  }
						  }
						/********************************/
						 warp_3 = new PowerFuncWarp();
						 warp_3.setChecked(obj_3.isChecked());
						 warp_3.setFuncAlias(obj_3.getFuncAlias());
						 warp_3.setFuncId(obj_3.getFuncId());
						 warp_3.setFuncName(obj_3.getFuncName());
						 warp_3.setFuncUrl(obj_3.getFuncUrl());
						 warp_3.setIsLeaf(obj_3.getIsLeaf());
						 warp_3.setStyleImg(obj_3.getStyleImg());
						 warp_3.setSupperFuncId(obj_3.getSupperFuncId());
						 warp_3.setChildren(warpList_4);
						 warpList_3.add(warp_3);
						 warpList_4=null;	  
					   }
				   }
				 }
				/********************************/
				 warp_2 = new PowerFuncWarp();
				 warp_2.setChecked(obj_2.isChecked());
				 warp_2.setFuncAlias(obj_2.getFuncAlias());
				 warp_2.setFuncId(obj_2.getFuncId());
				 warp_2.setFuncName(obj_2.getFuncName());
				 warp_2.setFuncUrl(obj_2.getFuncUrl());
				 warp_2.setIsLeaf(obj_2.getIsLeaf());
				 warp_2.setStyleImg(obj_2.getStyleImg());
				 warp_2.setSupperFuncId(obj_2.getSupperFuncId());
				 warp_2.setChildren(warpList_3);
				 warpList_2.add(warp_2);
				 warpList_3=null;	  
				   
			   }
			 }
		 }
	  /********************************/
				warp_1 = new PowerFuncWarp();
				warp_1.setChecked(obj_1.isChecked());
				warp_1.setFuncAlias(obj_1.getFuncAlias());
				warp_1.setFuncId(obj_1.getFuncId());
				warp_1.setFuncName(obj_1.getFuncName());
				warp_1.setFuncUrl(obj_1.getFuncUrl());
				warp_1.setStyleImg(obj_1.getStyleImg());
				warp_1.setIsLeaf(obj_1.getIsLeaf());
				warp_1.setSupperFuncId(obj_1.getSupperFuncId());
				warp_1.setChildren(warpList_2);
				returnList.add(warp_1);
				warpList_2=null;	  
			  }
		   }
		}
	}
	return returnList;	      
}   

   // 得到子节点列表
   private static List<PowerFunc> getChildList(List<PowerFunc> list,String funcId) {
       List<PowerFunc> childPowerFuncList = new ArrayList<PowerFunc>();
       Iterator<PowerFunc> pf = list.iterator();
       while (pf.hasNext()) {
    	   PowerFunc n = (PowerFunc) pf.next();
    	   if(n!=null){
               if (n.getSupperFuncId().equals(funcId)) {
            	   childPowerFuncList.add(n);
               } 
    	   }
       }
       return childPowerFuncList;
   } 

	  //运行
   public static void main(String arge[])throws Exception{

	   JsonUtil j=new JsonUtil();
       L.p("-----");

   }
}
