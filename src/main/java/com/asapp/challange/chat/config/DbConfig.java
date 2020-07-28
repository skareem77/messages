package com.asapp.challange.chat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

    @Value("${driverClassName}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${username}")
    private String user;

    @Value("${password}")
    private String password;


    @Bean
    public DataSource dataSource() {
        final DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.driverClassName(driver);
        dataSource.url(url);
        dataSource.username(user);
        dataSource.password(password);
        return dataSource.build();
    }

}
