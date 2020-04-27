package cn.yfyue.sysauth.controller;

public class CodeToStr {
//功能数据状态
public static String bjUserToState(String code){
	String reStr = "";
	try {
		if (code != null) {
			if (code.equals("0")) {
				reStr = "正常";
			} else if (code.equals("1")) {
				reStr = "禁止";
			}else {
				reStr = "";
			}
		} else {
			reStr = "";
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return reStr;
}
//功能数据叶子节点	
public static String bjUserToIsLeaf(String code){
	String reStr = "";
	try {
		if (code != null) {
			if (code.equals("0")) {
				reStr = "非叶子";
			} else if (code.equals("1")) {
				reStr = "叶子";
			}else {
				reStr = "";
			}
		} else {
			reStr = "";
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return reStr;
}

}
