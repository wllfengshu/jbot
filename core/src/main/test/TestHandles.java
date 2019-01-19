
import com.wllfengshu.core.after.AfterHandle;
import com.wllfengshu.core.work.DockerfileHandle;
import com.wllfengshu.core.work.PomHandle;
import com.wllfengshu.core.work.ReadmeHandle;
import com.wllfengshu.core.work.StartupHandle;
import com.wllfengshu.core.work.javaHandle.*;
import com.wllfengshu.core.work.resourcesHandle.LogbackHandle;
import com.wllfengshu.core.work.resourcesHandle.MapperHandle;
import com.wllfengshu.core.work.resourcesHandle.PropertiesHandle;
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
        StartupHandle.start(requestModel);
    }

    @Test
    public void testReadmeHandles(){
        ReadmeHandle.start(requestModel);
    }

    @Test
    public void testPomHandles(){
        PomHandle.start(requestModel);
    }

    @Test
    public void testDockerfileHandles(){
        DockerfileHandle.start(requestModel);
    }

    @Test
    public void testPropertiesHandles(){
        PropertiesHandle.start(requestModel);
    }

    @Test
    public void testLogbackHandles(){
        LogbackHandle.start(requestModel);
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
