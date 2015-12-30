package clonerx;

import rxme.internal.util.SubscriptionList;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 * 订阅者
 * 提供一种从Observable接收推送消息的机制，并且允许手动从这些Observable进行注销订阅
 *
 * 当Subscriber调用一个Observable的subscribe之后，Observable会调用Subscriber的onNext函数去推送这些项目。
 * 如果Observable的行为正常，则会调用Subscriber的onComplete函数，否则会调用Subscriber的onError函数
 *
 *
 * @param <T>
 *     Subscriber希望去观察（observe）的项目类型
 */
public abstract class Subscriber<T> implements Observer<T>, Subscription {
    /**
     * 代表请求尚未设定
     */
    private static final Long NOT_SET = Long.MIN_VALUE;

    private final SubscriptionList subscriptions;
    private final Subscriber<?> subscriber;

    private Producer producer;

    private long requested = NOT_SET;

    protected Subscriber() {
        this(null, false);
    }


    /**
     * 使用另一个Subscriber初始化一个新的Subscriber，共享订阅列表
     * @param subscriber 另一个Subscriber
     */
    protected Subscriber(Subscriber<?> subscriber) {
        this(subscriber, true);
    }

    /**
     * 使用另一个Subscriber初始化一个新的Subscriber，可选共享订阅列表
     * @param subscriber 另一个Subscriber
     * @param shareSubscriptions 是否共享订阅列表
     */
    protected Subscriber(Subscriber<?> subscriber, boolean shareSubscriptions) {
        this.subscriber = subscriber;
        this.subscriptions = shareSubscriptions && subscriber != null ? subscriber.subscriptions : new SubscriptionList();
    }

    public final void add(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    @Override
    public void unsubscribe() {
        subscriptions.unsubscribe();
    }

    @Override
    public boolean isUnsubscrible() {
        return subscriptions.isUnsubscrible();
    }

    public void onStart() {

    }

    protected final void request(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + n);
        }

        Producer producerToRequestFrom = null;
        synchronized (this) {
            if (producer != null) {
                producerToRequestFrom = producer;
            } else {
                addToRequested(n);
                return;
            }
        }
        producerToRequestFrom.request(n);
    }

    private void addToRequested(long n) {
        if (requested == NOT_SET) {
            requested = n;
        } else {
            final long total = requested + n;
            if (total < 0) {
                requested = Long.MAX_VALUE;
            } else {
                requested = total;
            }
        }
    }


    public void setProducer(Producer p) {
        long toRequest;
        boolean passToSubscriber = false;

        synchronized (this) {
            toRequest = requested;
            producer = p;
            if (subscriber != null) {
                if (toRequest == NOT_SET) {
                    passToSubscriber = true;
                }
            }
        }

        if (passToSubscriber) {
            subscriber.setProducer(producer);
        } else {
            if (toRequest == NOT_SET) {
                producer.request(Long.MAX_VALUE);
            } else {
                producer.request(toRequest);
            }
        }
    }
}
