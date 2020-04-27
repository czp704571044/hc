package cn.yfyue.sysauth.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class BjOrgeLayUi implements Serializable {

    private String title;
    private Integer id;
    private Boolean checked = false;
    private List<BjOrgeLayUi> children;
}
