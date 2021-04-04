package com.wllfengshu.jbot.work;

import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.Table;
import com.wllfengshu.jbot.task.GeneratorCodeTask;
import com.wllfengshu.jbot.utils.StringUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

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
@Configuration
@RequiredArgsConstructor
public class TemplateBoot {

    @NonNull
    private ExecutorService executorService;

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
        List<Runnable> tasks = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(15 + (tables.size() * 7));
        //root
        tasks.add(new GeneratorCodeTask(latch, projectName, data, "Dockerfile", "Dockerfile.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, "pom.xml", "pom.xml.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, "README.md", "README.md.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, "startup.sh", "startup.sh.ftl"));
        //java
        String packageProjectPath = "src/main/java/" + StringUtil.spotToSlash(packageName) + "/" + projectName + "/";
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + "aspect/LogAspect.java", "main/java/aspect/LogAspect.java.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + "configs/DruidConfig.java", "main/java/configs/DruidConfig.java.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + "configs/Swagger2Config.java", "main/java/configs/Swagger2Config.java.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + "exception/CustomException.java", "main/java/exception/CustomException.java.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + "exception/GlobalExceptionHandler.java", "main/java/exception/GlobalExceptionHandler.java.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + "exception/WebResponse.java", "main/java/exception/WebResponse.java.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + "utils/MapperUtil.java", "main/java/utils/MapperUtil.java.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + projectName4FU + "Application.java", "main/java/Application.java.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + "rest/HealthRest.java", "main/java/rest/HealthRest.java.ftl"));
        //resources
        tasks.add(new GeneratorCodeTask(latch, projectName, data, "src/main/resources/application.yml", "main/resources/application.yml.ftl"));
        tasks.add(new GeneratorCodeTask(latch, projectName, data, "src/main/resources/logback.xml", "main/resources/logback.xml.ftl"));
        //table(有多少张表，就循环多少次)
        for (Table table : tables) {
            Map<String, Object> tableData = new HashMap<>(data);
            String tableName4H = StringUtil.underlineToHump(table.getTableName());
            String tableName4FUH = StringUtil.toFirstCharUpperCase(tableName4H);
            String tableName4FLH = StringUtil.toFirstCharLowCase(tableName4H);
            tableData.put("tableName", table.getTableName());
            tableData.put("tableName4FUH", tableName4FUH);
            tableData.put("tableName4FLH", tableName4FLH);
            tableData.put("fields", table.getFields());
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "dao/" + tableName4FUH + "DAO.java", "main/java/dao/DAO.java.ftl"));
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "entity/" + tableName4FUH + "Entity.java", "main/java/entity/Entity.java.ftl"));
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "rest/" + tableName4FUH + "Rest.java", "main/java/rest/Rest.java.ftl"));
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "service/" + tableName4FUH + "Service.java", "main/java/service/Service.java.ftl"));
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "service/impl/" + tableName4FUH + "ServiceImpl.java", "main/java/service/impl/ServiceImpl.java.ftl"));
            //mapper
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, "src/main/resources/mapper/" + tableName4FUH + ".xml", "main/resources/mapper/mapper.xml.ftl"));
            //doc
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, "doc/" + tableName4FUH + "Doc.md", "doc/Doc.md.ftl"));
        }
        tasks.forEach(i -> executorService.execute(i));
        try {
            log.info("任务都已添加完毕");
            latch.await(10, TimeUnit.MINUTES);
            log.info("任务都已执行完毕");
        } catch (Exception e) {
            log.error("等待线程池执行完毕时发生异常", e);
            throw new CustomException("等待线程池执行完毕时发生异常", CustomException.ExceptionName.FAILED_AWAIT_EXECUTOR_SERVICE);
        }
    }
}
