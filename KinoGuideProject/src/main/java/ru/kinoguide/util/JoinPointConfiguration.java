package ru.kinoguide.util;

import org.aspectj.lang.annotation.Pointcut;

public class JoinPointConfiguration {

    @Pointcut("execution(* ru.kinoguide.service.*.*(..))")
    public void serviceLayerExecution() {
    }
}
