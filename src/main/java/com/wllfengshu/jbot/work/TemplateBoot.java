package com.wllfengshu.jbot.work;

import com.wllfengshu.jbot.common.Constant;
import com.wllfengshu.jbot.exception.CustomException;
import com.wllfengshu.jbot.model.Table;
import com.wllfengshu.jbot.task.GeneratorCodeTask;
import com.wllfengshu.jbot.utils.FileUtil;
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
        Map<String, Object> data = new HashMap<>(16);
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
        final CountDownLatch latch = new CountDownLatch(15 + (tables.size() * 7));//15写死了！！！
        //root
        List<String> listRoot = FileUtil.giveFileName4Dir(Constant.TEMPLATE_NAME);
        listRoot.forEach(i -> tasks.add(new GeneratorCodeTask(latch, projectName, data, StringUtil.splitFtlSuffix(i), i)));
        //java
        final String packageProjectPath = Constant.SRC_MAIN_JAVA_PATH2 + StringUtil.spotToSlash(packageName) + "/" + projectName + "/";
        List<String> listJava = FileUtil.giveFileName4DirNeedChild(Constant.TEMPLATE_NAME + Constant.SRC_MAIN_JAVA_PATH);
        listJava.forEach(i -> {
            //需要单独处理启动类
            if ("Application.java.ftl".equals(i)){
                tasks.add(new GeneratorCodeTask(latch, projectName, data, packageProjectPath + projectName4FU + "Application.java", Constant.SRC_MAIN_JAVA_PATH2 + i));
            }else {
                tasks.add(new GeneratorCodeTask(latch, projectName, data, StringUtil.splitFtlSuffix(packageProjectPath + i), Constant.SRC_MAIN_JAVA_PATH2 + i));
            }
        });
        //resources
        List<String> listResources = FileUtil.giveFileName4Dir(Constant.TEMPLATE_NAME + Constant.SRC_MAIN_RESOURCES_PATH);
        listResources.forEach(i -> tasks.add(new GeneratorCodeTask(latch, projectName, data, StringUtil.splitFtlSuffix(Constant.SRC_MAIN_RESOURCES_PATH2 + i), Constant.SRC_MAIN_RESOURCES_PATH2 + i)));
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
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "dao/" + tableName4FUH + "DAO.java", "src/main/java/dao/DAO.java.ftl"));
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "entity/" + tableName4FUH + "Entity.java", "src/main/java/entity/Entity.java.ftl"));
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "rest/" + tableName4FUH + "Rest.java", "src/main/java/rest/Rest.java.ftl"));
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "service/" + tableName4FUH + "Service.java", "src/main/java/service/Service.java.ftl"));
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, packageProjectPath + "service/impl/" + tableName4FUH + "ServiceImpl.java", "src/main/java/service/impl/ServiceImpl.java.ftl"));
            //mapper
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, "src/main/resources/mapper/" + tableName4FUH + ".xml", "src/main/resources/mapper/mapper.xml.ftl"));
            //doc
            tasks.add(new GeneratorCodeTask(latch, projectName, tableData, "doc/" + tableName4FUH + "Doc.md", "doc/Doc.md.ftl"));
        }
        tasks.forEach(i -> executorService.execute(i));
        try {
            log.info("任务都已添加完毕");
            boolean await = latch.await(10, TimeUnit.MINUTES);
            if (await){
                log.debug("任务全部执行完毕");
            }else {
                log.warn("任务部分执行完毕");
            }
        } catch (Exception e) {
            log.error("等待线程池执行完毕时发生异常", e);
            throw new CustomException("等待线程池执行完毕时发生异常", CustomException.ExceptionName.FAILED_AWAIT_EXECUTOR_SERVICE);
        }
    }
}
