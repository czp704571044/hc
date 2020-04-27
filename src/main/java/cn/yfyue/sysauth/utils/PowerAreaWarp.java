package cn.yfyue.sysauth.utils;

import java.util.List;

public class PowerAreaWarp extends PowerArea{
	private List<PowerAreaWarp> children;


	public List<PowerAreaWarp> getChildren() {
		return children;
	}

	public void setChildren(List<PowerAreaWarp> children) {
		this.children = children;
	}
}
