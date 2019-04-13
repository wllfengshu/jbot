package com.wllfengshu.jbot.core.after;

import com.wllfengshu.jbot.common.exception.CustomException;
import com.wllfengshu.jbot.common.utils.FileUtil;
import com.wllfengshu.jbot.common.model.RequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 负责core结束后的善后工作
 *
 * @author wllfengshu
 */
public class AfterHandle {

    private static Logger logger = LoggerFactory.getLogger(AfterHandle.class);

    public static void start(RequestModel model) throws CustomException {
        //1、把目标项目压缩为zip文件
        produceZip(model);
    }

    private static void produceZip(RequestModel model) throws CustomException {
        FileUtil.fileToZip(model.getProjectPath() + ".zip", model.getProjectPath());
    }
}
