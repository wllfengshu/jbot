package com.wllfengshu.core.work.java;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.model.RequestModel;
import com.wllfengshu.common.utils.FileReplaceUtil;

/**
 * 处理通用mapper
 * @author wllfengshu
 */
public class MyMapperHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“包名”和“项目名”
        replace(requestModel);
    }

    private static void replace(RequestModel requestModel){
        FileReplaceUtil.replace(
                requestModel.getPackageBasePath()+"/utils/MyMapper.java",
                new String[]{Collective.MODEL_PACKAGE_NAME,
                        Collective.MODEL_PROJECT_NAME},
                new String[]{requestModel.getPackageName(),
                        requestModel.getProjectName()}
        );
    }
}
