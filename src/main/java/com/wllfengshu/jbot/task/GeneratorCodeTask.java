package com.wllfengshu.jbot.task;

import com.wllfengshu.jbot.common.Constant;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 生成代码
 *
 * @author wangll
 */
@Slf4j
public class GeneratorCodeTask implements Runnable{

    private CountDownLatch latch;
    private String projectName;
    private Map<String, Object> data;
    private String path;
    private String templateName;

    public GeneratorCodeTask(CountDownLatch latch, String projectName, Map<String, Object> data, String path, String templateName) {
        this.latch = latch;
        this.projectName = projectName;
        this.data = data;
        this.path = path;
        this.templateName = templateName;
    }

    @Override
    public void run() {
        log.info("开始执行任务 projectName:{},path:{},templateName:{}", projectName, path, templateName);
        try {
            File file = new File(Constant.TARGET_PROJECT_HOME + "/" + projectName + "/" + path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try (Writer out = new FileWriter(file)) {
                Constant.CONFIGURATION.getTemplate(templateName).process(data, out);
                out.flush();
            } catch (Exception e) {
                log.error("生成代码失败", e);
            }
        }catch (Exception e){
            log.error("执行任务过程中发生异常",e);
        }finally {
            latch.countDown();
        }
        log.info("templateName:{} 任务执行完毕", templateName);
    }
}
