package com.wllfengshu.common.utils;

import com.wllfengshu.common.entity.FieldInfo;
import com.wllfengshu.common.entity.TableInfo;

public class MapperUtil {

    public static String genMapper(String projectName,String packageName,TableInfo tableInfo){
        String tableNameFUDTU=StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(StringUtil.delTUnderline(tableInfo.getTableName())));
        String daoClassName=packageName+"."+projectName+".dao."+tableNameFUDTU+"Dao";
        String entityClassName=packageName+"."+projectName+".entity."+tableNameFUDTU;
        StringBuffer mapper=new StringBuffer();
        mapper.append(genXmlHead(daoClassName));
        mapper.append(genResultMapper(entityClassName,tableInfo));
        mapper.append(genInsertMapper(tableNameFUDTU,entityClassName,tableInfo));
        mapper.append(genDeleteMapper(tableNameFUDTU,tableInfo.getTableName()));
        mapper.append(genUpdateMapper(tableNameFUDTU,entityClassName,tableInfo));
        mapper.append(genSelectMapper(tableNameFUDTU,entityClassName,tableInfo.getTableName()));
        mapper.append(genSelectListMapper(tableNameFUDTU,tableInfo));
        mapper.append(genXmlTail());
        return mapper.toString();
    }

    /**
     * 生成xml头
     * @param daoClassName
     * @return
     */
    private static String genXmlHead(String daoClassName){
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n" +
                "<mapper namespace=\""+daoClassName+"\">\n\n";
    }

    /**
     * 生成xml尾
     * @return
     */
    private static String genXmlTail(){
        return "</mapper>\n";
    }

    /**
     * 对象结果映射
     * @param entityClassName
     * @param tableInfo
     * @return
     */
    private static String genResultMapper(String entityClassName,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("    <resultMap type=\""+entityClassName+"\" id=\"resultMap\">\n");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("        <result property=\""+StringUtil.underlineToHump(field.getFieldName())+"\" column=\""+field.getFieldName()+"\"></result>\n");
        }
        sb.append("    </resultMap>\n\n");
        return sb.toString();
    }

    /**
     * 生成插入语句
     * @param tableNameFUDTU
     * @param entityClassName
     * @param tableInfo
     * @return
     */
    private static String genInsertMapper(String tableNameFUDTU,String entityClassName,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("    <INSERT id=\"insert"+tableNameFUDTU+"\" parameterType=\""+entityClassName+"\">\n");
        sb.append("        INSERT INTO "+tableInfo.getTableName()+"(\n            ");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append(field.getFieldName()+", ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\n        )VALUES(\n            ");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("#{"+StringUtil.underlineToHump(field.getFieldName())+"}, ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\n        )\n");
        sb.append("    </insert>\n\n");
        return sb.toString();
    }

    /**
    * 生成删除语句
    * * @param tableNameFUDTU
    * @param tableName
    * @return
    */
    private static String genDeleteMapper(String tableNameFUDTU,String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("    <delete id=\"delete"+tableNameFUDTU+"\" parameterType=\"java.lang.Integer\">\n");
        sb.append("        DELETE FROM "+tableName+"\n");
        sb.append("        WHERE id = #{id}\n");
        sb.append("    </delete>\n\n");
        return sb.toString();
    }

    /**
     * 生成更新语句
     * @param tableNameFUDTU
     * @param entityClassName
     * @param tableInfo
     * @return
     */
    private static String genUpdateMapper(String tableNameFUDTU,String entityClassName,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("    <update id=\"update"+tableNameFUDTU+"\" parameterType=\""+entityClassName+"\">\n");
        sb.append("        UPDATE "+tableInfo.getTableName()+" SET\n            ");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append(field.getFieldName()+" = #{"+StringUtil.underlineToHump(field.getFieldName())+"}, ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("\n        WHERE id = #{id}\n");
        sb.append("    </update>\n\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（单条）
     * @param tableNameFUDTU
     * @param entityClassName
     * @param tableName
     * @return
     */
    private static String genSelectMapper(String tableNameFUDTU,String entityClassName,String tableName){
        StringBuffer sb = new StringBuffer();
        sb.append("    <select id=\"select"+tableNameFUDTU+"\" parameterType=\"java.lang.Integer\" resultType=\""+entityClassName+"\">\n");
        sb.append("        SELECT * FROM "+tableName+"\n");
        sb.append("        WHERE id = #{id}\n");
        sb.append("    </select>\n\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条）
     * @param tableNameFUDTU
     * @param tableInfo
     * @return
     */
    private static String genSelectListMapper(String tableNameFUDTU,TableInfo tableInfo){
        StringBuffer sb = new StringBuffer();
        sb.append("    <select id=\"select"+tableNameFUDTU+"s\" parameterType=\"java.util.Map\">\n");
        sb.append("        SELECT * \n");
        sb.append("        FROM "+tableInfo.getTableName()+" \n");
        sb.append("        <where> 1=1 \n");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("            <if test=\""+field.getFieldName()+"!=null and "+field.getFieldName()+"!=''\">\n");
            sb.append("                AND "+field.getFieldName()+" = #{"+StringUtil.underlineToHump(field.getFieldName())+"}\n");
            sb.append("            </if>\n");
        }
        sb.append("        </where>\n    </select>\n\n");
        return sb.toString();
    }
}

