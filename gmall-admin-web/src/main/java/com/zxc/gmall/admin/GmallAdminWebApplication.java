package com.zxc.gmall.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
 * 不进行数据源的自动配置
 *
 * 如果导入的依赖,引入一个自动配置的场景
 * 1.这个场景自动配置默认生效,我们就必须配置他
 * 2.如果不想配置
 *      1.引入的时候在pom.xml文件中排除依赖
 *      2.排除掉这个场景的自动配置
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GmallAdminWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(GmallAdminWebApplication.class, args);
    }

}
