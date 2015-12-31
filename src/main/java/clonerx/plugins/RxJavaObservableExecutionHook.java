package clonerx.plugins;

import clonerx.Observable;
import clonerx.Observable.OnSubscribe;
import clonerx.Observable.Operator;
import clonerx.Subscription;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 * 虚拟执行钩子，Observable不同生命周期的默认调用实现
 * 注意线程安全以及性能
 * 该类的一个简单实现将会在全局调用，所以该类中的方法是同步的，因此所有类中的方法必须是线程安全的。
 *
 * 方法是同步被调用并且会加入到Observable的执行周期，以便所有的行为能够得到快速反应。如果有耗时的行为，必须将其移入单独的线程中去。
 *
 */
public abstract class RxJavaObservableExecutionHook {
    /**
     * 外观模式，可以对原OnSubscribe进行各种操作
     * @param f 原始OnSubscribe
     * @param <T> 类型
     * @return 修饰过的
     */
    public <T> OnSubscribe<T> onCreate(OnSubscribe<T> f) {
        return f;
    }

    /**
     * 外观模式，可以对原OnSubscribe进行各种操作
     * @param observableInstance OnSubscribe实例
     * @param onSubscribe 原始OnSubscribe
     * @param <T> 类型
     * @return 修饰过的
     */
    public <T> OnSubscribe<T> onSubscribeStart(Observable<? extends T> observableInstance, final OnSubscribe<T> onSubscribe) {
        return onSubscribe;
    }

    public <T> Subscription onSubscriptionReturn(Subscription subscription) {
        return subscription;
    }

    public <T> Throwable onSubscribeError(Throwable throwable) {
        return throwable;
    }

    public <T, R> Operator<? extends R, ? super T> onLift(final Operator<? extends R, ? super T> lift) {
        return lift;
    }
}
