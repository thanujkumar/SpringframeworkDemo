package org.tk.spring.scope;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.IntStream;

@Component
public class ThreadLauncher implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void launch() {
        IntStream.range(1, 10).forEach(x ->
                taskExecutor.execute(new ThreadAnnouncerRunnable(this.applicationContext)));

    }

    public static class ThreadAnnouncerRunnable implements Runnable {

        private ApplicationContext appContext;

        public ThreadAnnouncerRunnable(ApplicationContext applicationContext) {
            this.appContext = applicationContext;
        }

        @Override
        public void run() {
            System.out.println("Starting thread run for caller thread -- " + Thread.currentThread().getName());

            //Fetch the same bean twice to check whether same instance of ThreadAnnouncer is given
            appContext.getBean(ThreadAnnouncer.class).announce();
            appContext.getBean(ThreadAnnouncer.class).announce();
        }
    }
}
