package com.qwn.domitoryProject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qwn.domitoryproject.mapper")
public class DomitoryProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomitoryProjectApplication.class, args);
    }

}

