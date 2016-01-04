package clonerx.exceptions;

/**
 * Created by 王鹏超 on 2016-1-4-0004.
 * 代表一个被{@link clonerx.Subscriber#onError(Throwable)}重新抛出的异常
 */
public class OnErrorFailedException extends RuntimeException {
    private static final long serialVersionUID = -419289748403337611L;

    /**
     * Customizes the {@code Throwable} with a custom message and wraps it before it is to be re-thrown as an
     * {@code OnErrorFailedException}.
     *
     * @param message
     *          the message to assign to the {@code Throwable} to re-throw
     * @param e
     *          the {@code Throwable} to re-throw; if null, a NullPointerException is constructed
     */
    public OnErrorFailedException(String message, Throwable e) {
        super(message, e != null ? e : new NullPointerException());
    }

    /**
     * Wraps the {@code Throwable} before it is to be re-thrown as an {@code OnErrorFailedException}.
     *
     * @param e
     *          the {@code Throwable} to re-throw; if null, a NullPointerException is constructed
     */
    public OnErrorFailedException(Throwable e) {
        super(e != null ? e.getMessage() : null, e != null ? e : new NullPointerException());
    }
}