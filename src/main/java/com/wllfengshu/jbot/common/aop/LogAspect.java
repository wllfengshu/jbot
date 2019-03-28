package com.wllfengshu.jbot.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 * @author wllfengshu
 */
@Component
@Aspect
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 当sql执行时间超过该值时，进行warn级别的打印
     */
    private long warnWhenOverTime = 5 * 1000L;

    /**
     * 打印sql执行的时间
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * com.welljoint.query.dao.*.*(..))")
    public Object logSqlExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        if (costTime > warnWhenOverTime) {
            logger.warn("execute sql : {} costTime: [{}] ms",joinPoint.getSignature().getName(), costTime);
        } else {
            logger.info("execute sql : {} costTime: [{}] ms",joinPoint.getSignature().getName(), costTime);
        }
        return result;
    }

    /**
     * 打印Web请求的执行时间
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * com.welljoint.query.rest..*.*(..))")
    public Object webLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        logger.info("request: {} cost: {}",joinPoint.getSignature().getName(),costTime);
        return result;
    }

}
