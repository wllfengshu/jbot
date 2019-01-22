package com.wllfengshu.core.utils;

import com.wllfengshu.common.entity.FieldInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.StringUtil;

public class EntityUtil {

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
        sb.append("\r\tprivate static final long serialVersionUID = 1L;\r\n\r\n");
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
            sb.append("\r\tprivate "+StringUtil.sqlType2JavaType(field.getFieldType())+" "+StringUtil.underlineToHump(field.getFieldName()) +";\r\n ");
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
            String fieldName=StringUtil.underlineToHump(field.getFieldName());
            String fieldType=StringUtil.sqlType2JavaType(field.getFieldType());
            sb.append("\r\tpublic void set"+StringUtil.toFirstCharUpperCase(fieldName)+"("+fieldType+" "+fieldName+") {\r\n" +
                    "\r\t\r\tthis."+fieldName+" = "+fieldName+";\r\n" +
                    "\r\t}\r\n");
            sb.append("\r\tpublic "+fieldType+" get"+StringUtil.toFirstCharUpperCase(fieldName)+"() {\r\n" +
                    "\r\t\r\treturn "+fieldName+";\r\n" +
                    "\r\t}\r\n");
        }
        return sb.toString();
    }
}
