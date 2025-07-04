package org.tk.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    //@Pointcut("execution(* org.tk.spring.aop.*.*(..))")
    @Pointcut("execution(* org.tk.spring.aop.ObjectUnderAop.*(..))")
    private void allMethods(){} //join point

    //Apply the pointcut with advice
    @Around("allMethods()")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        String signature = proceedingJoinPoint.getSignature().toLongString();
        Object returnValue = proceedingJoinPoint.proceed(args);
        System.out.println("Method '"+signature+"' was called with arguments " + Arrays.asList(args));
        return  returnValue;
    }
}
