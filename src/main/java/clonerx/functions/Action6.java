package clonerx.functions;

/**
 * Created by luqiao-yangliuying on 2015-12-30-0030.
 */


/**
 * 6个参数的行为
 */
public interface Action6<T1,T2,T3,T4,T5,T6> extends Action {
    void call(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);
}
