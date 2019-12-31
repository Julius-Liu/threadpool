package cn.com.gkmeteor.threadpool.utils;

import cn.com.gkmeteor.threadpool.service.ThreadExecutorService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程池工具类
 */
@Component
public class ThreadPoolUtil implements InitializingBean {

    public static int POOL_SIZE = 2;

    @Autowired
    private ThreadExecutorService threadExecutorService;

    private static ThreadPoolUtil instance = null;

    private List<ThreadWithQueue> threadpool = new ArrayList<>();

    private static int index = 0;

    public static ThreadPoolUtil getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ThreadPoolUtil 初始化异常");
        }
        return instance;
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

    /**
     * 在所有基础属性初始化完成后，初始化当前类
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        for (int i = 0; i < POOL_SIZE; i++) {
            ThreadWithQueue threadWithQueue = new ThreadWithQueue(i, threadExecutorService);
            this.threadpool.add(threadWithQueue);
        }

        instance = this;
    }
}
