package cn.com.gkmeteor.threadpool.utils;

import cn.com.gkmeteor.threadpool.service.ContactService;
import cn.com.gkmeteor.threadpool.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;

public class ThreadWithQueue extends Thread {

    private Logger logger = LoggerFactory.getLogger(ThreadWithQueue.class);

    private ContactService contactService;
    private UserService userService;

    private Queue<String> queue;

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
     * @param i 第几个线程
     */
    public ThreadWithQueue(int i, ContactService contactService, UserService userService) {
        queue = new java.util.concurrent.LinkedBlockingQueue<>();
        threadName = "Thread(" + i + ")";

        this.contactService = contactService;
        this.userService = userService;

        this.start();
    }

    /**
     * 将导入任务放到导入线程中，并唤醒该线程执行
     * 同一时刻，只能被唤醒一次，只有等到该导入线程的队列为空了，才能再唤醒一次
     *
     * @param param 参数
     * @return
     */
    public synchronized int paramAdded(String param) {
        queue.offer(param);
        synchronized (queue) {
            queue.notify();
        }

        return queue.size();
    }

    public synchronized int getQueueSize() {
        return queue.size();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (queue.isEmpty()) {
                    logger.info("{} --> 参数队列为空，即将阻塞", this.getThreadName());
                    synchronized (queue) {
                        queue.wait();
                    }
                }
                String param = queue.poll();
                logger.info("{} 开始运行，参数队列中还有 {} 个在等待", this.getThreadName(), this.getQueueSize());
                if ("contact".equals(param)) {
                    contactService.doWork(param);
                } else if ("user".equals(param)) {
                    userService.doWork(param);
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
