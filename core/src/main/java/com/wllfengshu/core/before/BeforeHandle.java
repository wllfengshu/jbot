package com.wllfengshu.core.before;

import com.wllfengshu.common.constant.Collective;
import com.wllfengshu.common.entity.DBInfo;
import com.wllfengshu.common.entity.TableInfo;
import com.wllfengshu.common.utils.FileUtil;
import com.wllfengshu.common.utils.StringUtil;
import com.wllfengshu.core.model.RequestModel;
import com.wllfengshu.core.model.TableModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 负责core开始前的准备工作
 * @author wllfengshu
 */
public class BeforeHandle {

    public static RequestModel start(String projectName, String packageName, DBInfo dbInfo){
        //0、建立请求的模型
        RequestModel model=buildRequestModel(projectName, packageName, dbInfo);
        //1、创建项目名
        createProjectName(model.getProjectPath());
        //2、创建包
        createPackageName(model.getPackageBasePath());
        //3、复制pom、readme等文件
        copyConfFile(model.getProjectPath());
        //4、复制resources文件夹
        copyResource(model.getResourcesPath());
        //5、删除resources/mapper目录里的xml文件
        deleteFile(model.getResourcesPath());
        return model;
    }

    private static RequestModel buildRequestModel(String projectName, String packageName, DBInfo dbInfo){
        RequestModel model = new RequestModel();
        model.setProjectName(projectName);
        model.setPackageName(packageName);
        model.setProjectPath(Collective.TARGET_PROJECT_HOME+"/"+projectName);
        model.setJavaPath(model.getProjectPath()+"/src/main/java");
        model.setResourcesPath(model.getProjectPath()+"/src/main/resources");
        model.setPackageBasePath(model.getJavaPath()+"/"+StringUtil.spotToSlash(packageName)+"/"+projectName);
        model.setRestPack(packageName+"."+projectName+".rest");
        model.setEntityPack(packageName+"."+projectName+".entity");
        model.setDaoPack(packageName+"."+projectName+".dao");
        model.setServicePack(packageName+"."+projectName+".service");
        model.setServiceImplPack(packageName+"."+projectName+".service.impl");
        //处理table信息
        List<TableModel> tables = new ArrayList<>();
        for(TableInfo tableInfo:dbInfo.getTables()){
            TableModel table = new TableModel();
            table.setTableNameFUDTU(StringUtil.toFirstCharUpperCase(StringUtil.underlineToHump(tableInfo.getTableName())));
            table.setTableNameFLDTU(StringUtil.toFirstCharLowCase(StringUtil.underlineToHump(tableInfo.getTableName())));
            table.setEntityClassName(model.getEntityPack()+"."+table.getTableNameFUDTU());
            table.setDaoClassName(model.getDaoPack()+"."+table.getTableNameFUDTU()+"Dao");
            table.setServiceClassName(model.getServicePack()+"."+table.getTableNameFUDTU()+"Service");
            table.setServiceImplClassName(model.getServiceImplPack()+"."+table.getTableNameFUDTU()+"ServiceImpl");
            table.setTableInfo(tableInfo);
            tables.add(table);
        }
        model.setTableModels(tables);
        System.out.println("数据信息已经打包完毕，准备生成项目，数据如下："+model);
        return model;
    }

    private static void createProjectName(String projectPath){
        FileUtil.createDir(projectPath);
    }

    private static void createPackageName(String packageBasePath){
        FileUtil.createDir(packageBasePath);
    }

    private static void copyConfFile(String projectPath){
        String[] fileName={"pom.xml","README.md"};
        for (String f:fileName) {
            FileUtil.copyFile(Collective.MODEL_PROJECT_HOME+"/"+f,projectPath+"/"+f);
        }
    }

    private static void copyResource(String resourcesPath){
        FileUtil.copyDir(Collective.MODEL_PROJECT_HOME_RESOURCES,resourcesPath);
    }

    private static void deleteFile(String resourcesPath){
        FileUtil.deleteFile(resourcesPath+"/mapper/user.xml");
    }

}
