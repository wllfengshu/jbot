package com.wllfengshu.common.entity;

public class FieldInfo {
    /**
     * 字段名
     */
    private String fieldName = null;
    /**
     * 字段类型
     */
    private String fieldType = null;

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

    @Override
    public String toString() {
        return "FieldInfo{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldType='" + fieldType + '\'' +
                '}';
    }
}
