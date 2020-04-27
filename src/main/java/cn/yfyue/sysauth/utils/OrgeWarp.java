package cn.yfyue.sysauth.utils;



import cn.yfyue.sysauth.model.BjOrge;

import java.util.List;

public class OrgeWarp extends BjOrge {
	private List<OrgeWarp> children;

	public List<OrgeWarp> getChildren() {
		return children;
	}
	public void setChildren(List<OrgeWarp> children) {
		this.children = children;
	}
}
