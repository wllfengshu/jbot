package com.wllfengshu.core.work.javaHandle;

import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.model.RequestModel;
import com.wllfengshu.core.model.TableModel;
import com.wllfengshu.core.utils.ServiceImplUtil;

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
            String serviceImpl=ServiceImplUtil.genServiceImpl(t.getTableNameFLDTU(),t.getTableNameFUDTU(),t.getDaoClassName(),t.getEntityClassName(),requestModel.getServiceImplPack(),t.getServiceClassName());
            FileUtil.createFile(requestModel.getJavaPath()+"/"+StringUtil.spotToSlash(t.getServiceImplClassName())+".java",serviceImpl);
        }
    }
}
