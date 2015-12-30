package clonerx;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 * 提供一个接收推送消息的机制
 */
public interface Observer<T> {
    /**
     * 通知观察者（Observer）Observable已经完成推送消息的发送
     *   Observable 不会调用该函数，如果它调用了onError
     */
    void onComplete();

    /**
     * 通知Observer， Observable产生了错误
     * @param e 错误
     */
    void onError(Throwable e);

    /**
     * 为Observer提供下一个observe
     * 该函数可能调用很多次
     * 如果已经调用了onComplete 或者 onError,则该函数不会被再次调用
     * @param t 通过Observable发送的项目
     */
    void onNext(T t);
}
