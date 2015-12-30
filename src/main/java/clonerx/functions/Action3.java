package clonerx.functions;

/**
 * Created by luqiao-yangliuying on 2015-12-30-0030.
 */


/**
 * 3个参数的行为
 */
public interface Action3<T1,T2,T3> extends Action {
    void call(T1 t1, T2 t2, T3 t3);
}
