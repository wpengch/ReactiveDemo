package clonerx.functions;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 */
@SuppressWarnings("unchecked")
public final class Functions {
    private Functions() {
        throw new IllegalStateException("No instances!");
    }


    public static void checkArgs(Object[] args, Integer count) {
        if (args.length != count) {
            throw new RuntimeException("Func" + count + " expecting " + count + " argument.");
        }
    }

    public static <R> FuncN<R> fromFunc(Func0<? extends R> f) {
        return args -> {
            checkArgs(args, 0);
            return f.call();
        };
    }

    public static <T0, R> FuncN<R> fromFunc(Func1<? super T0, ? extends R> f) {
        return args -> {
            checkArgs(args, 1);
            return f.call((T0) args[0]);
        };
    }

    public static <T0, T1, R> FuncN<R> fromFunc(Func2<? super T0, ? super T1, ? extends R> f) {
        return args -> {
            checkArgs(args, 2);
            return f.call((T0) args[0], (T1) args[1]);
        };
    }

    public static <T0, T1, T2, R> FuncN<R> fromFunc(Func3<? super T0, ? super T1, ? super T2, ? extends R> f) {
        return args -> {
            checkArgs(args, 3);
            return f.call((T0) args[0], (T1) args[1], (T2) args[2]);
        };
    }

    public static <T0, T1, T2, T3, R> FuncN<R> fromFunc(Func4<? super T0, ? super T1, ? super T2, ? super T3, ? extends R> f) {
        return args -> {
            checkArgs(args, 4);
            return f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3]);
        };
    }

    public static <T0, T1, T2, T3, T4, R> FuncN<R> fromFunc(Func5<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? extends R> f) {
        return args -> {
            checkArgs(args, 5);
            return f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4]);
        };
    }

    public static <T0, T1, T2, T3, T4, T5, R> FuncN<R> fromFunc(Func6<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> f) {
        return args -> {
            checkArgs(args, 6);
            return f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4], (T5) args[5]);
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, R> FuncN<R> fromFunc(Func7<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> f) {
        return args -> {
            checkArgs(args, 7);
            return f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4], (T5) args[5], (T6) args[6]);
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, R> FuncN<R> fromFunc(Func8<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> f) {
        return args -> {
            checkArgs(args, 8);
            return f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4], (T5) args[5], (T6) args[6], (T7) args[7]);
        };
    }


    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, R> FuncN<R> fromFunc(Func9<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> f) {
        return args -> {
            checkArgs(args, 9);
            return f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4], (T5) args[5], (T6) args[6], (T7) args[7], (T8) args[8]);
        };
    }


    public static FuncN<Void> fromAction(Action0 f) {
        return args -> {
            checkArgs(args, 0);
            f.call();
            return null;
        };
    }

    public static <T0> FuncN<Void> fromAction(Action1<? super T0> f) {
        return args -> {
            checkArgs(args, 1);
            f.call((T0) args[0]);
            return null;
        };
    }

    public static <T0, T1> FuncN<Void> fromAction(Action2<? super T0, ? super T1> f) {
        return args -> {
            checkArgs(args, 2);
            f.call((T0) args[0], (T1) args[1]);
            return null;
        };
    }

    public static <T0, T1, T2> FuncN<Void> fromAction(Action3<? super T0, ? super T1, ? super T2> f) {
        return args -> {
            checkArgs(args, 3);
            f.call((T0) args[0], (T1) args[1], (T2) args[2]);
            return null;
        };
    }

    public static <T0, T1, T2, T3> FuncN<Void> fromAction(Action4<? super T0, ? super T1, ? super T2, ? super T3> f) {
        return args -> {
            checkArgs(args, 4);
            f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3]);
            return null;
        };
    }

    public static <T0, T1, T2, T3, T4> FuncN<Void> fromAction(Action5<? super T0, ? super T1, ? super T2, ? super T3, ? super T4> f) {
        return args -> {
            checkArgs(args, 5);
            f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4]);
            return null;
        };
    }

    public static <T0, T1, T2, T3, T4, T5> FuncN<Void> fromAction(Action6<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5> f) {
        return args -> {
            checkArgs(args, 6);
            f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4], (T5) args[5]);
            return null;
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6> FuncN<Void> fromAction(Action7<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6> f) {
        return args -> {
            checkArgs(args, 7);
            f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4], (T5) args[5], (T6) args[6]);
            return null;
        };
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7> FuncN<Void> fromAction(Action8<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7> f) {
        return args -> {
            checkArgs(args, 8);
            f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4], (T5) args[5], (T6) args[6], (T7) args[7]);
            return null;
        };
    }


    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8> FuncN<Void> fromAction(Action9<? super T0, ? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8> f) {
        return args -> {
            checkArgs(args, 9);
            f.call((T0) args[0], (T1) args[1], (T2) args[2], (T3) args[3], (T4) args[4], (T5) args[5], (T6) args[6], (T7) args[7], (T8) args[8]);
            return null;
        };
    }

    public static FuncN<Void> fromAction(ActionN action) {
        return args -> {
            action.call(args);
            return null;
        };
    }
}
