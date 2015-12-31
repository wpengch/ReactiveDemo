package clonerx.subscriptions;

import clonerx.Subscription;
import clonerx.functions.Action0;

import java.util.concurrent.Future;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 * Subscription的工具函数
 */
public final class Subscriptions {
    private Subscriptions() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription empty(){
        return BooleanSubscription.create();
    }

    public static Subscription unsubscribed() {
        return UNSUBSCRIBED;
    }

    public static Subscription create(final Action0 unsubscribe) {
        return BooleanSubscription.create(unsubscribe);
    }

    /**
     * 将{@link Future} 转换成{@link Subscription} 并且当取消订阅的时候 取消{@link Future}
     * @param f 待转换的Future
     * @return 包装了{@link Future} 的 {@link Subscription}
     */
    public static Subscription from(final Future<?> f) {
        return new FutureSubscription(f);
    }

    private static final class FutureSubscription implements Subscription {
        final Future<?> f;

        public FutureSubscription(Future<?> f) {
            this.f = f;
        }

        @Override
        public void unsubscribe() {
            f.cancel(true);
        }

        @Override
        public boolean isUnsubscrible() {
            return f.isCancelled();
        }
    }


    public static CompositeSubscription from(Subscription... subscriptions) {
        return new CompositeSubscription(subscriptions);
    }

    private static final Unsubscribed UNSUBSCRIBED = new Unsubscribed();
    private static final class Unsubscribed implements Subscription {
        @Override
        public void unsubscribe() {

        }

        @Override
        public boolean isUnsubscrible() {
            return true;
        }
    }

}
