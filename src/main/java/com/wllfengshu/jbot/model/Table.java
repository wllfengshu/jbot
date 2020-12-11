package com.wllfengshu.jbot.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表实体类
 *
 * @author wllfengshu
 */
@Data
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段集合
     */
    private List<Field> fields;

}
