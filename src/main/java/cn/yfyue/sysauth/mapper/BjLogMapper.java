package cn.yfyue.sysauth.mapper;


import cn.yfyue.sysauth.model.BjLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BjLogMapper {
    //添加LOG
    int addLog(BjLog bjLog);

    //统计
    int logListCount(@Param("adminLevel") String adminLevel, @Param("query_key") String query_key,
                     @Param("loginName") String loginName, @Param("log_type") String log_type,
                     @Param("b_time") String b_time, @Param("e_time") String e_time);

    //日志列表
    List<BjLog> logList(@Param("adminLevel") String adminLevel, @Param("query_key") String query_key,
                        @Param("loginName") String loginName, @Param("log_type") String log_type,
                        @Param("b_time") String b_time, @Param("e_time") String e_time,
                        @Param("startRow") int startRow, @Param("rowNum") int rowNum);
}