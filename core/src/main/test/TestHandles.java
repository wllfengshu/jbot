
import com.wllfengshu.core.after.AfterHandle;
import com.wllfengshu.core.work.common.CommonHandle;
import com.wllfengshu.core.work.java.*;
import com.wllfengshu.core.work.resources.MapperHandle;
import org.junit.Test;

/**
 * 以下以“生表-->课程表（多对多）”为例,对core模块中的Handles进行单元测试：
 *      数据库名：school
 *      学生表t_student：姓名sName,学号sNum
 *      课程表t_course：课程名cName,课程编号cNum
 */
public class TestHandles extends TestSuper {

    @Test
    public void testBeforeHandles(){
    }

    @Test
    public void testAfterHandles(){
        AfterHandle.start(requestModel);
    }

    @Test
    public void testStartupHandles(){
        CommonHandle.start(requestModel);
    }

    @Test
    public void testMapperHandles(){
        MapperHandle.start(requestModel);
    }

    @Test
    public void testDaoHandles(){
        DaoHandle.start(requestModel);
    }

    @Test
    public void testEntityHandles(){
        EntityHandle.start(requestModel);
    }

    @Test
    public void testRestHandles(){
        RestHandle.start(requestModel);
    }

    @Test
    public void testServiceHandles(){
        ServiceHandle.start(requestModel);
    }

    @Test
    public void testServiceImplHandles(){
        ServiceImplHandle.start(requestModel);
    }
}
