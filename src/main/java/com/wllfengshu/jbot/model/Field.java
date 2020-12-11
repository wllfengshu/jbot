package com.wllfengshu.jbot.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 表字段实体类
 *
 * @author wllfengshu
 */
@Data
public class Field implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字段名称（eg:username）
     */
    private String fieldName;

    /**
     * 字段类型（eg:varchar）
     */
    private String fieldType;

    /**
     * 列注释（eg:用户名）
     */
    private String columnComment;

    /**
     * 是否允许为空（eg:NO）
     */
    private String nullable;

    /**
     * 列类型（eg:varchar(32)）
     */
    private String columnType;

    /**
     * 列约束（eg:PRI主键约束;UNI唯一约束;MUL可以重复）
     */
    private String columnKey;

}
