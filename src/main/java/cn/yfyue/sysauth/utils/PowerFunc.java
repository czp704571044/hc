package cn.yfyue.sysauth.utils;

public class PowerFunc {
	private String funcId;
	private String funcName;
	private String funcUrl;
	private String supperFuncId;
	private String funcAlias;
	private String styleImg;
	private String isLeaf;
	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	private boolean checked = false;


	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public String getSupperFuncId() {
		return supperFuncId;
	}

	public void setSupperFuncId(String supperFuncId) {
		this.supperFuncId = supperFuncId;
	}

	public String getFuncAlias() {
		return funcAlias;
	}

	public void setFuncAlias(String funcAlias) {
		this.funcAlias = funcAlias;
	}

	public String getStyleImg() {
		return styleImg;
	}

	public void setStyleImg(String styleImg) {
		this.styleImg = styleImg;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
