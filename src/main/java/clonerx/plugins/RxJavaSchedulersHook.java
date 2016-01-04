package clonerx.plugins;

import clonerx.Scheduler;
import clonerx.functions.Action0;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 * 这个插件类提供了2种方式来定制{@link Scheduler}功能
 * 1. 完全重写，if you so choose. 如果这样做，需要重写（io(), computation(), newThread()）这三个方法
 * 2. 在传递到调度器之前，包装一个{@link clonerx.functions.Action0}， 系统提供的调度器,比如：
 * （Schedulers.ioScheduler, Schedulers.computationScheduler,
 * Scheduler.newThreadScheduler），这个方式是比第一种方式简单
 *
 * 当的重定义时，可以选择使用或者不适用onSchedule的钩子
 *
 */
public class RxJavaSchedulersHook {
    protected RxJavaSchedulersHook() {

    }

    private final static RxJavaSchedulersHook DEFAULT_INSTANCE = new RxJavaSchedulersHook();
    public Scheduler getComputationScheduler() {
        return null;
    }

    public Scheduler getIOScheduler() {
        return null;
    }

    public Scheduler getNewThreadScheduler() {
        return null;
    }

    public Action0 onSchedule(Action0 action0) {
        return action0;
    }

    public static RxJavaSchedulersHook getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }
}
