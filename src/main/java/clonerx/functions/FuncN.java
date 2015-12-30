package clonerx.functions;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 */
public interface FuncN<R> extends Function {
    R call(Object... args);
}
