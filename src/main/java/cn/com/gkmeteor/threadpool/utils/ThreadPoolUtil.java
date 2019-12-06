package cn.com.gkmeteor.threadpool.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程池工具类
 */
public class ThreadPoolUtil {

    public static int POOL_SIZE = 10;

    private static ThreadPoolUtil instance = null;

    private List<ThreadWithQueue> threadpool = new ArrayList<>();

    private static int index = 0;

    public synchronized static ThreadPoolUtil getInstance() {
        if (instance == null) {
            instance = new ThreadPoolUtil();
        }
        return instance;
    }

    /**
     * 构造方法
     */
    public ThreadPoolUtil() {
        for (int i = 0; i < POOL_SIZE; i++) {
            ThreadWithQueue threadWithQueue = new ThreadWithQueue(i);
            this.threadpool.add(threadWithQueue);
        }
    }

    public ThreadWithQueue returnOneThread() throws Exception {
        ThreadWithQueue thread = null;
        do {
            thread = this.getOneThread();
        } while (thread == null);

        return thread;
    }

    private ThreadWithQueue getOneThread() {
        index = (++index) % POOL_SIZE;
        ThreadWithQueue thread = threadpool.get(index);

        return thread;
    }
}
