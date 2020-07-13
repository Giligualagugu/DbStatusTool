package com.xujiale.tools.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xujiale 2020/7/11 18:20
 */
@Data
public class Foobar implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String demo;

    private String foobar;
}
