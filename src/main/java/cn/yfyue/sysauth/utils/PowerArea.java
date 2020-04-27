package cn.yfyue.sysauth.utils;

public class PowerArea {
	private String areaCode;
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getSupperCode() {
		return supperCode;
	}
	public void setSupperCode(String supperCode) {
		this.supperCode = supperCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	private String supperCode;
	private String areaName;
	private String fullName;
	private boolean checked = false;
}
