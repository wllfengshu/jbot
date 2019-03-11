package com.wllfengshu.common.entity;

/**
 * 表字段实体类
 * @author wllfengshu
 */
public class FieldInfo {

    /**
     * 字段名
     */
    private String fieldName = null;

    /**
     * 字段类型
     */
    private String fieldType = null;

    /**
     * 字段注解
     */
    private String fieldComment = null;


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

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                ", fieldComment='" + fieldComment + '\'' +
                '}';
    }
}
