package cn.com.gkmeteor.threadpool.controller;

import cn.com.gkmeteor.threadpool.utils.ThreadPoolUtil;
import cn.com.gkmeteor.threadpool.utils.ThreadWithQueue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TaskController {

    @PostMapping("/task-submit")
    public String taskSubmit(String param) {
        try {
            ThreadWithQueue threadWithQueue = ThreadPoolUtil.getInstance().returnOneThread();
            threadWithQueue.paramAdded(param);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Yes";
    }

}
