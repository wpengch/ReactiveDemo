package clonerx.subscriptions;

import clonerx.Subscription;
import rx.exceptions.Exceptions;

import java.util.*;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 * 代表一组已经退订了的订阅
 */
public final class CompositeSubscription implements Subscription {
    private Set<Subscription> subscriptions;
    private volatile boolean unsubscribed;

    public CompositeSubscription() {
    }

    public CompositeSubscription(final Subscription... subscriptions) {
        this.subscriptions = new HashSet<>(Arrays.asList(subscriptions));
    }

    @Override
    public void unsubscribe() {
        if (!unsubscribed) {
            Collection<Subscription> us = null;
            synchronized (this) {
                if (unsubscribed) {
                    return;
                }
                unsubscribed = true;
                us = subscriptions;
                subscriptions = null;
            }
            unsubscribeFromAll(us);
        }
    }

    @Override
    public boolean isUnsubscrible() {
        return unsubscribed;
    }

    /**
     * 增加一个新的{@link Subscription} 到该组合下面来
     * @param s 增加的{@link Subscription}
     */
    public void add(final Subscription s) {
        if (s.isUnsubscrible()) {
            return;
        }
        if (!unsubscribed) {
            synchronized (this) {
                if (!unsubscribed) {
                    if (subscriptions == null) {
                        subscriptions = new HashSet<>(4);
                    }
                    subscriptions.add(s);
                    return;
                }
            }
        }
        s.unsubscribe();
    }

    /**
     * 移除一个{@link Subscription}
     * @param s 要移除的
     */
    public void remove(final Subscription s) {
        if (!unsubscribed) {
            boolean unsub = false;
            synchronized (this) {
                if (unsubscribed || subscriptions == null) {
                    return;
                }
                unsub = subscriptions.remove(s);
            }
            if (unsub) {
                s.unsubscribe();
            }
        }
    }

    public void clear() {
        if (!unsubscribed) {
            Collection<Subscription> unsubscribes = null;
            synchronized (this) {
                if (unsubscribed || subscriptions == null) {
                    return;
                } else {
                    unsubscribes = subscriptions;
                    subscriptions = null;
                }
            }
            unsubscribeFromAll(unsubscribes);
        }
    }

    private void unsubscribeFromAll(Collection<Subscription> unsubscribes) {
        if (unsubscribes == null || unsubscribes.isEmpty()) {
            return;
        }
        List<Throwable> throwables = null;
        for (Subscription s : unsubscribes) {
            try {
                s.unsubscribe();
            } catch (Throwable throwable) {
                if (throwables == null) {
                    throwables = new ArrayList<>();
                }
                throwables.add(throwable);
            }
        }
        Exceptions.throwIfAny(throwables);
    }
}
