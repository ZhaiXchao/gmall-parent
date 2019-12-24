package com.zxc.gmall.pms;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * logstash整合
 * 1.导jar包
 * 2.导入日志配置
 * 3.在kibana里面简历好日志的索引
 */
@EnableDubbo
@MapperScan(basePackages = "com.zxc.gmall.pms.mapper")
@SpringBootApplication
public class GmallPmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallPmsApplication.class, args);
    }

}
