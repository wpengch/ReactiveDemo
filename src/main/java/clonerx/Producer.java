package clonerx;

/**
 * Created by 王鹏超 on 2015-12-30-0030.
 */
public interface Producer {

    /**
     * 该生产者所能生产的最大数目，这是用来实现backpressure的方式
     * backpressure 表示生产者产生的数量快于消费者消费的数量，也就是供大于求
     * 为了使backpressure失效，传递一个Long.MAX_VALUE作为参数就可以了
     *
     * 请求是累加的，当时如果请求的总数大于Long.MAX_VALUE，则另外的请求会被忽略
     * 累加的请求即使达到Long.Max_VALUE也不是使backpressure失效，如：
     * <pre>
     * request(100);
     * request(Long.MAX_VALUE-1);
     * </pre>
     *
     *
     * @param n 想要该生产者生产的数目，如果你希望生产者自己决定生产多少，则传递Long.MAX_VALUE。
     */
    void request(long n);
}
