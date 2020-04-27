package cn.yfyue.sysauth.controller;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;

import cn.yfyue.sysauth.model.BjUserArea;
import cn.yfyue.sysauth.service.UserArea;
import cn.yfyue.sysauth.utils.PowerArea;
import cn.yfyue.sysauth.utils.PowerAreaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
public class UserAreaAjax{

	@Autowired
	private UserArea userArea;

	@RequestMapping("/getUserAreaTree")
	public String getUserAreaTree(@RequestBody String empowerUserId, ServletRequest request) {
		L.printRequest(null, request);
		List<BjUserArea> myAreaList=null;
		List<BjUserArea> empowerAreaList=null;
		List<PowerArea> powerAreaList=null;
		ReturnJson returnJson=new ReturnJson();
		boolean bfalg=false;
		empowerUserId= F.isNull(empowerUserId);
		try {
		   if(Auth.isAuth(request))	{
			 if(empowerUserId!=null){
				if(!empowerUserId.equals(Auth.getAuthUserId(request))){
			//取自己的权限
				 
				 myAreaList=userArea.getUserAreaList(Auth.getAuthUserId(request), Auth.getAuthUserLevel(request));
				 empowerAreaList=userArea.getUserAreaList(empowerUserId, null);
			//取自己按钮
			if(myAreaList!=null && !myAreaList.isEmpty()){
				powerAreaList= new ArrayList<PowerArea>();
			  for(int i=0;i<myAreaList.size();i++){
				  BjUserArea myArea=(BjUserArea)myAreaList.get(i);
				  if(myArea!=null){
					if(empowerAreaList!=null){
					  for(int j=0;j<empowerAreaList.size();j++){
						  BjUserArea empowerUserArea=(BjUserArea)empowerAreaList.get(j);
						  if(empowerUserArea!=null){
							  if(myArea.getAreaCode().equals(empowerUserArea.getAreaCode())){
								 bfalg=true;
							  }
						  }
					  }
					}
					PowerArea pa=new PowerArea();
					pa.setAreaCode(myArea.getAreaCode());
					pa.setAreaName(myArea.getAreaName());
					pa.setChecked(bfalg);
					pa.setFullName(myArea.getFullName());
					pa.setSupperCode(myArea.getSupperCode());
					powerAreaList.add(pa);
					bfalg=false;
				  }
			  }
			  if(powerAreaList!=null && !powerAreaList.isEmpty()){
				  returnJson.setState(0);
				  returnJson.setMessage("操作成功");    
				  returnJson.setObject(PowerAreaUtil.getPowerArea(powerAreaList));
			  }else{
				 returnJson.setState(-1);
				 returnJson.setMessage("对不起,您暂未获得可分配地区");   
			  }
			}else{
			   returnJson.setState(-1);
			   returnJson.setMessage("对不起,您暂未获得可分配地区,不能分配"); 
			}
			     }else{
						returnJson.setState(-1);
						returnJson.setMessage("对不起!不能给自己分配地区"); 
						   }
		     }else{
				returnJson.setState(-1);
				returnJson.setMessage("对不起!数据格式错误"); 
				   }
			 }else{
			  returnJson.setState(-1);
			  returnJson.setMessage("请登录,再操作"); 
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnJson.getJson();
	}	
	//添加分配
	@RequestMapping("/addUserArea")
	public String addUserArea(@RequestBody HashMap<String, Object> param, ServletRequest request) {
		String areaCodes = (String) param.get("areaCodes");
		String empowerUserId = (String) param.get("empowerUserId");
		BjUserArea empowerUserArea=null;
		ReturnJson returnJson=new ReturnJson();
		areaCodes= F.isNull(areaCodes);
		empowerUserId= F.isNull(empowerUserId);
		String areaArr[]=null;
		String areaCode="";
		try {
			if (Auth.isAuth(request)) {
	           if(areaCodes!=null && empowerUserId!=null){
	        	   if(!empowerUserId.equals(Auth.getAuthUserId(request))){
	        		   
	        	   }else{
	      				returnJson.setState(-1);
	       				returnJson.setMessage("对不起!不能给自己分配地区");     
	        	   }
	        	   areaArr=areaCodes.split(",");
	        	   if(areaArr!=null){
	        		   
					   if(userArea.delUserIdToUserArea(empowerUserId)==0){
						   L.p("清掉角色功能分配表数据为0");
					   }

	        		   for(int i=0;i<areaArr.length;i++){
	        			   areaCode= areaArr[i];
	        			   if(areaCode!=null && !areaCode.isEmpty()){
	        				   empowerUserArea=new BjUserArea();
	        				   empowerUserArea.setAreaCode(areaCode);
	        				   empowerUserArea.setUserId(F.strToLong(empowerUserId));
	        				   empowerUserArea.setRegMan(F.strToLong(Auth.getAuthUserId(request)));
	        					   if(userArea.addUserArea(empowerUserArea)==0){
	        						   L.p("分配地区失败["+empowerUserArea.toString()+"]");
	        					   }
	        				   }
	        			   }
	     			 returnJson.setState(0);
	       			 returnJson.setMessage("操作权限已全新分配");  
	        	   }else{
	      				returnJson.setState(-1);
	       				returnJson.setMessage("对不起!数据解析错误");     
	        	   }
	           }else{
				returnJson.setState(-1);
				returnJson.setMessage("对不起!数据格式错误");   
	           }
			} else {
				returnJson.setState(-1);
				returnJson.setMessage("请登录,再操作");
			}
		} catch (Exception e) {
			returnJson.setState(-1);
			returnJson.setMessage(e.toString());
			e.printStackTrace();
		}
		L.opLog(Thread.currentThread() .getStackTrace(), request, "分配地区", returnJson.getMessage());
		return returnJson.getJson();
	}	
}
