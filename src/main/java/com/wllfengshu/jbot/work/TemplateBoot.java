package com.wllfengshu.jbot.work;

import com.wllfengshu.jbot.common.Constant;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.Table;
import com.wllfengshu.jbot.utils.StringUtil;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理模板文件
 *    注：文中涉及到单词缩写，其含义如下：
 *    F = first 第一个
 *    U = upper case 大写
 *    L = lower case 小写
 *    H = hump 驼峰
 *    Ul = underline 下划线
 *
 * @author wllfengshu
 */
@Slf4j
public class TemplateBoot {

    private String projectName;
    private String packageName;
    private List<Table> tables;
    private static final freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_22);

    static {
        try {
            configuration.setDirectoryForTemplateLoading(new File(Constant.TEMPLATE_PATH));
            configuration.setDefaultEncoding("UTF-8");
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        }catch (Exception e){
            log.error("创建template配置错误",e);
        }
    }

    public TemplateBoot(String projectName, String packageName,List<Table> tables){
        this.projectName = projectName;
        this.packageName = packageName;
        this.tables = tables;
    }

    public void start()throws CustomException{
        //1 准备数据
        Map<String,Object> data = new HashMap<>();
        //存入StringUtil类后，就可以在ftl文件中直接调用其静态方法
        data.put("stringUtil",new StringUtil());
        //project
        String projectName4FU = StringUtil.toFirstCharUpperCase(projectName);
        data.put("projectName",projectName);
        data.put("projectName4FU",projectName4FU);
        //package
        data.put("packageName",packageName);
        //2 生成代码
        //root
        generatorCode(data,"Dockerfile","Dockerfile.ftl");
        generatorCode(data,"pom.xml","pom.xml.ftl");
        generatorCode(data,"README.md","README.md.ftl");
        generatorCode(data,"startup.sh","startup.sh.ftl");
        //java
        String packageProjectPath = "src/main/java/" + StringUtil.spotToSlash(packageName) + "/" + projectName + "/";
        generatorCode(data,packageProjectPath + "aop/LogAspect.java","main/aop/LogAspect.java.ftl");
        generatorCode(data,packageProjectPath + "config/DruidConfig.java","main/configs/DruidConfig.java.ftl");
        generatorCode(data,packageProjectPath + "config/Swagger2Config.java","main/configs/Swagger2Config.java.ftl");
        generatorCode(data,packageProjectPath + "exception/CustomException.java","main/exception/CustomException.java.ftl");
        generatorCode(data,packageProjectPath + "exception/GlobalExceptionHandler.java","main/exception/GlobalExceptionHandler.java.ftl");
        generatorCode(data,packageProjectPath + "exception/WebResponse.java","main/exception/WebResponse.java.ftl");
        generatorCode(data,packageProjectPath + "utils/MyMapper.java","main/utils/MyMapper.java.ftl");
        generatorCode(data,packageProjectPath + projectName4FU + "Application.java","main/Application.java.ftl");
        //resources
        generatorCode(data,"src/main/resources/application.yml","main/resources/application.yml.ftl");
        generatorCode(data,"src/main/resources/logback.xml","main/resources/logback.xml.ftl");
        //table
        for (Table table:tables){
            String tableName4H = StringUtil.underlineToHump(table.getTableName());
            String tableName4FUH = StringUtil.toFirstCharUpperCase(tableName4H);
            String tableName4FLH = StringUtil.toFirstCharLowCase(tableName4H);
            data.put("tableName",table.getTableName());
            data.put("tableName4FUH",tableName4FUH);
            data.put("tableName4FLH",tableName4FLH);
            data.put("fields",table.getFields());
            generatorCode(data,packageProjectPath + "dao/"+ tableName4FUH +"DAO.java","main/dao/DAO.java.ftl");
            generatorCode(data,packageProjectPath + "entity/"+ tableName4FUH +"Entity.java","main/entity/Entity.java.ftl");
            generatorCode(data,packageProjectPath + "rest/"+ tableName4FUH +"Rest.java","main/rest/Rest.java.ftl");
            generatorCode(data,packageProjectPath + "service/"+ tableName4FUH +"Service.java","main/service/Service.java.ftl");
            generatorCode(data,packageProjectPath + "service/impl/"+ tableName4FUH +"ServiceImpl.java","main/service/impl/ServiceImpl.java.ftl");
            //mapper
            generatorCode(data,"src/main/resources/mapper/" + tableName4FUH +".xml","main/resources/mapper/mapper.xml.ftl");
            //doc
            generatorCode(data,"doc/" + tableName4FUH +"Doc.md","doc/Doc.md.ftl");
        }
    }

    /**
     * 生成代码
     * @param data 数据
     * @param path 生成路径
     * @param templateName 模板文件名
     * @throws Exception
     */
    public void generatorCode(Map<String, Object> data, String path, String templateName){
        Writer out = null;
        try {
            File file = new File(Constant.TARGET_PROJECT_HOME + "/" + projectName + "/" + path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            out = new FileWriter(file);
            configuration.getTemplate(templateName).process(data,out);
        } catch (Exception e) {
            log.error("生成代码失败",e);
        }finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                log.error("生成代码失败 finally", e);
            }
        }
    }

}
