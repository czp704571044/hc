package cn.yfyue.comm;

public class ReturnJson extends JsonUtil {
	private int state=-999;//0 正常 -1通用异常 -999程序EXCEPTION -998非JSON字符
	private String message="服务器忙，响应超时 ";
	private Object object;
	private int isRollback=0;//1 回滚   0不回滚
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getJson(){
		return toJson(this);
	}
	public int getIsRollback() {
		return isRollback;
	}
	public void setIsRollback(int isRollback) {
		this.isRollback = isRollback;
	}
}
