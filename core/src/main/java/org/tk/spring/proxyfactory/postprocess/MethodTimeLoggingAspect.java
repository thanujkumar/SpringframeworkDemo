package org.tk.spring.proxyfactory.postprocess;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class MethodTimeLoggingAspect {
    StopWatch watch = new StopWatch();

    @Around("@annotation(Timed)")
    public Object time(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Timed annotation invoked - " + joinPoint.getSignature().toLongString());
        watch.start();
        Object result = joinPoint.proceed();
        watch.stop();
        System.out.println("Method '" + joinPoint.getSignature().toLongString() + "' took " + watch.getTotalTimeMillis() + "ms");
        return result;
    }
}
