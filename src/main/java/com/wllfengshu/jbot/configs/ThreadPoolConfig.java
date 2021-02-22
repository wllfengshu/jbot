package com.wllfengshu.jbot.configs;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 线程池配置
 *
 * @author wangll
 */
@Component
@RequiredArgsConstructor
public class ThreadPoolConfig {

    @NonNull
    private EnvConfig envConfig;

    @Bean
    public ExecutorService executorService() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("io-thread-%d").build();
        return new ThreadPoolExecutor(Integer.parseInt(envConfig.getJobThreadPoolSize()),
                Integer.parseInt(envConfig.getJobThreadPoolSize()),
                0L,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(256),
                namedThreadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}

