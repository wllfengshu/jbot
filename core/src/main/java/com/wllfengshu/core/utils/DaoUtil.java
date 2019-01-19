package com.wllfengshu.core.utils;

/**
 * 生成dao层文件
 */
public class DaoUtil {

    public static String genDao(String tableNameFUDTU,String entityClassName,String daoPack){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU,daoPack,entityClassName));
        sb.append(genInsert(tableNameFUDTU));
        sb.append(genDelete());
        sb.append(genUpdate(tableNameFUDTU));
        sb.append(genSelect(tableNameFUDTU));
        sb.append(genSelectList(tableNameFUDTU));
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static String genHead(String tableNameFUDTU,String daoPack,String entityClassName){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+daoPack+";\r\n\r\n");
        sb.append("import "+entityClassName+";\r\n");
        sb.append("import org.apache.ibatis.annotations.Param;\r\n");
        sb.append("import org.springframework.stereotype.Repository;\r\n");
        sb.append("import java.util.List;\r\n");
        sb.append("import java.util.Map;\r\n\r\n");
        sb.append("@Repository\r\n");
        sb.append("public interface "+tableNameFUDTU+"Dao {\r\n\r\n");
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
     * 生成插入语句
     * @return
     */
    private static String genInsert(String tableNameFUDTU){
        return "\tvoid insert("+tableNameFUDTU+" entity);\r\n";
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(){
        return "\tvoid delete(@Param(\"id\") Integer id);\r\n";
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String tableNameFUDTU){
        return "\tvoid update("+tableNameFUDTU+" entity);\r\n";
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(String tableNameFUDTU){
        return "\t"+tableNameFUDTU+" select(@Param(\"id\") Integer id);\r\n";
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(String tableNameFUDTU){
        return "\tList<"+tableNameFUDTU+"> selects(Map<String, Object> params);\r\n";
    }
}
