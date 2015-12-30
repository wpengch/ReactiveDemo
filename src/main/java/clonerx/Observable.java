package clonerx;

import clonerx.functions.Action1;

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

    final OnSubscribe<T> onSubscribe;

    protected Observable(OnSubscribe<T> f) {
        this.onSubscribe = f;
    }

}
