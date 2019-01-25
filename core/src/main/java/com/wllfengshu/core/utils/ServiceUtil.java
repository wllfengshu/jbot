package com.wllfengshu.core.utils;

/**
 * 生成service层文件
 * @author wllfengshu
 */
public class ServiceUtil {

    public static String genService(String tableNameFUDTU,String entityClassName,String servicePack){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU,servicePack,entityClassName));
        sb.append(genInsert(tableNameFUDTU));
        sb.append(genDelete());
        sb.append(genUpdate(tableNameFUDTU));
        sb.append(genSelect());
        sb.append(genSelectList());
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static String genHead(String tableNameFUDTU,String servicePack,String entityClassName){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+servicePack+";\r\n\r\n");
        sb.append("import "+entityClassName+";\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        sb.append("import java.util.Map;\r\n\r\n");
        sb.append("@Service\r\n");
        sb.append("public interface "+tableNameFUDTU+"Service {\r\n\r\n");
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
        return "\tMap<String, Object> insert("+tableNameFUDTU+" entity);\r\n";
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(){
        return "\tMap<String, Object> delete(Integer id);\r\n";
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String tableNameFUDTU){
        return "\tMap<String, Object> update("+tableNameFUDTU+" entity);\r\n";
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(){
        return "\tMap<String, Object> select(Integer id);\r\n";
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(){
        return "\tMap<String, Object> selects(Map<String, Object> params);\r\n";
    }
}
