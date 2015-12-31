package clonerx.subscriptions;

import clonerx.Subscription;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 * 可以进行多次分配的订阅
 */
public final class MultipleAssignmentSubscription implements Subscription {
    final AtomicReference<State> state = new AtomicReference<>(new State(false, Subscriptions.empty()));

    @Override
    public void unsubscribe() {
        State oldState, newState;
        final AtomicReference<State> localState = this.state;
        do {
            oldState = localState.get();
            if (oldState.isUnsubscribed) {
                return;
            } else {
                newState = oldState.unsubscribe();
            }
        } while (!localState.compareAndSet(oldState, newState));
        oldState.subscription.unsubscribe();
    }

    @Override
    public boolean isUnsubscrible() {
        return state.get().isUnsubscribed;
    }

    public void set(Subscription s) {
        if (s == null) {
            throw new IllegalArgumentException("Subscription can not be null");
        }
        State oldState;
        State newState;
        final AtomicReference<State> localState = this.state;
        do {
            oldState = localState.get();
            if (oldState.isUnsubscribed) {
                s.unsubscribe();
                return;
            } else {
                newState = oldState.set(s);
            }
        } while (!localState.compareAndSet(oldState, newState));
    }

    public Subscription get() {
        return state.get().subscription;
    }

    private static final class State {
        final boolean isUnsubscribed;
        final Subscription subscription;

        State(boolean u, Subscription subscription) {
            this.isUnsubscribed = u;
            this.subscription = subscription;
        }

        State unsubscribe() {
            return new State(true, subscription);
        }

        State set(Subscription s) {
            return new State(isUnsubscribed, s);
        }
    }
}
