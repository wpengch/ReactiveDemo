package clonerx;

import clonerx.functions.Action1;
import clonerx.functions.Func1;
import clonerx.plugins.RxJavaObservableExecutionHook;
import rx.plugins.RxJavaPlugins;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 * 实现反应式编程模式的类，可观察的。
 * 该类提供了对Observable的订阅到Observer
 */
public class Observable<T> {
    /**
     * 当Observable.subscribe调用时调用
     * @param <T>
     */
    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {

    }

    /**
     * 对Observable的操作函数
     * @param <R> 泛型 返回类型
     * @param <T> 泛型 类型
     */
    public interface Operator<R,T> extends Func1<Subscriber<? super R>, Subscriber<? super T>>{

    }

    final OnSubscribe<T> onSubscribe;
//    private static final RxJavaObservableExecutionHook hook = RxJavaPlugins

    protected Observable(OnSubscribe<T> f) {
        this.onSubscribe = f;
    }

}
