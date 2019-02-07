package com.wllfengshu.core.work.java;

import com.wllfengshu.common.model.RequestModel;
import com.wllfengshu.common.model.TableModel;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;

/**
 * 处理dao文件
 * @author wllfengshu
 */
public class DaoHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的dao文件
        genFile(requestModel);
    }

    private static void genFile(RequestModel requestModel){
        for (TableModel t:requestModel.getTableModels()) {
            String dao=genData(t.getTableNameFUDTU(),t.getEntityClassName(),requestModel.getDaoPack(),requestModel.getUtilsPack());
            FileUtil.createFile(requestModel.getJavaPath()+"/"+StringUtil.spotToSlash(t.getDaoClassName())+".java",dao);
        }
    }

    private static String genData(String tableNameFUDTU,String entityClassName,String daoPack,String utilsPack){
        StringBuffer sb=new StringBuffer();
        sb.append("package "+daoPack+";\r\n\r\n");
        sb.append("import "+entityClassName+";\r\n");
        sb.append("import "+utilsPack+".MyMapper;\r\n");
        sb.append("import org.springframework.stereotype.Repository;\r\n");
        sb.append("import java.util.List;\r\n");
        sb.append("import java.util.Map;\r\n\r\n");
        sb.append("@Repository\r\n");
        sb.append("public interface "+tableNameFUDTU+"Dao extends MyMapper<"+tableNameFUDTU+"> {\r\n\r\n");
        sb.append("\tList<"+tableNameFUDTU+"> select"+tableNameFUDTU+"s(Map<String, Object> params);\r\n");
        sb.append("\tInteger select"+tableNameFUDTU+"sCount(Map<String, Object> params);\r\n");
        sb.append("\r\n}\r\n");
        return sb.toString();
    }

}
