package com.rs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan(basePackages = { "com.rs.dao" })
@SpringBootApplication
public class ThreeSmallApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ThreeSmallApplication.class, args);
	}
}
