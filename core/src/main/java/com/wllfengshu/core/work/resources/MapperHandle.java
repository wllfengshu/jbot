package com.wllfengshu.core.work.resources;

import com.wllfengshu.common.entity.FieldInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.model.RequestModel;
import com.wllfengshu.common.model.TableModel;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 处理mapper文件
 * @author wllfengshu
 */
public class MapperHandle {

    public static void start(RequestModel model){
        //1、生成对应的xml文件
        genFile(model);
    }

    private static void genFile(RequestModel model){
        for (TableModel t:model.getTableModels()) {
            String mapper=genData(t.getDaoClassPack(),t.getEntityClassPack(),t.getTableInfo(),t.getTableNameFUDTU());
            FileUtil.createFile(model.getResourcesPath()+"/mapper/"+t.getTableNameFUDTU()+".xml",mapper);
        }
    }

    private static String genData(String daoClassName,String entityClassName,TableInfo tableInfo,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(daoClassName));
        sb.append(genResult(entityClassName,tableInfo));
        sb.append(genCommonSql(tableInfo,tableNameFUDTU));
        sb.append(genSelects(tableInfo,tableNameFUDTU));
        sb.append(genSelectsCount(tableInfo,tableNameFUDTU));
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
     * 公共sql
     * @return
     */
    private static String genCommonSql(TableInfo tableInfo,String tableNameFUDTU){
        StringBuffer sb = new StringBuffer();
        sb.append("\t<sql id=\"select"+tableNameFUDTU+"sWhere\">\n");
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("\t\t<if test=\""+field.getFieldName()+"!=null and "+field.getFieldName()+"!=''\">\r\n");
            sb.append("\t\t\tAND "+field.getFieldName()+" = #{"+StringUtil.underlineToHump(field.getFieldName())+"}\r\n");
            sb.append("\t\t</if>\r\n");
        }
        sb.append("\t</sql>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条数据）
     * @return
     */
    private static String genSelects(TableInfo tableInfo,String tableNameFUDTU){
        StringBuffer sb = new StringBuffer();
        sb.append("\t<select id=\"select"+tableNameFUDTU+"s\" parameterType=\"java.util.Map\" resultMap=\"resultMap\">\r\n");
        sb.append("\t\tSELECT * \r\n");
        sb.append("\t\tFROM "+tableInfo.getTableName()+" \r\n");
        sb.append("\t\t<where> 1=1 \r\n");
        sb.append("\t\t\t<include refid=\"select"+tableNameFUDTU+"sWhere\"/>\r\n");
        sb.append("\t\t\tlimit ${pageNo * pageSize} , ${pageSize}\r\n");
        sb.append("\t\t\t</where>\r\n\t</select>\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（统计总数）
     * @return
     */
    private static String genSelectsCount(TableInfo tableInfo,String tableNameFUDTU){
        StringBuffer sb = new StringBuffer();
        sb.append("\t<select id=\"select"+tableNameFUDTU+"sCount\" parameterType=\"java.util.Map\" resultType=\"java.lang.Integer\">\r\n");
        sb.append("\t\tSELECT count(1) \r\n");
        sb.append("\t\tFROM "+tableInfo.getTableName()+" \r\n");
        sb.append("\t\t<where> 1=1 \r\n");
        sb.append("\t\t\t<include refid=\"select"+tableNameFUDTU+"sWhere\"/>\r\n");
        sb.append("\t\t\t</where>\r\n\t</select>\r\n\r\n");
        return sb.toString();
    }
}

