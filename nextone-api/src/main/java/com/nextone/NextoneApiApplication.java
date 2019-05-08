package com.nextone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.nextone.mapper")
public class NextoneApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NextoneApiApplication.class, args);
    }

}
