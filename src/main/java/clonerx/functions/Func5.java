package clonerx.functions;

/**
 * Created by ${王鹏超} on 2015-12-30-0030.
 */
public interface Func5<T1, T2, T3, T4, T5, R> extends Function {
    R call(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
}