package com.wllfengshu.jbot.core.work.java;

import com.wllfengshu.jbot.common.exception.CustomException;
import com.wllfengshu.jbot.common.model.RequestModel;
import com.wllfengshu.jbot.common.model.TableModel;
import com.wllfengshu.jbot.common.utils.FileUtil;
import com.wllfengshu.jbot.common.utils.StringUtil;

/**
 * 处理dao文件
 * @author wllfengshu
 */
public class DaoHandle {

    public static void start(RequestModel model)throws CustomException {
        //1、生成对应的dao文件
        genFile(model);
    }

    private static void genFile(RequestModel model)throws CustomException {
        for (TableModel t:model.getTableModels()) {
            String dao=genData(t.getTableNameFUDTU(),t.getEntityClassPack(),model.getDaoPack(),model.getUtilsPack());
            FileUtil.createFile(model.getJavaPath()+"/"+StringUtil.spotToSlash(t.getDaoClassPack())+".java",dao);
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
