package com.wllfengshu.core.utils;

/**
 * 生成serviceImpl层文件
 */
public class ServiceImplUtil {

    public static String genServiceImpl(String tableNameFUDTU,String daoClassName,String entityClassName,String serviceImplPack,String serviceClassName){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU,serviceImplPack,daoClassName,entityClassName,serviceClassName));
        sb.append(genMember(tableNameFUDTU));
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
    private static String genMember(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Autowired\r\n");
        sb.append("\tprivate "+tableNameFUDTU+"Dao dao;\r\n\r\n");
        sb.append("\tprivate Logger logger = LoggerFactory.getLogger(getClass());\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成插入语句
     * @return
     */
    private static String genInsert(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> insert("+tableNameFUDTU+" entity){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\tdao.insert(entity);\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> delete(Integer id){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\tdao.delete(id);\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> update("+tableNameFUDTU+" entity){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\tdao.update(entity);\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> select(Integer id){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\tresult.put(\"data\",dao.select(id));\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> selects(Map<String, Object> params){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\tresult.put(\"data\",dao.selects(params));\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n");
        return sb.toString();
    }
}
