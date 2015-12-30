package clonerx.functions;

/**
 * Created by luqiao-yangliuying on 2015-12-30-0030.
 */

/**
 * 2个参数的行为
 */
public interface Action2<T1,T2> extends Action {
    void call(T1 t1, T2 t2);
}
