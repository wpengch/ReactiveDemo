package clonerx;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 */

/**
 * 一个订阅，一条订阅，可以注销订阅
 */
public interface Subscription {
    /**
     * 注销该条订阅，将会在当前执行的地方终止
     */
    void unsubscribe();

    /**
     * 判断该条订阅是否已经注销
     * @return 是否注销
     */
    boolean isUnsubscrible();
}
