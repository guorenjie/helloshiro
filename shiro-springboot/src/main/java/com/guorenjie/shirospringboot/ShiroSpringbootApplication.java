package com.guorenjie.shirospringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.guorenjie.shirospringboot")
public class ShiroSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringbootApplication.class, args);
    }

}
