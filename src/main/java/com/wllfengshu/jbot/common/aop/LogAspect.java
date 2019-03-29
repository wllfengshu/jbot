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
     * 打印sql执行的时间
     * @param joinPoint
     * @return
     * @throws Throwable
     * 	  切入点解释：
     *      第一个 * 代表任意修饰符及任意返回值
     *      第二个 * 定义在web包或者子包
     *      第三个 * 任意方法
     *      .. 匹配任意数量的参数
     */
    @Around("execution( * com.wllfengshu.jbot.web.dao.*.*(..))")
    public Object logSqlExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        logger.info("执行dao的方法:{},耗时:{}ms",joinPoint.getSignature().getName(), costTime);
        return result;
    }

    /**
     * 打印Web请求的执行时间
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * com.wllfengshu.jbot.web.rest.*.*(..))")
    public Object webLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        logger.info("请求rest的方法:{},耗时:{}ms",joinPoint.getSignature().getName(),costTime);
        return result;
    }

}
