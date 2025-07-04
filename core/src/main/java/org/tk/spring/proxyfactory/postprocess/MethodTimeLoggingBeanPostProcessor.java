package org.tk.spring.proxyfactory.postprocess;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class MethodTimeLoggingBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ProxyFactory factory = new ProxyFactory();
        factory.addAdvice(new MethodTimedInterceptor());
        factory.setTarget(bean);
        return factory.getProxy();
    }

    private class MethodTimedInterceptor implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            //Will be called for class and also for method invocation
            System.out.println("----invocation method interceptor ------" + invocation.getMethod().getName());
            Object result = null;
            if (invocation.getMethod().getAnnotation(Timed.class) != null) {
                StopWatch watch = new StopWatch();
                watch.start();
                result = invocation.proceed();
                watch.stop();
                System.out.println("Timed - "+ watch.toString());
            } else {
                result = invocation.proceed();
            }
            return result;
        }
    }
}
