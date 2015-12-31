package clonerx;

import clonerx.functions.Action0;
import clonerx.subscriptions.MultipleAssignmentSubscription;

import java.util.concurrent.TimeUnit;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 * 安排工作进度的类
 */
public abstract class Scheduler {
    /**
     * 获取或者创建一个工作线程
     * 当工作完成时它应该被退订{@link Scheduler.Worker#unsubscribe()}.
     * @return 该工作保证顺序执行
     */
    public abstract Worker createWorker();

    /**
     * 为单个线程或者事件循环执行动作的连续调度器
     *
     * 退订 {@link Worker} 会退订所有未执行的操作并且清除所有的资源。
     */
    public abstract static class Worker implements Subscription {

        /**
         * 安排一个动作执行时间表
         * @param action 动作
         * @return 订阅
         */
        public abstract Subscription schedule(Action0 action);

        /**
         * 安排一个动作执行时间表，在未来的某个时刻执行
         * @param action 动作
         * @param delayTime 延迟时间
         * @param unit 时间单位
         * @return 订阅
         */
        public abstract Subscription schedule(final Action0 action, final long delayTime, final TimeUnit unit);

        /**
         * 当前时间
         * @return 当前时间
         */
        public long now() {
            return System.currentTimeMillis();
        }

        /**
         * 安排一个定期执行动作
         * @param action 动作
         * @param initDelayTime 初始延迟时间
         * @param period 间隔
         * @param unit 时间单位
         * @return 订阅
         */
        public Subscription schedulePeriodically(final Action0 action, long initDelayTime, long period, TimeUnit unit) {
            final long periodInNanos = unit.toNanos(period);
            final long startInNanos = TimeUnit.MILLISECONDS.toNanos(now()) + unit.toNanos(initDelayTime);

            final MultipleAssignmentSubscription mas = new MultipleAssignmentSubscription();
            final Action0 recursiveAction = new Action0() {
                long count = 0;
                @Override
                public void call() {
                    if (!mas.isUnsubscrible()) {
                        action.call();
                        long nextTick = startInNanos + (++count * periodInNanos);
                        mas.set(schedule(this, nextTick - TimeUnit.MICROSECONDS.toNanos(now()), TimeUnit.NANOSECONDS));
                    }
                }
            };
            MultipleAssignmentSubscription s = new MultipleAssignmentSubscription();
            mas.set(s);
            s.set(schedule(recursiveAction, initDelayTime, unit));
            return mas;
        }
    }

    /**
     * 当前时间
     * @return 返回当前worker的当前时间
     */
    public long now() {
        return System.currentTimeMillis();
    }
}
