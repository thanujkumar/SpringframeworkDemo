package org.tk.spring.scope;

public class ThreadAnnouncer {
    private String threadName;

    public ThreadAnnouncer() {
        threadName = Thread.currentThread().getName();
    }

    public void announce() {
        System.out.println(this + " -- Scope('thread') -- " + this.threadName);
    }
}
