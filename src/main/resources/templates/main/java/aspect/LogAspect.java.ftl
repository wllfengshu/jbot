package ${packageName}.${projectName}.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 日志切面类
 *
 * @author
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 当sql执行时间超过该值时，进行warn级别的打印(单位ms)
     */
    private static final long SQL_WARN_WHEN_OVER_TIME = 5 * 1000;

    /**
     * 当web请求响应时间超过该值时，进行warn级别的打印(单位ms)
     */
    private static final long WEB_WARN_WHEN_OVER_TIME = 60 * 1000;

    private Object process(ProceedingJoinPoint joinPoint,String target,long warnTime) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        if (costTime > warnTime) {
            log.warn("execute "+target+":{} costTime:{} ms", joinPoint.getSignature().getName(), costTime);
        } else {
            log.info("execute "+target+":{} costTime:{} ms", joinPoint.getSignature().getName(), costTime);
        }
        return result;
    }

    /**
     * 打印sql执行的时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     *    切入点解释：
     *         第一个 * 代表任意修饰符及任意返回值
     *         第二个 * 定义在web包或者子包
     *         第三个 * 任意方法
     *         .. 匹配任意数量的参数
     */
    @Around("execution( * ${packageName}.${projectName}.dao.*.*(..))")
    public Object sqlLogExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return process(joinPoint,"sql",SQL_WARN_WHEN_OVER_TIME);
    }

    /**
     * 打印Web请求的执行时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * ${packageName}.${projectName}.rest.*.*(..))")
    public Object webLogExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return process(joinPoint,"request",WEB_WARN_WHEN_OVER_TIME);
    }

}
