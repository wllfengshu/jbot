package com.wllfengshu.jbot.web.entity;

/**
 * 表字段实体类
 * @author wllfengshu
 */
public class FieldInfo {

    /**
     * 字段名称（eg:username）
     */
    private String fieldName = null;

    /**
     * 字段类型（eg:varchar）
     */
    private String fieldType = null;

    /**
     * 列注释（eg:用户名）
     */
    private String columnComment = null;

    /**
     * 是否允许为空（eg:NO）
     */
    private String isNullable = null;

    /**
     * 列类型（eg:varchar(32)）
     */
    private String columnType = null;

    /**
     * 列约束（eg:PRI主键约束;UNI唯一约束;MUL可以重复）
     */
    private String columnKey = null;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", columnType='" + columnType + '\'' +
                ", columnKey='" + columnKey + '\'' +
                '}';
    }
}
