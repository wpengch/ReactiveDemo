package clonerx.exceptions;

import rx.Observer;
import rx.exceptions.CompositeException;
import rx.exceptions.OnErrorFailedException;
import rx.exceptions.OnErrorNotImplementedException;
import rx.exceptions.OnErrorThrowable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 */
public final class Exceptions {
    private Exceptions() {

    }
    private static final int MAX_DEPTH = 25;
    public static RuntimeException propagate(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            throw (RuntimeException) throwable;
        } else if (throwable instanceof Error) {
            throw (Error) throwable;
        } else {
            throw new RuntimeException(throwable);
        }
    }

    public static void throwIfFatal(Throwable t) {
        if (t instanceof OnErrorNotImplementedException) {
            throw (OnErrorNotImplementedException) t;
        } else if (t instanceof OnErrorFailedException) {
            throw (OnErrorFailedException) t;
        }
        // values here derived from https://github.com/ReactiveX/RxJava/issues/748#issuecomment-32471495
        else if (t instanceof StackOverflowError) {
            throw (StackOverflowError) t;
        } else if (t instanceof VirtualMachineError) {
            throw (VirtualMachineError) t;
        } else if (t instanceof ThreadDeath) {
            throw (ThreadDeath) t;
        } else if (t instanceof LinkageError) {
            throw (LinkageError) t;
        }
    }

    public static void addCause(Throwable e, Throwable cause) {
        Set<Throwable> seenCauses = new HashSet<Throwable>();

        int i = 0;
        while (e.getCause() != null) {
            if (i++ >= MAX_DEPTH) {
                // stack too deep to associate cause
                return;
            }
            e = e.getCause();
            if (seenCauses.contains(e.getCause())) {
                break;
            } else {
                seenCauses.add(e.getCause());
            }
        }
        // we now have 'e' as the last in the chain
        try {
            e.initCause(cause);
        } catch (Throwable t) {
            // ignore
            // the javadocs say that some Throwables (depending on how they're made) will never
            // let me call initCause without blowing up even if it returns null
        }
    }

    public static Throwable getFinalCause(Throwable e) {
        int i = 0;
        while (e.getCause() != null) {
            if (i++ >= MAX_DEPTH) {
                // stack too deep to get final cause
                return new RuntimeException("Stack too deep to get final cause");
            }
            e = e.getCause();
        }
        return e;
    }

    public static void throwIfAny(List<? extends Throwable> exceptions) {
        if (exceptions != null && !exceptions.isEmpty()) {
            if (exceptions.size() == 1) {
                Throwable t = exceptions.get(0);
                // had to manually inline propagate because some tests attempt StackOverflowError
                // and can't handle it with the stack space remaining
                if (t instanceof RuntimeException) {
                    throw (RuntimeException) t;
                } else if (t instanceof Error) {
                    throw (Error) t;
                } else {
                    throw new RuntimeException(t);
                }
            }
            throw new CompositeException(
                    "Multiple exceptions", exceptions);
        }
    }

    public static void throwOrReport(Throwable t, Observer<?> o, Object value) {
        Exceptions.throwIfFatal(t);
        o.onError(OnErrorThrowable.addValueAsLastCause(t, value));
    }

    public static void throwOrReport(Throwable t, Observer<?> o) {
        Exceptions.throwIfFatal(t);
        o.onError(t);
    }
}
