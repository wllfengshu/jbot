package com.wllfengshu.core.work.javaHandle;

import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.model.RequestModel;
import com.wllfengshu.core.model.TableModel;
import com.wllfengshu.core.utils.RestUtil;

public class RestHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的rest文件
        genFile(requestModel);
    }

    private static void genFile(RequestModel requestModel){
        for (TableModel t:requestModel.getTableModels()) {
            String rest=RestUtil.genRest(t.getTableNameFLDTU(),t.getTableNameFUDTU(),t.getServiceClassName(),t.getEntityClassName(),requestModel.getRestPack());
            FileUtil.createFile(requestModel.getJavaPath()+"/"+StringUtil.spotToSlash(requestModel.getRestPack())+"/"+t.getTableNameFUDTU()+"Rest.java",rest);
        }
    }
}
