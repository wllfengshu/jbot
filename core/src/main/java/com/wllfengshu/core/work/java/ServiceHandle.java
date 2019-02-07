package com.wllfengshu.core.work.java;

import com.wllfengshu.common.model.RequestModel;
import com.wllfengshu.common.model.TableModel;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 处理service文件
 * @author wllfengshu
 */
public class ServiceHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的service文件
        genFile(requestModel);
}

    private static void genFile(RequestModel requestModel){
        for (TableModel t:requestModel.getTableModels()) {
            String service= genData(t.getTableNameFUDTU(),t.getEntityClassName(),requestModel.getServicePack());
            FileUtil.createFile(requestModel.getJavaPath()+"/"+StringUtil.spotToSlash(t.getServiceClassName())+".java",service);
        }
    }

    private static String genData(String tableNameFUDTU,String entityClassName,String servicePack){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+servicePack+";\r\n\r\n");
        sb.append("import "+entityClassName+";\r\n");
        sb.append("import org.springframework.stereotype.Service;\r\n");
        sb.append("import java.util.Map;\r\n\r\n");
        sb.append("@Service\r\n");
        sb.append("public interface "+tableNameFUDTU+"Service {\r\n\r\n");
        sb.append("\tMap<String, Object> insert("+tableNameFUDTU+" entity);\r\n");
        sb.append("\tMap<String, Object> delete(Integer id);\r\n");
        sb.append("\tMap<String, Object> update("+tableNameFUDTU+" entity);\r\n");
        sb.append("\tMap<String, Object> select(Integer id);\r\n");
        sb.append("\tMap<String, Object> selects(Map<String, Object> params);\r\n");
        sb.append("\r\n}\r\n");
        return sb.toString();
    }

}
