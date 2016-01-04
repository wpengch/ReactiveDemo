package clonerx.exceptions;

/**
 * Created by 王鹏超 on 2016-1-4-0004.
 * 当实现不存在的时候抛出的异常
 *
 * 当调用只有一个onNext参数的Subscriber方法时，onError行为将会在该消息来源的可观察序列所在线程上面抛出该异常。
 * 在这种情况下，onComplete什么也不做。
 */
public class OnErrorNotImplementedException extends RuntimeException {
    private static final long serialVersionUID = -6298857009889503852L;

    /**
     * Customizes the {@code Throwable} with a custom message and wraps it before it is to be re-thrown as an
     * {@code OnErrorNotImplementedException}.
     *
     * @param message
     *          the message to assign to the {@code Throwable} to re-throw
     * @param e
     *          the {@code Throwable} to re-throw; if null, a NullPointerException is constructed
     */
    public OnErrorNotImplementedException(String message, Throwable e) {
        super(message, e != null ? e : new NullPointerException());
    }

    /**
     * Wraps the {@code Throwable} before it is to be re-thrown as an {@code OnErrorNotImplementedException}.
     *
     * @param e
     *          the {@code Throwable} to re-throw; if null, a NullPointerException is constructed
     */
    public OnErrorNotImplementedException(Throwable e) {
        super(e != null ? e.getMessage() : null, e != null ? e : new NullPointerException());
    }
}
