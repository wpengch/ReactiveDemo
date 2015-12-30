package clonerx.functions;

/**
 * Created by ${王鹏超} on 2015-12-30-0030.
 */
public class Actions {
    private Actions(){
        throw new IllegalStateException("No Instance!");
    }

    @SuppressWarnings("unchecked")
    public static <T0,T1,T2,T3,T4,T5,T6,T7,T8> EmptyAction<T0,T1,T2,T3,T4,T5,T6,T7,T8> empty(){
        return EMPTY_ACTION;
    }

    private static final EmptyAction EMPTY_ACTION = new EmptyAction();

    /**
     * 空行为
     */
    private static final class EmptyAction<T0,T1,T2,T3,T4,T5,T6,T7,T8> implements
            Action0,
            Action1<T0>,
            Action2<T0,T1>,
            Action3<T0,T1,T2>,
            Action4<T0,T1,T2,T3>,
            Action5<T0,T1,T2,T3,T4>,
            Action6<T0,T1,T2,T3,T4,T5>,
            Action7<T0,T1,T2,T3,T4,T5,T6>,
            Action8<T0,T1,T2,T3,T4,T5,T6,T7>,
            Action9<T0,T1,T2,T3,T4,T5,T6,T7,T8>,
            ActionN{

        @Override
        public void call() {

        }

        @Override
        public void call(T0 t0) {

        }

        @Override
        public void call(T0 t0, T1 t1) {

        }

        @Override
        public void call(T0 t0, T1 t1, T2 t2) {

        }

        @Override
        public void call(T0 t0, T1 t1, T2 t2, T3 t3) {

        }

        @Override
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {

        }

        @Override
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {

        }

        @Override
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {

        }

        @Override
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {

        }

        @Override
        public void call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {

        }

        @Override
        public void call(Object... args) {

        }
    }

    public static Func0<Void> toFunc(final Action0 action) {
        return toFunc(action, (Void) null);
    }

    public static <T1> Func1<T1, Void> toFunc(final Action1<T1> action) {
        return toFunc(action, (Void) null);
    }

    public static <T1, T2> Func2<T1, T2, Void> toFunc(final Action2<T1, T2> action) {
        return toFunc(action, (Void) null);
    }

    public static <T1, T2, T3> Func3<T1, T2, T3, Void> toFunc(final Action3<T1, T2, T3> action) {
        return toFunc(action, (Void) null);
    }


    public static <T1, T2, T3, T4> Func4<T1, T2, T3, T4, Void> toFunc(final Action4<T1, T2, T3, T4> action) {
        return toFunc(action, (Void) null);
    }


    public static <T1, T2, T3, T4, T5> Func5<T1, T2, T3, T4, T5, Void> toFunc(
            final Action5<T1, T2, T3, T4, T5> action) {
        return toFunc(action, (Void) null);
    }

    public static <T1, T2, T3, T4, T5, T6> Func6<T1, T2, T3, T4, T5, T6, Void> toFunc(
            final Action6<T1, T2, T3, T4, T5, T6> action) {
        return toFunc(action, (Void) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7> Func7<T1, T2, T3, T4, T5, T6, T7, Void> toFunc(
            final Action7<T1, T2, T3, T4, T5, T6, T7> action) {
        return toFunc(action, (Void) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Func8<T1, T2, T3, T4, T5, T6, T7, T8, Void> toFunc(
            final Action8<T1, T2, T3, T4, T5, T6, T7, T8> action) {
        return toFunc(action, (Void) null);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, Void> toFunc(
            final Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> action) {
        return toFunc(action, (Void)null);
    }


    public static FuncN<Void> toFunc(final ActionN action) {
        return toFunc(action, null);
    }

    public static <R> Func0<R> toFunc(final Action0 action, R result) {
        return () -> {
            action.call();
            return result;
        };
    }
    public static <T1, R> Func1<T1,  R> toFunc(final Action1<T1> action, R result) {
        return (t1) -> {
            action.call(t1);
            return result;
        };
    }
    public static <T1, T2,R> Func2<T1, T2,R> toFunc(final Action2<T1, T2> action, R result) {
        return (t1, t2) -> {
            action.call(t1, t2);
            return result;
        };
    }
    public static <T1, T2, T3, R> Func3<T1, T2, T3, R> toFunc(final Action3<T1, T2, T3> action, R result) {
        return (t1, t2, t3) -> {
            action.call(t1, t2, t3);
            return result;
        };
    }
    public static <T1, T2, T3, T4, R> Func4<T1, T2, T3, T4, R> toFunc(final Action4<T1, T2, T3, T4> action, R result) {
        return (t1, t2, t3, t4) -> {
            action.call(t1, t2, t3, t4);
            return result;
        };
    }
    public static <T1, T2, T3, T4, T5, R> Func5<T1, T2, T3, T4, T5, R> toFunc(final Action5<T1, T2, T3, T4, T5> action, R result) {
        return (t1, t2, t3, t4, t5) -> {
            action.call(t1, t2, t3, t4, t5);
            return result;
        };
    }
    public static <T1, T2, T3, T4, T5, T6, R> Func6<T1, T2, T3, T4, T5, T6, R> toFunc(final Action6<T1, T2, T3, T4, T5, T6> action, R result) {
        return (t1, t2, t3, t4, t5, t6) -> {
            action.call(t1, t2, t3, t4, t5, t6);
            return result;
        };
    }


    public static <T1, T2, T3, T4, T5, T6, T7, R> Func7<T1, T2, T3, T4, T5, T6, T7, R> toFunc(final Action7<T1, T2, T3, T4, T5, T6, T7> action, R result) {
        return (t1, t2, t3, t4, t5, t6, t7) -> {
            action.call(t1, t2, t3, t4, t5, t6, t7);
            return result;
        };
    }


    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Func8<T1, T2, T3, T4, T5, T6, T7, T8, R> toFunc(final Action8<T1, T2, T3, T4, T5, T6, T7, T8> action, R result) {
        return (t1, t2, t3, t4, t5, t6, t7, t8) -> {
            action.call(t1, t2, t3, t4, t5, t6, t7, t8);
            return result;
        };
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Func9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> toFunc(final Action9<T1, T2, T3, T4, T5, T6, T7, T8, T9> action, R result) {
        return (t1, t2, t3, t4, t5, t6, t7, t8, t9) -> {
            action.call(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            return result;
        };
    }

    public static <R> FuncN<R> toFunc(final ActionN action, final R result) {
        return args -> {
            action.call(args);
            return result;
        };
    }
}
