package com.wllfengshu.core.work.java;

import com.wllfengshu.common.model.RequestModel;
import com.wllfengshu.common.model.TableModel;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 处理serviceImpl文件
 * @author wllfengshu
 */
public class ServiceImplHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的serviceImpl文件
        genFile(requestModel);
    }

    private static void genFile(RequestModel requestModel){
        for (TableModel t:requestModel.getTableModels()) {
            String serviceImpl=genData(t.getTableNameFLDTU(),t.getTableNameFUDTU(),t.getDaoClassName(),t.getEntityClassName(),requestModel.getServiceImplPack(),t.getServiceClassName());
            FileUtil.createFile(requestModel.getJavaPath()+"/"+StringUtil.spotToSlash(t.getServiceImplClassName())+".java",serviceImpl);
        }
    }

    private static String genData(String tableNameFLDTU,String tableNameFUDTU,String daoClassName,String entityClassName,String serviceImplPack,String serviceClassName){
        StringBuffer sb=new StringBuffer();
        sb.append(genHead(tableNameFUDTU,serviceImplPack,daoClassName,entityClassName,serviceClassName));
        sb.append(genMember(tableNameFLDTU,tableNameFUDTU));
        sb.append(genInsert(tableNameFLDTU,tableNameFUDTU));
        sb.append(genDelete(tableNameFLDTU));
        sb.append(genUpdate(tableNameFLDTU,tableNameFUDTU));
        sb.append(genSelect(tableNameFLDTU));
        sb.append(genSelectList(tableNameFLDTU,tableNameFUDTU));
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
        sb.append("\t@Autowired\r\n");
        sb.append("\tprivate "+tableNameFUDTU+"Dao "+tableNameFLDTU+"Dao;\r\n\r\n");
        sb.append("\tprivate Logger logger = LoggerFactory.getLogger(getClass());\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成插入语句
     * @return
     */
    private static String genInsert(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> insert("+tableNameFUDTU+" entity){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\t"+tableNameFLDTU+"Dao.insert(entity);\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成删除语句
     * @return
     */
    private static String genDelete(String tableNameFLDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> delete(Integer id){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\t"+tableNameFLDTU+"Dao.deleteByPrimaryKey(id);\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成更新语句
     * @return
     */
    private static String genUpdate(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> update("+tableNameFUDTU+" entity){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\t"+tableNameFLDTU+"Dao.updateByPrimaryKey(entity);\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（单条）
     * @return
     */
    private static String genSelect(String tableNameFLDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> select(Integer id){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\tresult.put(\"data\","+tableNameFLDTU+"Dao.selectByPrimaryKey(id));\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }

    /**
     * 生成查询语句（多条）
     * @return
     */
    private static String genSelectList(String tableNameFLDTU,String tableNameFUDTU){
        StringBuffer sb=new StringBuffer();
        sb.append("\t@Override\r\n");
        sb.append("\tpublic Map<String, Object> selects(Map<String, Object> params){\r\n");
        sb.append("\t\tMap<String, Object> result = new HashMap<>();\r\n");
        sb.append("\t\tresult.put(\"data\","+tableNameFLDTU+"Dao.select"+tableNameFUDTU+"s(params));\r\n");
        sb.append("\t\tresult.put(\"total\","+tableNameFLDTU+"Dao.select"+tableNameFUDTU+"sCount(params));\r\n");
        sb.append("\t\treturn result;\r\n");
        sb.append("\t}\r\n\r\n");
        return sb.toString();
    }
}
