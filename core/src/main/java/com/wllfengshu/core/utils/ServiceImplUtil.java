package com.wllfengshu.core.utils;

import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 生成serviceImpl层文件
 */
public class ServiceImplUtil {

    public static String genServiceImpl(String projectName,String packageName,TableInfo tableInfo){
        String tableNameFUDTU=StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(StringUtil.delTUnderline(tableInfo.getTableName())));
        String daoClassName=packageName+"."+projectName+".dao."+tableNameFUDTU+"Dao";
        String entityClassName=packageName+"."+projectName+".entity."+tableNameFUDTU;
        String pack=packageName+"."+projectName+".service.impl";
        StringBuffer serviceImpl=new StringBuffer();
        serviceImpl.append(genHead(tableNameFUDTU,pack,daoClassName,entityClassName));
        serviceImpl.append(genMember(tableNameFUDTU));
        serviceImpl.append(genInsert(tableNameFUDTU));
        serviceImpl.append(genDelete());
        serviceImpl.append(genUpdate(tableNameFUDTU));
        serviceImpl.append(genSelect());
        serviceImpl.append(genSelectList());
        serviceImpl.append(genTail());
        return serviceImpl.toString();
    }

    /**
     * 生成头
     * @param tableNameFUDTU
     * @param pack
     * @param daoClassName
     * @param entityClassName
     * @return
     */
    private static String genHead(String tableNameFUDTU,String pack,String daoClassName,String entityClassName){
        return  "package "+pack+";\n\n" +
                "import "+daoClassName+";\n" +
                "import "+entityClassName+";\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import java.util.Date;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n\n" +
                "@Service\n" +
                "public class "+tableNameFUDTU+"ServiceImpl implements "+tableNameFUDTU+"Service {\n\n";
    }

    /**
     * 生成尾
     * @return
     */
    private static String genTail(){
        return "\n}\n";
    }

    /**
     * 生成成员变量
     * @param tableNameFUDTU
     * @return
     */
    private static String genMember(String tableNameFUDTU){
        return  "    @Autowired\n" +
                "    private "+tableNameFUDTU+"Dao dao;\n\n" +
                "    private Logger logger = LoggerFactory.getLogger(getClass());\n\n";
    }

    /**
     * 生成插入语句
     * @param tableNameFUDTU
     * @return
     */
    private static String genInsert(String tableNameFUDTU){
        return  "    @Override\n" +
                "    public Map<String, Object> insert("+tableNameFUDTU+" entity){\n" +
                "        Map<String, Object> result = new HashMap<>();\n" +
                "        dao.insert(entity);\n" +
                "        return result;\n" +
                "    }\n\n";
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(){
        return  "    @Override\n" +
                "    public Map<String, Object> delete(Integer id){\n" +
                "        Map<String, Object> result = new HashMap<>();\n" +
                "        dao.delete(id);\n" +
                "        return result;\n" +
                "    }\n\n";
    }

    /**
     * 生成更新语句
     * @param tableNameFUDTU
     * @return
     */
    private static String genUpdate(String tableNameFUDTU){
        return  "    @Override\n" +
                "    public Map<String, Object> update("+tableNameFUDTU+" entity){\n" +
                "        Map<String, Object> result = new HashMap<>();\n" +
                "        dao.update(entity);\n" +
                "        return result;\n" +
                "    }\n\n";
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(){
        return  "    @Override\n" +
                "    public Map<String, Object> select(Integer id){\n" +
                "        Map<String, Object> result = new HashMap<>();\n" +
                "        result.put(\"data\",dao.select(id));\n" +
                "        return result;\n" +
                "    }\n\n";
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(){
        return  "    @Override\n" +
                "    public Map<String, Object> selects(Map<String, Object> params){\n" +
                "        Map<String, Object> result = new HashMap<>();\n" +
                "        result.put(\"data\",dao.selects(params));\n" +
                "        return result;\n" +
                "    }\n\n";
    }
}
