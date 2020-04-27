package cn.yfyue.sysauth.utils;

import cn.yfyue.comm.JsonUtil;
import cn.yfyue.comm.L;
import cn.yfyue.sysauth.model.BjArea;
import cn.yfyue.sysauth.service.Area;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AreaUtil{
//找当前结点以下	
   public static List<AreaWarp> getAreaChildNodes(List<BjArea> list, String code) {
	   List<BjArea> plist=null;
	   List<BjArea> clist=null;
	   List<BjArea> xlist=null;
	   List<BjArea> zlist=null;
	   List<BjArea> slist=null;
	   List<BjArea> dlist=null;
	   BjArea pArea=null;
	   BjArea cArea=null;
	   BjArea xArea=null;
	   BjArea zArea=null;
	   BjArea sArea=null;
	   BjArea dArea=null;
	   AreaWarp pWarp=null;
	   AreaWarp cWarp=null;
	   AreaWarp xWarp=null;
	   AreaWarp zWarp=null;
	   AreaWarp sWarp=null;
	   AreaWarp dWarp=null;
	   List<AreaWarp> xWarpList =null;
	   List<AreaWarp> cWarpList = null;
	   List<AreaWarp> zWarpList =null;
	   List<AreaWarp> sWarpList = null;
	   List<AreaWarp> dWarpList =null;
	   List<AreaWarp> returnList = new ArrayList<AreaWarp>();
	   
	   if(list!=null && !list.isEmpty()){
		   if(code==null || code.isEmpty()){
			   code="0";
	   }
		   
	  //省子节点
	   plist=getChildList(list,code);
	   if(plist!=null && !plist.isEmpty()){
		   for(int i=0;i<plist.size();i++){
			   pArea=(BjArea)plist.get(i);
			   if(pArea!=null){
				   //取所有城市
				   clist=getChildList(list,pArea.getAreaCode());
				   if(clist!=null && !clist.isEmpty()){
					   cWarpList = new ArrayList<AreaWarp>();
					   for(int j=0;j<clist.size();j++){
						   cArea=(BjArea)clist.get(j);
						   if(cArea!=null){
							   xlist= getChildList(list,cArea.getAreaCode());
							   if(xlist!=null && !xlist.isEmpty()){
								   xWarpList = new ArrayList<AreaWarp>();
								   for(int k=0;k<xlist.size();k++){
								   xArea=(BjArea)xlist.get(k);
								   if(xArea!=null){
								   //取镇列表
									 zlist= getChildList(list,xArea.getAreaCode()); 
									 if(zlist!=null && !zlist.isEmpty()){
									    zWarpList = new ArrayList<AreaWarp>(); 
									    for(int z=0;z<zlist.size();z++){
									    zArea=(BjArea)zlist.get(z);   
									    if(zArea!=null){
									      //取村列表	
									      slist= getChildList(list,zArea.getAreaCode()); 	
									      if(slist!=null && !slist.isEmpty()){
									    	 sWarpList = new ArrayList<AreaWarp>();
									    	 for(int s=0;s<slist.size();s++){
									    	   sArea=(BjArea)slist.get(s);   
									    	   if(sArea!=null){
									    		 //取队
									    		 dlist= getChildList(list,sArea.getAreaCode());  
									    		 if(dlist!=null && !dlist.isEmpty()){
									    			 dWarpList = new ArrayList<AreaWarp>();
									    			 for(int d=0;d<dlist.size();d++){
												    	dArea=(BjArea)dlist.get(d);
												    	//添加队
												    	if(dArea!=null){
														    dWarp=new AreaWarp();
															dWarp.setTitle(dArea.getAreaName());
															dWarp.setId(dArea.getAreaCode());
														    dWarpList.add(dWarp);
												    	}
									    			 }
									    		 }
									    		//添加村
												    sWarp=new AreaWarp();
									    		 	sWarp.setId(sArea.getAreaCode());
												 	sWarp.setTitle(sArea.getAreaName());
											   		sWarp.setChildren(dWarpList);
											   		sWarpList.add(sWarp);
											   		dWarpList=null;
									    	   }
									    	 }
									      }
									     //添加镇	
										    zWarp=new AreaWarp();
											zWarp.setTitle(zArea.getAreaName());
											zWarp.setId(zArea.getAreaCode());
										    zWarp.setChildren(sWarpList);
										    zWarpList.add(zWarp);
										    sWarpList=null;
									     }
									    }
									 }
									 //添加县  
									   xWarp=new AreaWarp();
									   xWarp.setId(xArea.getAreaCode());
									   xWarp.setTitle(xArea.getAreaName());
								   	   xWarp.setChildren(zWarpList);
								       xWarpList.add(xWarp);
								       zWarpList=null;
								   }
								  }  
							   }
							   //市
							   cWarp=new AreaWarp();
							   cWarp.setTitle(cArea.getAreaName());
							   cWarp.setId(cArea.getAreaCode());
							   cWarp.setChildren(xWarpList);
							   cWarpList.add(cWarp); 
							   xWarpList=null;
						   }
					   }
				   }
				   //添加省
					   pWarp=new AreaWarp(); 
					   pWarp.setId(pArea.getAreaCode());
				       pWarp.setTitle(pArea.getAreaName());
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
   private static List<BjArea> getChildList(List<BjArea> list,String code) {
       List<BjArea> childAreaList = new ArrayList<BjArea>();
       Iterator<BjArea> it = list.iterator();
       while (it.hasNext()) {
    	   BjArea n = (BjArea) it.next();
    	   if(n!=null){
               if (n.getSupperCode().equals(code)) {
            	   childAreaList.add(n);
               } 
    	   }
       }
       return childAreaList;
   }   



  //运行
   public static void main(String arge[])throws Exception{
	   Area area=new Area();
	   List<BjArea> arealist=area.getAreaList(null);
	   AreaUtil areaUtil=new AreaUtil();
	   JsonUtil j=new JsonUtil();
       L.p("-----");
	   List<AreaWarp> aaList=areaUtil.getAreaChildNodes(arealist,"0");
	   L.p("-----");
	   String aa=j.toJson(aaList);
	   L.p("-----");
	   System.out.println(aa);
	   L.p("-----");
   }
}
