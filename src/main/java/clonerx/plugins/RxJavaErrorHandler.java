package clonerx.plugins;


import clonerx.exceptions.Exceptions;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 * 抽象类定义，Subscriber的onError默认处理逻辑
 *
 * 这个插件还负责渲染{@code OnErrorThrowable.OnNextValue}
 */
public abstract class RxJavaErrorHandler {
    protected static final String ERROR_IN_RENDERING_SUFFIX = ".errorRendering";

    /**
     * 接受来自 Observab传递给Subscriber.onError的错误
     * @param throwable 错误
     */
    public void handleError(Throwable throwable) {

    }

    /**
     * 接收OnErrorThrowable.onNextValue引起的错误，并给一个机会去修改该错误的字符串表示，如果该项目不需要管理，则返回空
     * @param item 项目
     * @return 渲染字符串
     */
    public final String handleOnNextValueRendering(Object item) {
        try {
            return render(item);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Throwable throwable) {
            Exceptions.throwIfFatal(throwable);
        }

        return item.getClass().getName() + ERROR_IN_RENDERING_SUFFIX;
    }

    /**
     * 重装该函数去重写特定类型的渲染字符串
     * @param item 项目
     * @return 渲染
     * @throws InterruptedException
     */
    protected String render(Object item) throws InterruptedException{
        return null;
    }
}
