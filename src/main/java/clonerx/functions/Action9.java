package clonerx.functions;

/**
 * Created by luqiao-yangliuying on 2015-12-30-0030.
 */


/**
 * 9个参数的行为
 */
public interface Action9<T1,T2,T3,T4,T5,T6,T7,T8,T9> extends Action {
    void call(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9);
}
