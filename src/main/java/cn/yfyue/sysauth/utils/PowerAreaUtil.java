package cn.yfyue.sysauth.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PowerAreaUtil {
	public static List <PowerAreaWarp> getPowerArea(List <PowerArea> list) {
		List <PowerArea> list_1 = null;
		List <PowerArea> list_2 = null;
		List <PowerArea> list_3 = null;
		List <PowerArea> list_4 = null;
		List <PowerArea> list_5 = null;
		List <PowerArea> list_6 = null;
		List <PowerArea> list_7 = null;
		PowerArea obj_1 = null;
		PowerArea obj_2 = null;
		PowerArea obj_3 = null;
		PowerArea obj_4 = null;
		PowerArea obj_5 = null;
		PowerArea obj_6 = null;
		PowerArea obj_7 = null;
		PowerAreaWarp warp_1 = null;
		PowerAreaWarp warp_2 = null;
		PowerAreaWarp warp_3 = null;
		PowerAreaWarp warp_4 = null;
		PowerAreaWarp warp_5 = null;
		PowerAreaWarp warp_6 = null;
		PowerAreaWarp warp_7 = null;
		List <PowerAreaWarp> warpList_2 = null;
		List <PowerAreaWarp> warpList_3 = null;
		List <PowerAreaWarp> warpList_4 = null;
		List <PowerAreaWarp> warpList_5 = null;
		List <PowerAreaWarp> warpList_6 = null;
		List <PowerAreaWarp> warpList_7 = null;
		List <PowerAreaWarp> returnList = new ArrayList <PowerAreaWarp> ();

		String code = "0";
		if (list != null && !list.isEmpty()) {
			list_1 = getChildList(list, code);
			if (list_1 != null && !list_1.isEmpty()) {
			   for (int i = 0; i < list_1.size(); i++) {
				  obj_1 = (PowerArea)list_1.get(i);	
				  if(obj_1!=null){
					 list_2 = getChildList(list, obj_1.getAreaCode());   
					 if (list_2 != null && !list_2.isEmpty()) {
						 warpList_2 = new ArrayList <PowerAreaWarp>(); 
						 for (int j = 0; j < list_2.size(); j++) {
							 obj_2 = (PowerArea)list_2.get(j);
						   if (obj_2 != null) {
							 list_3 = getChildList(list, obj_2.getAreaCode());
							 if (list_3 != null && !list_3.isEmpty()) {
							   warpList_3 = new ArrayList <PowerAreaWarp>(); 
							   for (int k = 0; k < list_3.size(); k++) {
								   obj_3 = (PowerArea)list_3.get(k);
								   if (obj_3 != null) {
									  list_4 = getChildList(list, obj_3.getAreaCode()); 
									  if (list_4 != null && !list_4.isEmpty()) {
										  warpList_4 = new ArrayList <PowerAreaWarp>();  
										  for (int z = 0; z < list_4.size(); z++) {
											  obj_4 = (PowerArea)list_4.get(z);
											  if(obj_4!=null){
												  list_5 = getChildList(list, obj_4.getAreaCode());
													if (list_5 != null && !list_5.isEmpty()) {
													  warpList_5 = new ArrayList <PowerAreaWarp>();
													  for (int s = 0; s < list_5.size(); s++) {
														obj_5 = (PowerArea)list_5.get(s);  
														if (obj_5 != null) {
														   list_6 = getChildList(list, obj_5.getAreaCode());
														   if (list_6 != null && !list_6.isEmpty()) {
															   warpList_6 = new ArrayList <PowerAreaWarp> ();
															   for (int d = 0; d < list_6.size(); d++) {
																 obj_6 = (PowerArea)list_6.get(d);  
																 if(obj_6!=null){
																    list_7 = getChildList(list, obj_6.getAreaCode()); 
																    if (list_7 != null && !list_7.isEmpty()) {
																      warpList_7 = new ArrayList <PowerAreaWarp>();	
																      for (int x = 0; x < list_7.size(); x++) {
																    	obj_7 = (PowerArea)list_7.get(x);
																    	if(obj_7!=null){
																			/********************************/
																		 warp_7 = new PowerAreaWarp();
																		 warp_7.setChecked(obj_7.isChecked());
																		 warp_7.setAreaCode(obj_7.getAreaCode());
																		 warp_7.setAreaName(obj_7.getAreaName());
																		 warp_7.setFullName(obj_7.getFullName());
																		 warp_7.setSupperCode(obj_7.getSupperCode());
																		 warpList_7.add(warp_7);
															    	}
															      }
															    }
															/********************************/
															 warp_6 = new PowerAreaWarp();
															 warp_6.setChecked(obj_6.isChecked());
															 warp_6.setAreaCode(obj_6.getAreaCode());
															 warp_6.setAreaName(obj_6.getAreaName());
															 warp_6.setFullName(obj_6.getFullName());
															 warp_6.setSupperCode(obj_6.getSupperCode());
															 warp_6.setChildren(warpList_7);
															 warpList_6.add(warp_6);
															 warpList_7=null;		  
															 }
														   }
													   }	
													/********************************/
													 warp_5 = new PowerAreaWarp();
													 warp_5.setChecked(obj_5.isChecked());
													 warp_5.setAreaCode(obj_5.getAreaCode());
													 warp_5.setAreaName(obj_5.getAreaName());
													 warp_5.setFullName(obj_5.getFullName());
													 warp_5.setSupperCode(obj_5.getSupperCode());
													 warp_5.setChildren(warpList_6);
													 warpList_5.add(warp_5);
													 warpList_6=null;	  	
													}
												  }
												}
											/********************************/
											 warp_4 = new PowerAreaWarp();
											 warp_4.setChecked(obj_4.isChecked());
											 warp_4.setAreaCode(obj_4.getAreaCode());
											 warp_4.setAreaName(obj_4.getAreaName());
											 warp_4.setFullName(obj_4.getFullName());
											 warp_4.setSupperCode(obj_4.getSupperCode());
											 warp_4.setChildren(warpList_5);
											 warpList_4.add(warp_4);
											 warpList_5=null;	  
										  }
									  }
								  }
								/********************************/
								 warp_3 = new PowerAreaWarp();
								 warp_3.setChecked(obj_3.isChecked());
								 warp_3.setAreaCode(obj_3.getAreaCode());
								 warp_3.setAreaName(obj_3.getAreaName());
								 warp_3.setFullName(obj_3.getFullName());
								 warp_3.setSupperCode(obj_3.getSupperCode());
								 warp_3.setChildren(warpList_4);
								 warpList_3.add(warp_3);
								 warpList_4=null;	  
							   }
						   }
						 }
						/********************************/
						 warp_2 = new PowerAreaWarp();
						 warp_2.setChecked(obj_2.isChecked());
						 warp_2.setAreaCode(obj_2.getAreaCode());
						 warp_2.setAreaName(obj_2.getAreaName());
						 warp_2.setFullName(obj_2.getFullName());
						 warp_2.setSupperCode(obj_2.getSupperCode());
						 warp_2.setChildren(warpList_3);
						 warpList_2.add(warp_2);
						 warpList_3=null;	  
						   
					   }
					 }
				 }
			  /********************************/
						warp_1 = new PowerAreaWarp();
						warp_1.setChecked(obj_1.isChecked());
						warp_1.setAreaCode(obj_1.getAreaCode());
						warp_1.setAreaName(obj_1.getAreaName());
						warp_1.setFullName(obj_1.getFullName());
						warp_1.setSupperCode(obj_1.getSupperCode());
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
   private static List<PowerArea> getChildList(List<PowerArea> list,String areaCode) {
       List<PowerArea> childPowerAreaList = new ArrayList<PowerArea>();
       Iterator<PowerArea> PowerArea = list.iterator();
       while (PowerArea.hasNext()) {
    	   PowerArea n = (PowerArea) PowerArea.next();
    	   if(n!=null){
               if (n.getSupperCode().equals(areaCode)) {
            	   childPowerAreaList.add(n);
               } 
    	   }
       }
       return childPowerAreaList;
   } 

}
