package clonerx.functions;

import java.util.concurrent.Callable;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 */
public interface Func0<R> extends Function, Callable<R> {
    R call();
}
