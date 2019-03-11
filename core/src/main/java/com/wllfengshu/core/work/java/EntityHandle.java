package com.wllfengshu.core.work.java;

import com.wllfengshu.common.entity.FieldInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.model.RequestModel;
import com.wllfengshu.common.model.TableModel;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 处理entity文件
 * @author wllfengshu
 */
public class EntityHandle {

    public static void start(RequestModel model){
        //1、生成对应的entity文件
        genFile(model);
    }

    private static void genFile(RequestModel model){
        for (TableModel t:model.getTableModels()) {
            String entity=genData(t.getTableNameFUDTU(),model.getEntityPack(),t.getTableInfo());
            FileUtil.createFile(model.getJavaPath()+"/"+StringUtil.spotToSlash(t.getEntityClassPack())+".java",entity);
        }
    }

    private static String genData(String tableNameFUDTU,String entityPack,TableInfo tableInfo){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU,entityPack,tableInfo.getTableName()));
        sb.append(genAttrs(tableInfo));
        sb.append(genMethod(tableInfo));
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static String genHead(String tableNameFUDTU,String entityPack,String tableName){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+entityPack+";\r\n\r\n");
        sb.append("import com.fasterxml.jackson.annotation.JsonFormat;\r\n");
        sb.append("import javax.persistence.*;\r\n");
        sb.append("import java.io.Serializable;\r\n");
        sb.append("import java.util.Date;\r\n\r\n");
        sb.append("@Table(name = \""+tableName+"\")\r\n");
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
            sb.append("\t/**\r\n");
            sb.append("\t * "+field.getFieldComment()==null?"":field.getFieldComment()+"\r\n");
            sb.append("\t **/\r\n");
            if ("id".equals(field.getFieldName())){//如果是id，则默认为主键
                sb.append("\t@Id\r\n");
            }
            if ("Date".equals(StringUtil.sqlType2JavaType(field.getFieldType()))){
                sb.append("\t@JsonFormat(pattern = \"yyyy-MM-dd HH:mm:ss\", timezone=\"GMT+8\")\r\n");
            }
            sb.append("\t@Column(name = \""+field.getFieldName()+"\")\r\n");
            sb.append("\tprivate "+StringUtil.sqlType2JavaType(field.getFieldType())+" "+StringUtil.underlineToHump(field.getFieldName()) +";\r\n\r\n ");
        }
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
            sb.append("\r\n\tpublic void set"+StringUtil.toFirstCharUpperCase(fieldName)+"("+fieldType+" "+fieldName+") {\r\n" +
                    "\t\tthis."+fieldName+" = "+fieldName+";\r\n" +
                    "\t}\r\n");
            sb.append("\r\n\tpublic "+fieldType+" get"+StringUtil.toFirstCharUpperCase(fieldName)+"() {\r\n" +
                    "\t\treturn "+fieldName+";\r\n" +
                    "\t}\r\n");
        }
        return sb.toString();
    }
}
