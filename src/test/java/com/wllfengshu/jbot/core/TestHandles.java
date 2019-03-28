package com.wllfengshu.jbot.core;

import com.wllfengshu.jbot.common.exception.CustomException;
import com.wllfengshu.jbot.core.after.AfterHandle;
import com.wllfengshu.jbot.core.work.common.CommonHandle;
import com.wllfengshu.jbot.core.work.java.*;
import com.wllfengshu.jbot.core.work.resources.MapperHandle;
import org.junit.Test;

/**
 * 以下以“生表-->课程表（多对多）”为例,对core模块中的Handles进行单元测试：
 *      数据库名：school
 *      学生表t_student：姓名sName,学号sNum
 *      课程表t_course：课程名cName,课程编号cNum
 */
public class TestHandles extends TestSuper {

    @Test
    public void testBeforeHandles() throws CustomException {
    }

    @Test
    public void testAfterHandles() throws CustomException{
        AfterHandle.start(requestModel);
    }

    @Test
    public void testStartupHandles() throws CustomException{
        CommonHandle.start(requestModel);
    }

    @Test
    public void testMapperHandles() throws CustomException{
        MapperHandle.start(requestModel);
    }

    @Test
    public void testDaoHandles() throws CustomException{
        DaoHandle.start(requestModel);
    }

    @Test
    public void testEntityHandles() throws CustomException{
        EntityHandle.start(requestModel);
    }

    @Test
    public void testRestHandles() throws CustomException{
        RestHandle.start(requestModel);
    }

    @Test
    public void testServiceHandles() throws CustomException{
        ServiceHandle.start(requestModel);
    }

    @Test
    public void testServiceImplHandles() throws CustomException{
        ServiceImplHandle.start(requestModel);
    }
}
