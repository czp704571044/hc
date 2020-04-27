package cn.yfyue.sysauth.utils;



import cn.yfyue.sysauth.model.BjFunc;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class FuncWarp extends BjFunc implements Serializable {
	private String id;
	private List<FuncWarp> children;

	private String title;

	private String href;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FuncWarp> getChildren() {
		return children;
	}

	public void setChildren(List<FuncWarp> children) {
		this.children = children;
	}

}
