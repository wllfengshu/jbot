package com.wllfengshu.core.utils;

import com.wllfengshu.common.entity.FieldInfo;
import com.wllfengshu.common.entity.TableInfo;

public class EntityUtil {
    /**
     * 实体类难在怎么把数据库中字段的类型，转换为java中的类型，我们使用Set
     * @return
     */
    public static String genEntity(String tableNameFUDTU,String entityPack,TableInfo tableInfo){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU,entityPack));
        sb.append(genAttrs(tableInfo));
        sb.append(genMethod(tableInfo));
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static String genHead(String tableNameFUDTU,String entityPack){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+entityPack+";\r\n\r\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonFormat;\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("import java.util.Date;\r\n\r\n");
        sb.append("public class "+tableNameFUDTU+" implements Serializable{\r\n\r\n");
        sb.append("\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成尾
     * @return
     */
    private static String genTail(){
        return "\r\n}\r\n";
    }

    /**
     * 生成属性
     * @return
     */
    private static String genAttrs(TableInfo tableInfo) {
        StringBuffer sb = new StringBuffer();
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("\tprivate "+field.getFieldType()+" "+field.getFieldName()+";\r\n ");
        }
        sb.append("\r\n");
        return sb.toString();
    }

    /**
     * 生成方法
     * @param
     */
    private static String genMethod(TableInfo tableInfo) {
        StringBuffer sb = new StringBuffer();
        for (FieldInfo field : tableInfo.getFields()) {
            sb.append("\tpublic void set"+field.getFieldName()+"("+field.getFieldType()+" "+field.getFieldName()+") {\r\n" +
                      "\t\tthis."+field.getFieldName()+" = "+field.getFieldName()+";\r\n" +
                      "\t}\r\n");
            sb.append("\tpublic "+field.getFieldType()+" get"+field.getFieldName()+"() {\r\n" +
                      "\t\treturn "+field.getFieldName()+";\r\n" +
                      "\t}\r\n");
        }
        return sb.toString();
    }
}
