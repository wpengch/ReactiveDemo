package clonerx.plugins;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by 王鹏超 on 2015-12-31-0031.
 */
public class RxJavaPlugins {
    private final static RxJavaPlugins INSTANCE = new RxJavaPlugins();

    private final AtomicReference<RxJavaErrorHandler> errorHandler = new AtomicReference<>();
    private final AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook = new AtomicReference<>();
    private final AtomicReference<RxJavaSchedulersHook> schedulersHook = new AtomicReference<>();

    public static RxJavaPlugins getInstance() {
        return INSTANCE;
    }

    public RxJavaPlugins() {
    }

    void reset() {
        INSTANCE.errorHandler.set(null);
        INSTANCE.observableExecutionHook.set(null);
        INSTANCE.schedulersHook.set(null);
    }

    static final RxJavaErrorHandler DEFAULT_ERROR_HANDLER = new RxJavaErrorHandler() {
    };

    /**
     * 获取错误处理程序
     * @return 错误处理程序
     */
    public RxJavaErrorHandler getErrorHandler() {
        if (errorHandler.get() == null) {
            Object impl = getPluginImplementationViaProperty(RxJavaErrorHandler.class, System.getProperties());
            if (impl == null) {
                errorHandler.compareAndSet(null, DEFAULT_ERROR_HANDLER);
            } else {
                errorHandler.compareAndSet(null, (RxJavaErrorHandler) impl);
            }
        }
        return errorHandler.get();
    }

    /**
     * 通过属性获取类的实例
     * @param pluginClass 插件类
     * @param props 属性列表
     * @return 插件实例
     */
    static Object getPluginImplementationViaProperty(Class<?> pluginClass, Properties props) {
        final String classSimpleName = pluginClass.getSimpleName();

        final String pluginPrefix = "rxjava.plugin.";

        String defaultKey = pluginPrefix + classSimpleName + ".implementation";
        String implementationClass = props.getProperty(defaultKey);

        if (implementationClass == null) {
            final String classSuffix = ".class";
            final String implSuffix = ".impl";

            for (Map.Entry<Object, Object> e : props.entrySet()) {
                String key = e.getKey().toString();
                if (key.startsWith(pluginPrefix) && key.endsWith(classSuffix)) {
                    String value = e.getValue().toString();

                    if (classSimpleName.equals(value)) {
                        String index = key.substring(0, key.length() - classSuffix.length()).substring(pluginPrefix.length());

                        String implKey = pluginPrefix + index + implSuffix;
                        implementationClass = props.getProperty(implKey);

                        if (implementationClass == null) {
                            throw new RuntimeException("Implementing class declaration for " + classSimpleName + " missing: " + implKey);
                        }
                        break;
                    }
                }
            }
        }
        if (implementationClass != null) {
            try {
                Class<?> cls = Class.forName(implementationClass);
                cls = cls.asSubclass(pluginClass);
                return cls.newInstance();
            } catch (ClassCastException e) {
                throw new RuntimeException(classSimpleName + " implementation is not an instance of " + classSimpleName + ": " + implementationClass);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(classSimpleName + " implementation class not found: " + implementationClass, e);
            } catch (InstantiationException e) {
                throw new RuntimeException(classSimpleName + " implementation not able to be instantiated: " + implementationClass, e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(classSimpleName + " implementation not able to be accessed: " + implementationClass, e);
            }
        }
        return null;
    }

}
