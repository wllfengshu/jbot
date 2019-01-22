package com.wllfengshu.core.utils;

/**
 * 生成serviceImpl层文件
 */
public class ServiceImplUtil {

    public static String genServiceImpl(String tableNameFLDTU,String tableNameFUDTU,String daoClassName,String entityClassName,String serviceImplPack,String serviceClassName){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU,serviceImplPack,daoClassName,entityClassName,serviceClassName));
        sb.append(genMember(tableNameFLDTU,tableNameFUDTU));
        sb.append(genInsert(tableNameFLDTU,tableNameFUDTU));
        sb.append(genDelete(tableNameFLDTU));
        sb.append(genUpdate(tableNameFLDTU,tableNameFUDTU));
        sb.append(genSelect(tableNameFLDTU));
        sb.append(genSelectList(tableNameFLDTU));
        sb.append(genTail());
        return sb.toString();
    }

    /**
     * 生成头
     * @return
     */
    private static String genHead(String tableNameFUDTU,String serviceImplPack,String daoClassName,String entityClassName,String serviceClassName){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+serviceImplPack+";\r\n\r\n");
        sb.append("import "+serviceClassName+";\r\n");
        sb.append("import "+daoClassName+";\r\n");
        sb.append("import "+entityClassName+";\r\n");
        sb.append("import org.slf4j.Logger;\r\n");
        sb.append("import org.slf4j.LoggerFactory;\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        sb.append("import java.util.HashMap;\r\n");
        sb.append("import java.util.Map;\r\n\r\n");
        sb.append("@Service\r\n");
        sb.append("public class "+tableNameFUDTU+"ServiceImpl implements "+tableNameFUDTU+"Service {\r\n\r\n");
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
     * 生成成员变量
     * @return
     */
    private static String genMember(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@Autowired\r\n");
        sb.append("\r\tprivate "+tableNameFUDTU+"Dao "+tableNameFLDTU+"Dao;\r\n\r\n");
        sb.append("\r\tprivate Logger logger = LoggerFactory.getLogger(getClass());\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成插入语句
     * @return
     */
    private static String genInsert(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@Override\r\n");
        sb.append("\r\tpublic Map<String, Object> insert("+tableNameFUDTU+" entity){\r\n");
        sb.append("\r\t\r\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\r\t\r\t"+tableNameFLDTU+"Dao.insert(entity);\r\n");
        sb.append("\r\t\r\treturn result;\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(String tableNameFLDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@Override\r\n");
        sb.append("\r\tpublic Map<String, Object> delete(Integer id){\r\n");
        sb.append("\r\t\r\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\r\t\r\t"+tableNameFLDTU+"Dao.delete(id);\r\n");
        sb.append("\r\t\r\treturn result;\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@Override\r\n");
        sb.append("\r\tpublic Map<String, Object> update("+tableNameFUDTU+" entity){\r\n");
        sb.append("\r\t\r\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\r\t\r\t"+tableNameFLDTU+"Dao.update(entity);\r\n");
        sb.append("\r\t\r\treturn result;\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(String tableNameFLDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@Override\r\n");
        sb.append("\r\tpublic Map<String, Object> select(Integer id){\r\n");
        sb.append("\r\t\r\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\r\t\r\tresult.put(\"data\","+tableNameFLDTU+"Dao.select(id));\r\n");
        sb.append("\r\t\r\treturn result;\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(String tableNameFLDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\r\t@Override\r\n");
        sb.append("\r\tpublic Map<String, Object> selects(Map<String, Object> params){\r\n");
        sb.append("\r\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\r\t\r\tresult.put(\"data\","+tableNameFLDTU+"Dao.selects(params));\r\n");
        sb.append("\r\t\r\treturn result;\r\n");
        sb.append("\r\t}\r\n\r\n");
        return sb.toString();
    }
}
