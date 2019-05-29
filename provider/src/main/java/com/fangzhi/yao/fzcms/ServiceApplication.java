package com.fangzhi.yao.fzcms;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableDubbo(scanBasePackages = "com.fangzhi.yao.fzcms.service.impl")
@PropertySource(value = "classpath:/provider-config.properties")
@EnableConfigurationProperties
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = "com.fangzhi.yao.fzcms")
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class);
    }

}
