package cn.yfyue.sysauth.utils;

import cn.yfyue.comm.L;
import cn.yfyue.comm.SetSys;

import cn.yfyue.sysauth.model.BjRoleFun;
import cn.yfyue.sysauth.model.BjUserButton;
import cn.yfyue.sysauth.model.BjUserFunc;
import cn.yfyue.sysauth.model.BjUserRole;
import cn.yfyue.sysauth.service.RoleFun;
import cn.yfyue.sysauth.service.UserButton;
import cn.yfyue.sysauth.service.UserFun;
import cn.yfyue.sysauth.service.UserRole;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class TagBll {
//生成导航 1-只能分配自己的功能  0-按角色分配 2按组织职务直接分配
	
public static List<PowerFuncWarp> funcTagWarp(String userId,String userLevel){
	List<PowerFuncWarp> poerFuncWarpList=null;
	try{
		if(SetSys.funcAllot!=null && SetSys.funcAllot.equals("0")){
			poerFuncWarpList=roleFuncTagWarp(userId,userLevel);
		}else{
			poerFuncWarpList=userFuncTagWarp(userId,userLevel);
		}
		
	}catch(Exception e){e.printStackTrace();}
	return poerFuncWarpList;
}
	
	
public static List<PowerFuncWarp> userFuncTagWarp(String userId,String userLevel){
	UserFun userFunc=ContextLoader.getCurrentWebApplicationContext().getBean(UserFun.class);
	List<BjUserFunc> userFuncList=null;
	List<PowerFunc> powerFuncList=null;
	PowerFunc powerFunc=null;
	List<PowerFuncWarp> poerFuncWarpList=null;
	try{
		//取用户所有权限
		userFuncList=userFunc.getUserFuncList(userId, userLevel);
		if(userFuncList!=null && !userFuncList.isEmpty()){
			powerFuncList=new ArrayList <PowerFunc>(); 
		   for(int i=0;i<userFuncList.size();i++){
			   BjUserFunc bjUserFunc=(BjUserFunc)userFuncList.get(i);
			   if(bjUserFunc!=null){
				   powerFunc=new PowerFunc();
				   powerFunc.setFuncAlias(bjUserFunc.getFuncAlias());
				   powerFunc.setFuncId(bjUserFunc.getFuncId());
				   powerFunc.setFuncName(bjUserFunc.getFuncName());
				   powerFunc.setFuncUrl(bjUserFunc.getFuncUrl());
				   powerFunc.setIsLeaf(bjUserFunc.getIsLeaf());
				   powerFunc.setStyleImg(bjUserFunc.getStyleImg());
				   powerFunc.setSupperFuncId(bjUserFunc.getSupperFuncId());
				   powerFuncList.add(powerFunc);
			   }
		   }
		   poerFuncWarpList=PowerFuncUtil.getPowerFunc(powerFuncList);
		}
	}catch(Exception e){e.printStackTrace();}
	return poerFuncWarpList;
}

public static List<PowerFuncWarp> roleFuncTagWarp(String userId,String userLevel){
	RoleFun roleFun=ContextLoader.getCurrentWebApplicationContext().getBean(RoleFun.class);
	List<BjRoleFun> roleFuncList=null;
	List<PowerFunc> powerFuncList=null;
	PowerFunc powerFunc=null;
	List<PowerFuncWarp> poerFuncWarpList=null;
	try{
		if(userLevel!=null && userLevel.equals("0")){
			roleFuncList=roleFun.getAllFuncList();
		}else{
			L.p("fffffffffffffffff");
			List<String> roleIds=getUserRoleList(userId);
			if(roleIds!=null){
				for(int i=0;i<roleIds.size();i++){
					String s=(String)roleIds.get(i);
					L.p("sssssssssss"+s);
				}
			}
			roleFuncList=roleFun.getRoleFuncList(getUserRoleList(userId));
		}
		if(roleFuncList!=null && !roleFuncList.isEmpty()){
			powerFuncList=new ArrayList <PowerFunc>(); 
		   for(int i=0;i<roleFuncList.size();i++){
			   BjRoleFun bjRoleFun=(BjRoleFun)roleFuncList.get(i);
			   if(bjRoleFun!=null){
				   powerFunc=new PowerFunc();
				   powerFunc.setFuncAlias(bjRoleFun.getFuncAlias());
				   powerFunc.setFuncId(bjRoleFun.getFunId());
				   powerFunc.setFuncName(bjRoleFun.getFuncName());
				   powerFunc.setFuncUrl(bjRoleFun.getFuncUrl());
				   powerFunc.setIsLeaf(bjRoleFun.getIsLeaf());
				   powerFunc.setStyleImg(bjRoleFun.getStyleImg());
				   powerFunc.setSupperFuncId(bjRoleFun.getSupperFuncId());
				   powerFuncList.add(powerFunc);
			   }
		   }
		   poerFuncWarpList=PowerFuncUtil.getPowerFunc(powerFuncList);
		}else{
			L.p("aaaa");
		}
	}catch(Exception e){e.printStackTrace();}
	return poerFuncWarpList;
}
//角色字符串转成LIST
//public static List<String> userRoleStrToList(String roleCode){
//	 List<String> roleList=null;
//	 try{
//		if(roleCode!=null && !roleCode.isEmpty()){
//		  String roleArr[]=roleCode.split(",");
//	    if(roleArr!=null && roleArr.length>0){
//		 roleList=new ArrayList<String>(); 
//		for(int i=0;i<roleArr.length;i++){
//		   if(roleArr[i]!=null && !roleArr[i].isEmpty()){
//			 String userRoleCode=roleArr[i];
//			 if(userRoleCode!=null){
//				 roleList.add(userRoleCode);
//			 }
//		   }
//		}
//	  }
//	}
//}catch(Exception e){e.printStackTrace();}
//return roleList;
//}


public static String funcTagHtml(String userId,String userLevel){
	
	List<PowerFuncWarp> poerFuncWarpList=null;
	String tagHtml="";
	try{
		poerFuncWarpList=TagBll.funcTagWarp(userId, userLevel);
		if(poerFuncWarpList!=null && !poerFuncWarpList.isEmpty()){
		  for(int i=0;i<poerFuncWarpList.size();i++){
			//第一层开始
			  PowerFuncWarp warp_1=(PowerFuncWarp)poerFuncWarpList.get(i);
			  if(warp_1!=null){
				  tagHtml=tagHtml+startNode(warp_1.getFuncId(),warp_1.getStyleImg(),warp_1.getFuncName(),i,i);
				  if(warp_1.getChildren()!=null && !warp_1.getChildren().isEmpty()){
					  List<PowerFuncWarp> warpList_2=warp_1.getChildren();
					  for(int j=0;j<warpList_2.size();j++){
						  PowerFuncWarp warp_2=(PowerFuncWarp)warpList_2.get(j); 
						  if(warp_2!=null){
							 if(warp_2.getIsLeaf().equals("1")){
								 tagHtml=tagHtml+leafNode(warp_2.getSupperFuncId(),warp_2.getFuncId(),warp_2.getFuncUrl(),warp_2.getStyleImg(),warp_2.getFuncName());
							 }else{
								 tagHtml=tagHtml+plainNode(warp_2.getSupperFuncId(),warp_2.getFuncId(),warp_2.getStyleImg(),warp_2.getFuncName());
							 }
							 if(warp_2.getChildren()!=null && !warp_2.getChildren().isEmpty()){
								 List<PowerFuncWarp> warpList_3=warp_2.getChildren();
								 for(int k=0;k<warpList_3.size();k++){
									PowerFuncWarp warp_3=(PowerFuncWarp)warpList_3.get(k);
									if(warp_3!=null){
									   if(warp_3.getIsLeaf().equals("1")){
										  tagHtml=tagHtml+leafNode(warp_3.getSupperFuncId(),warp_3.getFuncId(),warp_3.getFuncUrl(),warp_3.getStyleImg(),warp_3.getFuncName());
										}else{
										  tagHtml=tagHtml+plainNode(warp_3.getSupperFuncId(),warp_3.getFuncId(),warp_3.getStyleImg(),warp_3.getFuncName());
										}
									   if(warp_3.getChildren()!=null && !warp_3.getChildren().isEmpty()){
										   List<PowerFuncWarp> warpList_4=warp_3.getChildren();
										   for(int x=0;x<warpList_4.size();x++){
											   PowerFuncWarp warp_4=(PowerFuncWarp)warpList_4.get(x);
											   if(warp_4!=null){
												   if(warp_4.getIsLeaf().equals("1")){
													   tagHtml=tagHtml+leafNode(warp_4.getSupperFuncId(),warp_4.getFuncId(),warp_4.getFuncUrl(),warp_4.getStyleImg(),warp_4.getFuncName());
													}else{
													   tagHtml=tagHtml+plainNode(warp_4.getSupperFuncId(),warp_4.getFuncId(),warp_4.getStyleImg(),warp_4.getFuncName());
													} 
												   if(warp_4.getChildren()!=null && !warp_4.getChildren().isEmpty()){ 
													   List<PowerFuncWarp> warpList_5=warp_4.getChildren();
													   for(int s=0;s<warpList_5.size();s++){
														   PowerFuncWarp warp_5=(PowerFuncWarp)warpList_5.get(s);
														   if(warp_5!=null){
															  
															  if(warp_5.getIsLeaf().equals("1")){
																 tagHtml=tagHtml+leafNode(warp_5.getSupperFuncId(),warp_5.getFuncId(),warp_5.getFuncUrl(),warp_5.getStyleImg(),warp_5.getFuncName());
															   }else{
																 tagHtml=tagHtml+plainNode(warp_5.getSupperFuncId(),warp_5.getFuncId(),warp_5.getStyleImg(),warp_5.getFuncName());
															  }  
															  if(warp_5.getChildren()!=null && !warp_5.getChildren().isEmpty()){ 
																  List<PowerFuncWarp> warpList_6=warp_5.getChildren();
																  for(int d=0;d<warpList_6.size();d++){ 
																	  PowerFuncWarp warp_6=(PowerFuncWarp)warpList_6.get(d);
																	  if(warp_6!=null){
																		  if(warp_6.getIsLeaf().equals("1")){
																			tagHtml=tagHtml+leafNode(warp_6.getSupperFuncId(),warp_6.getFuncId(),warp_6.getFuncUrl(),warp_6.getStyleImg(),warp_6.getFuncName());
																		}else{
																			tagHtml=tagHtml+plainNode(warp_6.getSupperFuncId(),warp_6.getFuncId(),warp_6.getStyleImg(),warp_6.getFuncName());
																		} 
																		if(warp_6.getChildren()!=null && !warp_6.getChildren().isEmpty()){  
																			List<PowerFuncWarp> warpList_7=warp_6.getChildren();
																			for(int l=0;l<warpList_7.size();l++){
																				PowerFuncWarp warp_7=(PowerFuncWarp)warpList_7.get(l);
																				if(warp_7!=null){
																					if(warp_7.getIsLeaf().equals("1")){
																						tagHtml=tagHtml+leafNode(warp_7.getSupperFuncId(),warp_7.getFuncId(),warp_7.getFuncUrl(),warp_7.getStyleImg(),warp_7.getFuncName());
																				    }else{
																						tagHtml=tagHtml+plainNode(warp_7.getSupperFuncId(),warp_7.getFuncId(),warp_7.getStyleImg(),warp_7.getFuncName());
																					} 
																				}
																			}
																		}
																		  
																	  }
				
																  }
															  }
														   }
													   }
												   }
											   }
										   }
									   }
									   
									}
								 }
							 }
						  }
					  }
				  }
				  tagHtml=tagHtml+endNode();  
			  }
			//最后结束  
		  }
		}
	}catch(Exception e){e.printStackTrace();}
	return tagHtml;
}

public static String startNode(String funcId,String icom,String noteName,int activeNum,int expandNum){
	String htmlStr="";
	String icomHtml="";
	String expandHtml="";
	String activeHtml="";
	try{
		if(icom!=null && !icom.isEmpty()){
			icomHtml=icom;
		}else{
			icomHtml="table";
		}

	
		if(expandNum==100){
			expandHtml="false";
		}else{
			expandHtml="true";
		}
		
		if(activeNum==1){
			activeHtml="class=\"active\"";	
		}
		//不显示头功能导行
		//htmlStr=htmlStr+"<li  style=\"display:none\" "+activeHtml+"><a href=\"javascript:;\" data-toggle=\"slidebar\"><i class=\"fa fa-check-square-o\"></i>"+noteName+"</a>";
		//显示头功能导行
		htmlStr=htmlStr+"<li "+activeHtml+"><a href=\"javascript:;\" data-toggle=\"slidebar\"><i class=\"fa fa-check-square-o\"></i>"+noteName+"</a>";
		htmlStr=htmlStr+"<div class=\"items hide\" data-noinit=\"true\">";
		htmlStr=htmlStr+"<ul id=\"bjui-hnav-tree"+funcId+"\" class=\"ztree ztree_main\" data-toggle=\"ztree\" data-on-click=\"MainMenuClick\" data-expand-all=\""+expandHtml+"\" data-faicon=\""+icomHtml+"\">";
	}catch(Exception e){e.printStackTrace();}
	return htmlStr;
}

public static String plainNode(String supperFuncId,String funcId,String icom,String noteName){
	String htmlStr="";
	String icomHtml="";
	try{
		if(icom!=null && !icom.isEmpty()){
			icomHtml=icom;
		}else{
			icomHtml="forward";
		}
		htmlStr=htmlStr+"<li data-id=\""+funcId+"\" data-pid=\""+supperFuncId+"\" data-faicon=\""+icomHtml+"\" data-faicon-close=\"folder-o\">"+noteName+"</li>";
	}catch(Exception e){e.printStackTrace();}
	return htmlStr;
}

public static String leafNode(String supperFuncId,String funcId,String url,String icom,String noteName){
	String htmlStr="";
	String icomHtml="";
	try{
		if(icom!=null && !icom.isEmpty()){
			icomHtml=icom;
		}else{
			icomHtml="list-alt";
		}
		htmlStr=htmlStr+"<li data-id=\""+funcId+"\" data-pid=\""+supperFuncId+"\" data-url=\""+url+"?resourceId="+funcId+"\" data-tabid=\""+funcId+"\" data-faicon=\""+icomHtml+"\">"+noteName+"</li>";
	}catch(Exception e){e.printStackTrace();}
	return htmlStr;
}

public static String endNode(){
	String htmlStr="";
	try{
		htmlStr=htmlStr+"</ul></div></li>";
	}catch(Exception e){e.printStackTrace();}
	return htmlStr;
}
//生成按钮
public static String getButtonHtml(String userId,String funcId,String userLevel){
	   String  buttonHtml="";	
	   UserButton userButton=ContextLoader.getCurrentWebApplicationContext().getBean(UserButton.class);
	   List<BjUserButton> userButtonList=null;
	   try{
		   userButtonList=userButton.getUserIdAndFuncIdButtonList(userId, funcId, userLevel);
		   if(userButtonList!=null && !userButtonList.isEmpty()){
			  for(int i=0;i<userButtonList.size();i++){
				  BjUserButton bjUserButton=(BjUserButton)userButtonList.get(i);
				  buttonHtml=buttonHtml+bjUserButton.getButtonHtml();
			  }
		   }
	   }catch(Exception e){e.printStackTrace();}
	   return buttonHtml;
	}
//验证是否有权使用
public static boolean isPage(String userId,String funcId,String userLevel){
   boolean bflag=false;	
   UserFun userFunc=ContextLoader.getCurrentWebApplicationContext().getBean(UserFun.class);	
   RoleFun roleFun=ContextLoader.getCurrentWebApplicationContext().getBean(RoleFun.class);
   //L.p("isPage userId["+userId+"] funcId["+funcId+"] userLevel["+userLevel+"]");
   try{
	   if(userLevel!=null && userLevel.equals("0")){
		   bflag=true;
	   }else{
			if(SetSys.funcAllot!=null && SetSys.funcAllot.equals("0")){
				if(roleFun.isRoleFunc(getUserRoleList(userId), funcId)>0){
					 bflag=true; 
				}
			}else{
			   if(userFunc.isUserFunc(userId, funcId)>0){
				   bflag=true; 
			   }
			}
	   }
   }catch(Exception e){e.printStackTrace();}
   return bflag;
}
//按用户ID取用户所有角色ID并转化为LIST<STRING>
public static List<String> getUserRoleList(String userId){
	List<String> roleList=null;
	UserRole userRole=ContextLoader.getCurrentWebApplicationContext().getBean(UserRole.class);
	try{
	  List<BjUserRole> userRoleList=userRole.getUserRoleList(userId);
	  if(userRoleList!=null && !userRoleList.isEmpty()){
		  roleList=new ArrayList();
		for(int i=0;i<userRoleList.size();i++){
			BjUserRole bjUserRole=(BjUserRole)userRoleList.get(i);
			if(bjUserRole!=null){
			  if(bjUserRole.getRoleId()!=null){
				roleList.add(""+bjUserRole.getRoleId());
			  }
			}
		}
	  }
	}catch(Exception e){e.printStackTrace();}
	return roleList;
}
public static void main(String areg[])throws Exception
{
	//System.out.println(funcTagHtml("2","1"));
}
}
