package com.zxc.gmall.pms.config;

import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;

@Configuration
public class PmsDataSourceConfig {

    @Bean
    public DataSource dataSource() throws Exception {

        File file = ResourceUtils.getFile("classpath:shardingjdbc.yml");
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(file);

        return dataSource;
    }
}
