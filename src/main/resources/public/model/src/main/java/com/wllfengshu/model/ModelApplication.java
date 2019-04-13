package com.wllfengshu.model;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * springboot启动类
 *
 * @author
 */
@SpringBootApplication
@EnableSwagger2
@EnableAspectJAutoProxy
@MapperScan(value = "com.wllfengshu.model.dao")
public class ModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModelApplication.class, args);
    }

}

