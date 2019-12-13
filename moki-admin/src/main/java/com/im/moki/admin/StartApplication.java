package com.im.moki.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//启注解事务管理
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.im.moki.admin"})
@ImportResource("classpath:dubbo/appcontext-dubbo-server.xml")
@EnableAsync
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

}
