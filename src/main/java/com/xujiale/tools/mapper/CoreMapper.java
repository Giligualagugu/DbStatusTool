package com.xujiale.tools.mapper;

import com.xujiale.tools.entity.DbStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xujiale 2020/7/10 17:07
 */
@Mapper
public interface CoreMapper {

    @Select("show global status")
    List<DbStatus> getGlobalStatusInfo();


    @Select("show global status where variable_name in ('connections','slow_queries','threads_connected','Uptime')")
    List<DbStatus> getSomeGlobalStatusInfo();

    @Select("show GLOBAL status where variable_name='Connections'")
    DbStatus getConnections();

    @Select("show GLOBAL status where variable_name='uptime'")
    DbStatus getUpTime();

    @Select("show GLOBAL status where variable_name='questions'")
    DbStatus getQuestions();

    @Select("show GLOBAL status where variable_name='COM_COMMIT'")
    DbStatus getCommitCounts();

    @Select("show GLOBAL status where variable_name='COM_ROLLBACK'")
    DbStatus getCommitRollBackCounts();

    @Select("show GLOBAL status where variable_name='bytes_sent'")
    DbStatus getSentFlow();

    @Select("show GLOBAL status where variable_name='Bytes_received'")
    DbStatus getBytesReceived();



}
