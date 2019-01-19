package com.wllfengshu.core.work;

import com.wllfengshu.common.utils.FileReplaceUtil;
import com.wllfengshu.common.utils.TimeUtil;
import com.wllfengshu.core.model.RequestModel;

/**
 * 处理dockerfile文件
 */
public class DockerfileHandle {

    public static void start(RequestModel requestModel){
        //1、修改里面的“时间”
        replaceTime(requestModel);
    }

    private static void replaceTime(RequestModel requestModel){
        FileReplaceUtil.replace(
                    requestModel.getProjectPath()+"/Dockerfile",
                             new String[]{"20190101000000"},
                             new String[]{TimeUtil.getCurrentYearMonth("")}
        );
    }
}
