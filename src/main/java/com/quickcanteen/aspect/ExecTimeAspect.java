package com.quickcanteen.aspect;

import com.google.common.base.Joiner;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;


@Aspect
@Component
public class ExecTimeAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExecTimeAspect.class);

    @Pointcut("@annotation(com.quickcanteen.annotation.ExecTime)")
    public void annotationPointCut() {
    }

    @Pointcut("execution(public * com.quickcanteen.mapper..*.*(..))")
    public void mapperTime() {
    }

    /**
     * 统计方法执行的时长
     *
     * @param joinPoint the join point
     * @return object
     */
    @Around("annotationPointCut()")
    public Object costTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return logCostTime(joinPoint);
    }

    @Around("mapperTime()")
    public Object mapperCostTime(ProceedingJoinPoint joinPoint) throws Throwable {
        return logCostTime(joinPoint);
    }

    private Object logCostTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Object output = null;
        try {
            long start = System.currentTimeMillis();
            output = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String args = Joiner.on(",").join(joinPoint.getArgs());
            Optional<Class<?>> realClass = Arrays.stream(joinPoint.getTarget().getClass().getInterfaces()).findFirst();
            if (elapsedTime > 500) {
                logger.warn(String.format("method [%s.%s(%s)] execution time:%sms", realClass.isPresent() ? realClass.get().getSimpleName() : className, joinPoint.getSignature().getName(), args, elapsedTime));
            }
        } catch (Throwable throwable) {
            logger.error("aop record method exec time error", throwable);
            //throw throwable;
        }
        return output;
    }
}
