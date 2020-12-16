package com.wllfengshu.jbot.work;

import com.wllfengshu.jbot.common.Constant;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.Table;
import com.wllfengshu.jbot.utils.FileUtil;
import com.wllfengshu.jbot.utils.StringUtil;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理模板文件
 * 注：文中涉及到单词缩写，其含义如下：
 * F = first 第一个
 * U = upper case 大写
 * L = lower case 小写
 * H = hump 驼峰
 * Ul = underline 下划线
 *
 * @author wllfengshu
 */
@Slf4j
@Component
public class TemplateBoot {

    private static final freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_22);

    static {
        log.info("正在加载模板文件");
        //1 尝试本地直接查找模板文件所在目录
        File templateDir = new File(Constant.TEMPLATE_PATH);
        //2 尝试使用getResource方式查找
        if (!FileUtil.isExistsAndDir(templateDir)) {
            try {
                log.info("本地直接查找-失败");
                String url = TemplateBoot.class.getClassLoader().getResource(Constant.TEMPLATE_NAME).getPath();
                templateDir = new File(URLDecoder.decode(url, "UTF-8"));
            } catch (Exception e) {
                log.error("尝试使用getResource方式查找-发生异常", e);
            }
        }
        //2 尝试使用jar包方式查找
        if (!FileUtil.isExistsAndDir(templateDir)) {
            try {
                log.info("使用getResource方式查找-失败");
                ClassPathResource resource = new ClassPathResource(Constant.TEMPLATE_PATH);
                if (null != resource && resource.exists()) {
                    templateDir = resource.getFile();
                } else {
                    log.error("尝试使用jar包方式查找-失败");
                }
            } catch (Exception e) {
                log.error("尝试使用jar包方式查找-发生异常", e);
            }
        }
        //4 全部方式都无法加载，只能退出程序
        if (FileUtil.isExistsAndDir(templateDir)) {
            try {
                configuration.setDirectoryForTemplateLoading(templateDir);
                configuration.setDefaultEncoding("UTF-8");
                configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            } catch (Exception e) {
                log.error("创建template配置错误", e);
            }
        } else {
            log.error("尝试了所有方法都无法加载模板文件，程序退出");
            System.exit(-1);
        }
        log.info("模板文件加载完毕");
    }

    public void start(String projectName, String packageName, List<Table> tables) throws CustomException {
        //1 准备数据
        Map<String, Object> data = new HashMap<>();
        //存入StringUtil类后，就可以在ftl文件中直接调用其静态方法
        data.put("stringUtil", new StringUtil());
        //project
        String projectName4FU = StringUtil.toFirstCharUpperCase(projectName);
        data.put("projectName", projectName);
        data.put("projectName4FU", projectName4FU);
        //package
        data.put("packageName", packageName);
        //2 生成代码
        //root
        generatorCode(projectName, data, "Dockerfile", "Dockerfile.ftl");
        generatorCode(projectName, data, "pom.xml", "pom.xml.ftl");
        generatorCode(projectName, data, "README.md", "README.md.ftl");
        generatorCode(projectName, data, "startup.sh", "startup.sh.ftl");
        //java
        String packageProjectPath = "src/main/java/" + StringUtil.spotToSlash(packageName) + "/" + projectName + "/";
        generatorCode(projectName, data, packageProjectPath + "aspect/LogAspect.java", "main/aspect/LogAspect.java.ftl");
        generatorCode(projectName, data, packageProjectPath + "configs/DruidConfig.java", "main/configs/DruidConfig.java.ftl");
        generatorCode(projectName, data, packageProjectPath + "configs/Swagger2Config.java", "main/configs/Swagger2Config.java.ftl");
        generatorCode(projectName, data, packageProjectPath + "exception/CustomException.java", "main/exception/CustomException.java.ftl");
        generatorCode(projectName, data, packageProjectPath + "exception/GlobalExceptionHandler.java", "main/exception/GlobalExceptionHandler.java.ftl");
        generatorCode(projectName, data, packageProjectPath + "exception/WebResponse.java", "main/exception/WebResponse.java.ftl");
        generatorCode(projectName, data, packageProjectPath + "utils/MapperUtil.java", "main/utils/MapperUtil.java.ftl");
        generatorCode(projectName, data, packageProjectPath + projectName4FU + "Application.java", "main/Application.java.ftl");
        generatorCode(projectName, data, packageProjectPath + "rest/HealthRest.java", "main/rest/HealthRest.java.ftl");
        //resources
        generatorCode(projectName, data, "src/main/resources/application.yml", "main/resources/application.yml.ftl");
        generatorCode(projectName, data, "src/main/resources/logback.xml", "main/resources/logback.xml.ftl");
        //table
        for (Table table : tables) {
            String tableName4H = StringUtil.underlineToHump(table.getTableName());
            String tableName4FUH = StringUtil.toFirstCharUpperCase(tableName4H);
            String tableName4FLH = StringUtil.toFirstCharLowCase(tableName4H);
            data.put("tableName", table.getTableName());
            data.put("tableName4FUH", tableName4FUH);
            data.put("tableName4FLH", tableName4FLH);
            data.put("fields", table.getFields());
            generatorCode(projectName, data, packageProjectPath + "dao/" + tableName4FUH + "DAO.java", "main/dao/DAO.java.ftl");
            generatorCode(projectName, data, packageProjectPath + "entity/" + tableName4FUH + "Entity.java", "main/entity/Entity.java.ftl");
            generatorCode(projectName, data, packageProjectPath + "rest/" + tableName4FUH + "Rest.java", "main/rest/Rest.java.ftl");
            generatorCode(projectName, data, packageProjectPath + "service/" + tableName4FUH + "Service.java", "main/service/Service.java.ftl");
            generatorCode(projectName, data, packageProjectPath + "service/impl/" + tableName4FUH + "ServiceImpl.java", "main/service/impl/ServiceImpl.java.ftl");
            //mapper
            generatorCode(projectName, data, "src/main/resources/mapper/" + tableName4FUH + ".xml", "main/resources/mapper/mapper.xml.ftl");
            //doc
            generatorCode(projectName, data, "doc/" + tableName4FUH + "Doc.md", "doc/Doc.md.ftl");
        }
    }

    /**
     * 生成代码
     *
     * @param projectName  项目名
     * @param data         数据
     * @param path         生成路径
     * @param templateName 模板文件名
     * @throws Exception
     */
    public void generatorCode(String projectName, Map<String, Object> data, String path, String templateName) {
        Writer out = null;
        try {
            File file = new File(Constant.TARGET_PROJECT_HOME + "/" + projectName + "/" + path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            out = new FileWriter(file);
            configuration.getTemplate(templateName).process(data, out);
        } catch (Exception e) {
            log.error("生成代码失败", e);
        } finally {
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
