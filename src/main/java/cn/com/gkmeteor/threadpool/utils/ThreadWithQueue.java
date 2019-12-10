package cn.com.gkmeteor.threadpool.utils;

import cn.com.gkmeteor.threadpool.service.ThreadExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * 带有【参数阻塞队列】的线程
 */
public class ThreadWithQueue extends Thread {

    private Logger logger = LoggerFactory.getLogger(ThreadWithQueue.class);

    private BlockingQueue<String> queue;

    private ThreadExecutorService threadExecutorService;    // 线程运行后的业务逻辑处理

    private String threadName;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    /**
     * 构造方法
     *
     * @param i                     第几个线程
     * @param threadExecutorService 线程运行后的业务逻辑处理
     */
    public ThreadWithQueue(int i, ThreadExecutorService threadExecutorService) {
        queue = new java.util.concurrent.LinkedBlockingQueue<>();
        threadName = "Thread(" + i + ")";

        this.threadExecutorService = threadExecutorService;

        this.start();
    }

    /**
     * 将参数放到线程的参数队列中
     *
     * @param param 参数
     * @return
     */
    public void paramAdded(String param) {
        queue.offer(param);

        logger.info("参数已入队，{} 目前参数个数 {}", this.getThreadName(), queue.size());
    }

    public synchronized int getQueueSize() {
        return queue.size();
    }

    @Override
    public void run() {
        while (true) {
            try {
                String param = queue.take();
                logger.info("{} 开始运行，参数队列中还有 {} 个在等待", this.getThreadName(), this.getQueueSize());
                if (param.startsWith("contact")) {
                    threadExecutorService.doContact(param);
                } else if (param.startsWith("user")) {
                    threadExecutorService.doUser(param);
                } else {
                    logger.info("参数无效，不做处理");
                }
                logger.info("{} 本次处理完成", this.getThreadName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
