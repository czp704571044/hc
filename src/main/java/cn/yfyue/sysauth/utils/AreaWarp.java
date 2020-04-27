package cn.yfyue.sysauth.utils;



import cn.yfyue.sysauth.model.BjArea;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter

public class AreaWarp extends BjArea {
	private String id;
	private String title;

	private List<AreaWarp> children;

}
