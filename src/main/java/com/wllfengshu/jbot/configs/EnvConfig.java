package com.wllfengshu.jbot.configs;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务环境的统一配置，服务中使用的环境变量都要在此类中定义
 *
 * @author wangll
 */
@Slf4j
@Data
@Order(0)
@Component
public class EnvConfig implements InitializingBean {

    /**
     * 服务端数据库配置
     */
    @Value("${db_url:jdbc:mysql://127.0.0.1:3306/test}")
    private String dbUrl;
    @Value("${db_username:root}")
    private String dbUsername;
    @Value("${db_password:root}")
    private String dbPassword;

    /**
     * 定时任务线程池大小
     */
    @Value("${job_thread_pool_size:4}")
    private String jobThreadPoolSize;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("程序环境变量:{}", this.toString());
        log.info("系统环境变量:{}", JSON.toJSONString(System.getenv()));
    }
}

