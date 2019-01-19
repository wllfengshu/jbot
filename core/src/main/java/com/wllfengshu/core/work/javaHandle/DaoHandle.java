package com.wllfengshu.core.work.javaHandle;

import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.model.RequestModel;
import com.wllfengshu.core.model.TableModel;
import com.wllfengshu.core.utils.DaoUtil;
import com.wllfengshu.common.utils.FileUtil;

public class DaoHandle {

    public static void start(RequestModel requestModel){
        //1、生成对应的dao文件
        genFile(requestModel);
}

    private static void genFile(RequestModel requestModel){
        for (TableModel t:requestModel.getTableModels()) {
            String dao=DaoUtil.genDao(t.getTableNameFUDTU(),t.getEntityClassName(),requestModel.getDaoPack());
            FileUtil.createFile(requestModel.getJavaPath()+"/"+StringUtil.spotToSlash(t.getDaoClassName())+".java",dao);
        }
    }
}
