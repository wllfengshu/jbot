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
        sb.append("\t<resultMap type=\""+entityClassName+"\" id=\"resultMap\">\r\n");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("\t\t<result property=\""+StringUtil.underlineToHump(field.getFieldName())+"\" column=\""+field.getFieldName()+"\"></result>\r\n");
        }
        sb.append("\t</resultMap>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成插入语句
     * @return
     */
    private static String genInsert(String entityClassName,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("\t<INSERT id=\"insert\" parameterType=\""+entityClassName+"\">\r\n");
        sb.append("\t\tINSERT INTO "+tableInfo.getTableName()+"(\r\n\t\t\t");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append(field.getFieldName()+", ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\r\n\t\t)VALUES(\r\n\t\t\t");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("#{"+StringUtil.underlineToHump(field.getFieldName())+"}, ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\r\n\t\t)\r\n");
        sb.append("\t</insert>\r\n\r\n");
        return sb.toString();
    }

    /**
    * 生成删除语句
    * @return
    */
    private static String genDelete(String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("\t<delete id=\"delete\" parameterType=\"java.lang.Integer\">\r\n");
        sb.append("\t\tDELETE FROM "+tableName+"\r\n");
        sb.append("\t\tWHERE id = #{id}\r\n");
        sb.append("\t</delete>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String entityClassName,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("\t<update id=\"update\" parameterType=\""+entityClassName+"\">\r\n");
        sb.append("\t\tUPDATE "+tableInfo.getTableName()+" SET\r\n\t\t\t");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append(field.getFieldName()+" = #{"+StringUtil.underlineToHump(field.getFieldName())+"}, ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\r\n\t\tWHERE id = #{id}\r\n");
        sb.append("\t</update>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(String entityClassName,String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("\t<select id=\"select\" parameterType=\"java.lang.Integer\" resultType=\""+entityClassName+"\">\r\n");
        sb.append("\t\tSELECT * FROM "+tableName+"\r\n");
        sb.append("\t\tWHERE id = #{id}\r\n");
        sb.append("\t</select>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("\t<select id=\"selects\" parameterType=\"java.util.Map\">\r\n");
        sb.append("\t\tSELECT * \r\n");
        sb.append("\t\tFROM "+tableInfo.getTableName()+" \r\n");
        sb.append("\t\t<where> 1=1 \r\n");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("\t\t\t<if test=\""+field.getFieldName()+"!=null and "+field.getFieldName()+"!=''\">\r\n");
            sb.append("\t\t\t\tAND "+field.getFieldName()+" = #{"+StringUtil.underlineToHump(field.getFieldName())+"}\r\n");
            sb.append("\t\t\t</if>\r\n");
        }
        sb.append("\t\t\t</where>\r\n\t</select>\r\n\r\n");
        return sb.toString();
    }
}

