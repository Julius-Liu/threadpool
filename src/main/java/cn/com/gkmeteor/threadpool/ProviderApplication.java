package cn.com.gkmeteor.threadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication()
public class ProviderApplication {
    public static void main(String[] args) {
        // 启动spring boot应用
        SpringApplication.run(ProviderApplication.class, args);
    }
}


