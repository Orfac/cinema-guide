package ru.kinoguide.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around("ru.kinoguide.util.JoinPointConfiguration.serviceLayerExecution()")
    public Object logServiceLayerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info(joinPoint.getSignature() + " (time taken: {})", timeTaken);
        return object;
    }

}
