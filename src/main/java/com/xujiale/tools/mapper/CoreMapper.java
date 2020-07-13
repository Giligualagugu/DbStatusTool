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
}
