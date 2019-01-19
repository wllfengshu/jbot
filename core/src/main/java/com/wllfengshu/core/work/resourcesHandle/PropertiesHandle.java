package com.wllfengshu.core.work.resourcesHandle;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.core.model.RequestModel;

/**
 * 处理application.properties文件
 */
public class PropertiesHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“mapper映射实体类的包的路径”
        replace(requestModel);
    }

    private static void replace(RequestModel requestModel){
        FileReplaceUtil.replace(
                    requestModel.getResourcesPath()+"/application.properties",
                             new String[]{Collective.MODEL_PACKAGE_NAME,
                                          Collective.MODEL_PROJECT_NAME},
                             new String[]{requestModel.getPackageName(),
                                          requestModel.getProjectName()}
        );
    }
}
