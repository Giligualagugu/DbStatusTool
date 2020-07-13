package com.xujiale.tools.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xujiale.tools.entity.Foobar;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoobarMapper extends BaseMapper<Foobar> {
}
