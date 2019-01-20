
import com.wllfengshu.core.model.TableModel;
import com.wllfengshu.core.utils.*;
import org.junit.Test;


/**
 * 以下以“生表-->课程表（多对多）”为例,对core模块中的utils进行单元测试：
 *      数据库名：school
 *      学生表t_student：姓名sName,学号sNum
 *      课程表t_course：课程名cName,课程编号cNum
 */
public class TestUtils extends TestSuper {

    private static TableModel t = requestModel.getTableModels().get(0);

    @Test
    public void testDaoUtil(){
        String temp=DaoUtil.genDao(t.getTableNameFUDTU(),t.getEntityClassName(),requestModel.getDaoPack());
        System.out.println("文件数据："+temp);
    }

    @Test
    public void testEntityUtil(){
        String temp= EntityUtil.genEntity(t.getTableNameFUDTU(),t.getServiceClassName(),t.getTableInfo());
        System.out.println("文件数据："+temp);
    }

    @Test
    public void testMapperUtil(){
        String temp= MapperUtil.genMapper(t.getDaoClassName(),t.getEntityClassName(),t.getTableInfo());
        System.out.println("文件数据："+temp);
    }

    @Test
    public void testRestUtil(){
        String temp= RestUtil.genRest(t.getTableNameFLDTU(),t.getTableNameFUDTU(),t.getServiceClassName(),t.getEntityClassName(),requestModel.getRestPack());
        System.out.println("文件数据："+temp);
    }

    @Test
    public void testServiceImplUtil(){
        String temp= ServiceImplUtil.genServiceImpl(t.getTableNameFLDTU(),t.getTableNameFUDTU(),t.getDaoClassName(),t.getEntityClassName(),requestModel.getServiceImplPack(),t.getServiceClassName());
        System.out.println("文件数据："+temp);
    }

    @Test
    public void testServiceUtil(){
        String temp= ServiceUtil.genService(t.getTableNameFUDTU(),t.getEntityClassName(),requestModel.getServicePack());
        System.out.println("文件数据："+temp);
    }
}
