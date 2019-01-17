package com.wllfengshu.core.utils;

import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 生成service层文件
 */
public class ServiceUtil {

    public static String genService(String projectName,String packageName,TableInfo tableInfo){
        String tableNameFUDTU=StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(StringUtil.delTUnderline(tableInfo.getTableName())));
        String entityClassName=packageName+"."+projectName+".entity."+tableNameFUDTU;
        String pack=packageName+"."+projectName+".service";
        StringBuffer service=new StringBuffer();
        service.append(genHead(tableNameFUDTU,pack,entityClassName));
        service.append(genInsert(tableNameFUDTU));
        service.append(genDelete());
        service.append(genUpdate(tableNameFUDTU));
        service.append(genSelect());
        service.append(genSelectList());
        service.append(genTail());
        return service.toString();
    }

    /**
     * 生成头
     * @param pack
     * @return
     */
    private static String genHead(String tableNameFUDTU,String pack,String entityClassName){
        return  "package "+pack+";\n\n" +
                "import "+entityClassName+";\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import java.util.Map;\n\n" +
                "@Service\n" +
                "public interface "+tableNameFUDTU+"Service {\n\n";
    }

    /**
     * 生成尾
     * @return
     */
    private static String genTail(){
        return "\n}\n";
    }

    /**
     * 生成插入语句
     * @param tableNameFUDTU
     * @return
     */
    private static String genInsert(String tableNameFUDTU){
        return "    Map<String, Object> insert("+tableNameFUDTU+" entity);\n";
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(){
        return "    Map<String, Object> delete(Integer id);\n";
    }

    /**
     * 生成更新语句
     * @param tableNameFUDTU
     * @return
     */
    private static String genUpdate(String tableNameFUDTU){
        return "    Map<String, Object> update("+tableNameFUDTU+" entity);\n";
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(){
        return "    Map<String, Object> select(Integer id);\n";
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(){
        return "    Map<String, Object> selects(Map<String, Object> params);\n";
    }
}
