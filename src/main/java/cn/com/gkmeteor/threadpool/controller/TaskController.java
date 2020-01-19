package cn.com.gkmeteor.threadpool.controller;

import cn.com.gkmeteor.threadpool.utils.ThreadPoolUtil;
import cn.com.gkmeteor.threadpool.utils.ThreadWithQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TaskController {

    @Autowired
    private ThreadPoolUtil threadPoolUtil;

    @PostMapping("/task-submit")
    public String taskSubmit(String param) {
        String result = "Yes";
        try {
            /**
             * 轮询获取一个线程，然后将参数放到线程的参数队列中
             */
            ThreadWithQueue threadWithQueue = threadPoolUtil.returnOneThread();
            result = threadWithQueue.paramAdded(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
