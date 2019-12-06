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
        try {
            ThreadWithQueue threadWithQueue = threadPoolUtil.returnOneThread();
            threadWithQueue.paramAdded(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Yes";
    }

    @PostMapping("/init")
    public String init() {
        ThreadPoolUtil instance = ThreadPoolUtil.getInstance();
        return "Init Success";
    }

}
