package com.wllfengshu.jbot.core;

import com.wllfengshu.jbot.common.exception.CustomException;
import com.wllfengshu.jbot.common.utils.FileUtil;
import org.junit.Test;

import java.io.File;

public class TestOther {

    @Test
    public void testFile() throws CustomException {
        System.out.println(new File("../model").getAbsolutePath());
        //注意：在本测试用例中需要使用../model路径，但是在使用web模块启动时应该是./model，不然路径错误
        String str="../model/Dockerfile";
        FileUtil.copyFile(str,"/home/listen/Apps/exam/Dockerfile");
    }
}
