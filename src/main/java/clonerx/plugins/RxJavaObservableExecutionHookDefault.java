package clonerx.plugins;

/**
 * Created by 王鹏超 on 2016-1-4-0004.
 * {@link RxJavaObservableExecutionHook}的空实现（no-op impl）
 */
public class RxJavaObservableExecutionHookDefault extends RxJavaObservableExecutionHook {
    private static RxJavaObservableExecutionHookDefault INSTANCE = new RxJavaObservableExecutionHookDefault();
    public static RxJavaObservableExecutionHook getInstance() {
        return INSTANCE;
    }
}
