package com.wllfengshu.core.utils;

import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 生成dao层文件
 */
public class DaoUtil {

    public static String genDao(String projectName,String packageName,TableInfo tableInfo){
        String tableNameFUDTU=StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(StringUtil.delTUnderline(tableInfo.getTableName())));
        String entityClassName=packageName+"."+projectName+".entity."+tableNameFUDTU;
        String pack=packageName+"."+projectName+".dao";
        StringBuffer dao=new StringBuffer();
        dao.append(genHead(tableNameFUDTU,pack,entityClassName));
        dao.append(genInsert(tableNameFUDTU));
        dao.append(genDelete(tableNameFUDTU));
        dao.append(genUpdate(tableNameFUDTU));
        dao.append(genSelect(tableNameFUDTU));
        dao.append(genSelectList(tableNameFUDTU));
        dao.append(genTail());
        return dao.toString();
    }

    /**
     * 生成头
     * @param pack
     * @return
     */
    private static String genHead(String tableNameFUDTU,String pack,String entityClassName){
        return  "package "+pack+";\n\n" +
                "import "+entityClassName+";\n" +
                "import org.apache.ibatis.annotations.Param;\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n\n" +
                "@Repository\n" +
                "public interface "+tableNameFUDTU+"Dao {\n\n";
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
        return "    void insert("+tableNameFUDTU+" entity);\n";
    }

    /**
     * 生成删除语句
     * * @param tableNameFUDTU
     * @return
     */
    private static String genDelete(String tableNameFUDTU){
        return "    void delete(@Param(\"id\") Integer id);\n";
    }

    /**
     * 生成更新语句
     * @param tableNameFUDTU
     * @return
     */
    private static String genUpdate(String tableNameFUDTU){
        return "    void update("+tableNameFUDTU+" entity);\n";
    }

    /**
     * 生成查询语句（单条）
     * @param tableNameFUDTU
     * @return
     */
    private static String genSelect(String tableNameFUDTU){
        return "    "+tableNameFUDTU+" select(@Param(\"id\") Integer id);\n";
    }

    /**
     * 生成查询语句（多条）
     * @param tableNameFUDTU
     * @return
     */
    private static String genSelectList(String tableNameFUDTU){
        return "    List<"+tableNameFUDTU+"> selects(Map<String, Object> params);\n";
    }
}
