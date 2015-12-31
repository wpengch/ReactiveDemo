package rxtest;

import org.junit.Test;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 */
public class TestRxJava {
    @Test
    public void testSimple() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello rxJava");
                subscriber.onCompleted();
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError: " + e.toString());
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        observable.subscribe(subscriber);
    }
}
