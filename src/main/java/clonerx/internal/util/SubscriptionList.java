package clonerx.internal.util;

import clonerx.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 */
public class SubscriptionList implements Subscription {

    /**
     * 点阅链表
     */
    private LinkedList<Subscription> subscriptions;
    /**
     * 订阅是否诗经注销
     */
    private volatile boolean unsubscribled;


    public SubscriptionList() {

    }

    public SubscriptionList(final Subscription... subscriptions) {
        this.subscriptions = new LinkedList<>(Arrays.asList(subscriptions));
    }

    public SubscriptionList(Subscription subscription) {
        this.subscriptions = new LinkedList<>();
        this.subscriptions.add(subscription);
    }


    @Override
    public void unsubscribe() {
        if (!unsubscribled) {
            List<Subscription> list;
            synchronized (this) {
                if (unsubscribled) {
                    return;
                }
                unsubscribled = true;
                list = subscriptions;
                subscriptions = null;
            }
            unsubscribeFromAll(list);
        }
    }

    private void unsubscribeFromAll(List<Subscription> list) {
        if (subscriptions == null) {
            return;
        }
        List<Throwable> es = null;
        for (Subscription subscription : subscriptions) {
            try {
                subscription.unsubscribe();
            } catch (Throwable e) {
                if (es == null) {
                    es = new ArrayList<>();
                }
                es.add(e);
            }
        }
        Exceptions.throwIfAny(es);
    }

    @Override
    public boolean isUnsubscrible() {
        return unsubscribled;
    }

    /**
     * 添加一条订阅
     * @param subscription 订阅
     */
    public void add(Subscription subscription) {
        if (subscription.isUnsubscrible()) {
            return;
        }
        if (!unsubscribled) {
            synchronized (this) {
                if (!unsubscribled) {
                    LinkedList<Subscription> subs = subscriptions;
                    if (subs == null) {
                        subs = new LinkedList<>();
                        subscriptions = subs;
                    }
                    subs.add(subscription);
                    return;
                }
            }
        }
        subscription.unsubscribe();
    }

    //移除一条订阅
    public void remove(final Subscription subscription) {
        if (!unsubscribled) {
            boolean unsubscrible = false;
            synchronized (this) {
                LinkedList<Subscription> subs = subscriptions;
                if (unsubscribled || subs == null) {
                    return;
                }
                unsubscrible = subs.remove(subscription);
            }
            if (unsubscrible) {
                subscription.unsubscribe();
            }
        }
    }

    /**
     * 清除订阅列表
     */
    public void clear() {
        if (!unsubscribled) {
            List<Subscription> list;
            synchronized (this) {
                list = subscriptions;
                subscriptions = null;
            }
            unsubscribeFromAll(list);
        }
    }

    /**
     * 是否具有订阅
     * @return  是否具有订阅
     */
    public boolean hasSubscriptions() {
        if (!unsubscribled) {
            synchronized (this) {
                return !unsubscribled && subscriptions != null && !subscriptions.isEmpty();
            }
        }
        return false;
    }
}
