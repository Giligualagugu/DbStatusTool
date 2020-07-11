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

}
