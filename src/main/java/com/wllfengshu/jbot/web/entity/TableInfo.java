package com.wllfengshu.jbot.web.entity;

import java.util.List;

/**
 * 表实体类
 * @author wllfengshu
 */
public class TableInfo {

    /**
     * 表名
     */
    private String tableName = null;

    /**
     * 字段集合
     */
    private List<FieldInfo> fields = null;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<FieldInfo> getFields() {
        return fields;
    }

    public void setFields(List<FieldInfo> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "TableInfo{" +
                "tableName='" + tableName + '\'' +
                ", fields=" + fields +
                '}';
    }
}
