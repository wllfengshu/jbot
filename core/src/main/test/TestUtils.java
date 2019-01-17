import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.common.entity.FieldInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.core.utils.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * 以下以“生表-->课程表（多对多）”为例进行单元测试：
 *      数据库名：school
 *      学生表t_student：姓名sName,学号sNum
 *      课程表t_course：课程名cName,课程编号cNum
 */
public class TestUtils {

    private static String projectName = "exam";
    private static String packageName = "com.tiandixuanwu";
    private static DBInfo dbInfo = new DBInfo();

    @BeforeClass
    public static void befor(){
        dbInfo.setDbName("school");
        ArrayList<TableInfo> tables = new ArrayList<>();

        //student表
        TableInfo student = new TableInfo();
        student.setTableName("t_student");
        ArrayList<FieldInfo> s_fields = new ArrayList<>();
        FieldInfo s_name = new FieldInfo();
        s_name.setFieldName("sName");
        s_name.setFieldType("String");
        s_fields.add(s_name);
        FieldInfo s_num = new FieldInfo();
        s_num.setFieldName("sNum");
        s_num.setFieldType("int");
        s_fields.add(s_num);
        student.setFields(s_fields);
        tables.add(student);

        //course表
        TableInfo course = new TableInfo();
        course.setTableName("t_course");
        ArrayList<FieldInfo> c_fields = new ArrayList<>();
        FieldInfo c_name = new FieldInfo();
        c_name.setFieldName("cName");
        c_name.setFieldType("String");
        c_fields.add(c_name);
        FieldInfo c_num = new FieldInfo();
        c_num.setFieldName("cNum");
        c_num.setFieldType("int");
        c_fields.add(c_num);
        course.setFields(c_fields);
        tables.add(course);

        dbInfo.setTables(tables);
        System.out.println("初始数据库数据："+dbInfo.toString());
    }

    @Test
    public void testDaoUtil(){
        String temp=DaoUtil.genDao(projectName,packageName,dbInfo.getTables().get(0));
        System.out.println("文件数据："+temp);
    }

//    @Test
//    public void testEntityUtil(){
//        String temp= EntityUtil.genEntity(projectName, packageName, dbInfo.getTables().get(0));
//        System.out.println("文件数据："+temp);
//    }

    @Test
    public void testMapperUtil(){
        String temp= MapperUtil.genMapper(projectName, packageName, dbInfo.getTables().get(0));
        System.out.println("文件数据："+temp);
    }

    @Test
    public void testRestUtil(){
        String temp= RestUtil.genRest(projectName, packageName, dbInfo.getTables().get(0));
        System.out.println("文件数据："+temp);
    }

    @Test
    public void testServiceImplUtil(){
        String temp= ServiceImplUtil.genServiceImpl(projectName, packageName, dbInfo.getTables().get(0));
        System.out.println("文件数据："+temp);
    }

    @Test
    public void testServiceUtil(){
        String temp= ServiceUtil.genService(projectName, packageName, dbInfo.getTables().get(0));
        System.out.println("文件数据："+temp);
    }
}
