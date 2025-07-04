//package org.tk.spring.ehcache;
//
//import org.ehcache.event.CacheEvent;
//import org.ehcache.event.CacheEventListener;
//
//public class EhCacheEventLogger implements CacheEventListener<Object, Object> {
//
//    @Override
//    public void onEvent(CacheEvent<?, ?> cacheEvent) {
//        System.out.printf("Cache event with key %s  %s, old value %s and new value %s \n", cacheEvent.getKey(), cacheEvent.getType(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
//    }
//}
