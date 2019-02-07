package com.wllfengshu.core.work;

import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.common.model.RequestModel;

/**
 * 处理readme.md文件
 * @author wllfengshu
 */
public class ReadmeHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“模板”
        replace(requestModel);
    }

    private static void replace(RequestModel requestModel){
        FileReplaceUtil.replace(
                    requestModel.getProjectPath()+"/README.md",
                             new String[]{"标准模板"},
                             new String[]{requestModel.getProjectName()+"项目"}
        );
    }
}
