package cn.yfyue.sysauth.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class TApiLog {

    private Long logId;


    private String appId;


    private String interfaceCode;


    private String reqIp;


    private String reqUrl;


    private String reqJson;


    private Long timeNum;


    private String respJson;


    private Integer respState;


    private String respMsg;


    private String extKey;


    private Date logTime;

}