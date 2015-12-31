package clonerx.subscriptions;

import clonerx.Subscription;
import clonerx.functions.Action;
import clonerx.functions.Action0;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 * 可以检查状态的订阅，比如在一个{@link clonerx.Observable}的循环内部，如果取消订阅，则退出循环
 */
public final class BooleanSubscription implements Subscription {
    private static final Action0 EMPTY_ACTION = new Action0() {
        @Override
        public void call() {

        }
    };
    final AtomicReference<Action0> actionRef;
    public BooleanSubscription() {
        actionRef = new AtomicReference<>();
    }

    private BooleanSubscription(Action0 action) {
        actionRef = new AtomicReference<>(action);
    }

    public static BooleanSubscription create() {
        return new BooleanSubscription();
    }

    public static BooleanSubscription create(Action0 onUnsubscribe) {
        return new BooleanSubscription(onUnsubscribe);
    }


    @Override
    public void unsubscribe() {
        Action0 action0 = actionRef.get();
        if (action0 != EMPTY_ACTION) {
            action0 = actionRef.getAndSet(EMPTY_ACTION);
            if (action0 != null && action0 != EMPTY_ACTION) {
                action0.call();
            }
        }
    }

    @Override
    public boolean isUnsubscrible() {
        return actionRef.get() == EMPTY_ACTION;
    }
}
