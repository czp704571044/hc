package cn.yfyue.comm;


import cn.yfyue.sysauth.model.BjInitData;
import cn.yfyue.sysauth.service.Init;
import org.springframework.web.context.ContextLoader;

import java.util.ArrayList;
import java.util.List;

public class SetSys {
public static String imgUrl="http://47.95.4.221:7070/hongboImg/fileUpload";	
public static String kindEditorAttrUrl="http://47.95.4.221:7070/hongboImg/fileUpload?busiFolder=website/kindEditor";	
public static String platName="电子商务管理平台";//平台名字
public static String platShortName="电子商务管理平台";//平台名字
public static String framePlatName="电子商务管理平台";
public static String funcAllot="0";//1-只能分配自己的功能  0-按角色分配
public static String icp="";//ICP备案号
public static String linkTel="";//公司电话
public static String companyName="湖南冠文科技服务有限公司";//公司名称
public static String technicalSupport="技术支持：<a target=\"_blank\" href=\"http://www.yfyue.cn\">湖南云峰悦</a>";//技术支持 A标签
public static int passwdErrNum=5;//平台密码输错次数
//JSP加载系统数据字典
public static String loadInitData(){
	String initJson=null;
	List<BjInitData> dataList = null;
	ReturnJson returnJson=null;	
	try{
		dataList=loadCatchInitData(null);
		if(dataList!=null){
			returnJson=new ReturnJson();
			returnJson.setMessage("数据加载成功");
			returnJson.setObject(dataList);
			returnJson.setState(0);
			initJson=returnJson.getJson();
		}
	}catch(Exception e){e.printStackTrace();}
	return initJson;
}
//加载初始化数据 从缓存读入不联数据库
public static List<BjInitData> loadCatchInitData(String typeCode) {
	List<BjInitData> initDataList=null;
	try{
	   Init init=ContextLoader.getCurrentWebApplicationContext().getBean(Init.class);
	   if(typeCode!=null){
		 List<BjInitData> allInitDataList=init.catchLoadInitData(); 
		 if(allInitDataList!=null && !allInitDataList.isEmpty()){
			 initDataList=new ArrayList<BjInitData>();
			 for(int i=0;i<allInitDataList.size();i++){
			    BjInitData bjInitData=(BjInitData)allInitDataList.get(i);
			    if(bjInitData!=null && bjInitData.getTypeCode()!=null){
			    	if(bjInitData.getTypeCode().equals(typeCode)){
			    		initDataList.add(bjInitData);	
			    	}
			    }
			 }			 
		 }
	   }else{
		   initDataList=init.catchLoadInitData(); 
	   }
	}catch(Exception e){e.printStackTrace();}
	return initDataList;
}

//数据字典初始化编码转名称
public static String initCodeToName(String initCode) {
	String codeToNameStr="";
	try{
	   if(initCode!=null && !initCode.isEmpty()){
		 Init init=ContextLoader.getCurrentWebApplicationContext().getBean(Init.class);	
		 List<BjInitData> allInitDataList=init.catchLoadInitData(); 
		 if(allInitDataList!=null && !allInitDataList.isEmpty()){
			 for(int i=0;i<allInitDataList.size();i++){
			    BjInitData bjInitData=(BjInitData)allInitDataList.get(i);
			    if(bjInitData!=null && bjInitData.getDataCode()!=null){
			    	if(bjInitData.getDataCode().equals(initCode)){
			    		codeToNameStr=bjInitData.getDataName();	
			    		break;
			    	}
			    }
			 }			 
		 }
	   }else{
		   codeToNameStr=initCode; 
	   }
	}catch(Exception e){e.printStackTrace();}
	return codeToNameStr;
}
}
