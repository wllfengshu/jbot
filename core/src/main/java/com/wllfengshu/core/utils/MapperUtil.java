package com.wllfengshu.core.utils;

import com.wllfengshu.common.entity.FieldInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 生成mapper文件
 */
public class MapperUtil {

    public static String genMapper(String daoClassName,String entityClassName,TableInfo tableInfo){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(daoClassName));
        sb.append(genResult(entityClassName,tableInfo));
        sb.append(genInsert(entityClassName,tableInfo));
        sb.append(genDelete(tableInfo.getTableName()));
        sb.append(genUpdate(entityClassName,tableInfo));
        sb.append(genSelect(entityClassName,tableInfo.getTableName()));
        sb.append(genSelectList(tableInfo));
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static String genHead(String daoClassName){
        StringBuffer sb=new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\r\n");
        sb.append("<mapper namespace=\""+daoClassName+"\">\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成尾
     * @return
     */
    private static String genTail(){
        return "</mapper>\r\n";
    }

    /**
     * 对象结果映射
     * @return
     */
    private static String genResult(String entityClassName,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("\r\t<resultMap type=\""+entityClassName+"\" id=\"resultMap\">\r\n");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("\r\t\r\t<result property=\""+StringUtil.underlineToHump(field.getFieldName())+"\" column=\""+field.getFieldName()+"\"></result>\r\n");
        }
        sb.append("\r\t</resultMap>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成插入语句
     * @return
     */
    private static String genInsert(String entityClassName,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("\r\t<insert id=\"insert\" parameterType=\""+entityClassName+"\">\r\n");
        sb.append("\r\t\r\tINSERT INTO "+tableInfo.getTableName()+"(\r\n\r\t\r\t\r\t");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append(field.getFieldName()+", ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\r\n\r\t\r\t)VALUES(\r\n\r\t\r\t\r\t");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("#{"+StringUtil.underlineToHump(field.getFieldName())+"}, ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\r\n\r\t\r\t)\r\n");
        sb.append("\r\t</insert>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("\r\t<delete id=\"delete\" parameterType=\"java.lang.Integer\">\r\n");
        sb.append("\r\t\r\tDELETE FROM "+tableName+"\r\n");
        sb.append("\r\t\r\tWHERE id = #{id}\r\n");
        sb.append("\r\t</delete>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String entityClassName,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("\r\t<update id=\"update\" parameterType=\""+entityClassName+"\">\r\n");
        sb.append("\r\t\r\tUPDATE "+tableInfo.getTableName()+" SET\r\n\r\t\r\t\r\t");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append(field.getFieldName()+" = #{"+StringUtil.underlineToHump(field.getFieldName())+"}, ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\r\n\r\t\r\tWHERE id = #{id}\r\n");
        sb.append("\r\t</update>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(String entityClassName,String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("\r\t<select id=\"select\" parameterType=\"java.lang.Integer\" resultType=\""+entityClassName+"\">\r\n");
        sb.append("\r\t\r\tSELECT * FROM "+tableName+"\r\n");
        sb.append("\r\t\r\tWHERE id = #{id}\r\n");
        sb.append("\r\t</select>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("\r\t<select id=\"selects\" parameterType=\"java.util.Map\" resultMap=\"resultMap\">\r\n");
        sb.append("\r\t\r\tSELECT * \r\n");
        sb.append("\r\t\r\tFROM "+tableInfo.getTableName()+" \r\n");
        sb.append("\r\t\r\t<where> 1=1 \r\n");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("\r\t\r\t\r\t<if test=\""+field.getFieldName()+"!=null and "+field.getFieldName()+"!=''\">\r\n");
            sb.append("\r\t\r\t\r\t\r\tAND "+field.getFieldName()+" = #{"+StringUtil.underlineToHump(field.getFieldName())+"}\r\n");
            sb.append("\r\t\r\t\r\t</if>\r\n");
        }
        sb.append("\r\t\r\t\r\t</where>\r\n\r\t</select>\r\n\r\n");
        return sb.toString();
    }
}

