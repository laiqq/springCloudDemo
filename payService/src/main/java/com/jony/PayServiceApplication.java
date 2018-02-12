package com.jony;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;


@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
//mapper接口类扫描包配置
@MapperScan("com.jony.mapper")
public class PayServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PayServiceApplication.class).web(true).run(args);
	}

}
