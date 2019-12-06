package cn.com.gkmeteor.threadpool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public String doWork(String param) {
        String result = "No";
        try {
            logger.info("=== Step 1: 开始收集用户数据 ===");
            Thread.sleep(6000);
            logger.info("=== 数据收集完成 ===");

            logger.info("=== Step 2: 开始数据清洗 ===");
            Thread.sleep(6000);
            logger.info("=== 数据清洗完成 ===");

            logger.info("=== Step 3: 开始入库操作 ===");
            Thread.sleep(3000);
            logger.info("=== 入库完成，所有处理结束 === Complete");

            return "Yes";

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
