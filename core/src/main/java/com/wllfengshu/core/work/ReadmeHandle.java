package com.wllfengshu.core.work;

import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.core.model.RequestModel;

/**
 * 处理readme.md文件
 */
public class ReadmeHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“模板”
        replaceModel(requestModel);
    }

    private static void replaceModel(RequestModel requestModel){
        FileReplaceUtil.replace(
                    requestModel.getProjectPath()+"/README.md",
                             new String[]{"标准模板"},
                             new String[]{requestModel.getProjectName()+"项目"}
        );
    }
}
