package com.austshop.austshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value="com.austshop.austshop.mapper")
@SpringBootApplication
public class AustshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AustshopApplication.class, args);
    }

}

