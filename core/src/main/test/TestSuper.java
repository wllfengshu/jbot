import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.common.entity.FieldInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.core.before.BeforeHandle;
import com.wllfengshu.core.model.RequestModel;
import org.junit.BeforeClass;

import java.util.ArrayList;

/**
 * 测试类的父类
 */
public class TestSuper {

    private static String projectName = "exam";
    private static String packageName = "com.tiandixuanwu";
    private static DBInfo dbInfo = null;

    protected static RequestModel requestModel = null;

    @BeforeClass
    public static void before(){
        dbInfo = new DBInfo();
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

        //创建请求model
        requestModel = BeforeHandle.start(projectName,packageName,dbInfo);

    }
}
