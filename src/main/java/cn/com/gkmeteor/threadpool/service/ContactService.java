package cn.com.gkmeteor.threadpool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    Logger logger = LoggerFactory.getLogger(ContactService.class);

    public String doWork(String param) {
        String result = "No";
        try {
            logger.info("=== Step 1: 开始获取数据 ===");
            Thread.sleep(3000);
            logger.info("=== 数据获取完成 ===");

            logger.info("=== Step 2: 开始数据处理 ===");
            Thread.sleep(5000);
            logger.info("=== 数据处理完成 ===");

            logger.info("=== Step 3: 开始入库操作 ===");
            Thread.sleep(2000);
            logger.info("=== 入库完成，所有处理结束 === Complete");

            return "Yes";

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
