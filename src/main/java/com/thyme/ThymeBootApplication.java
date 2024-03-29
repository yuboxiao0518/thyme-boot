package com.thyme;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author thyme
 * @ClassName SecurityMvcConfigurer
 * @Description 启动类
 * @Date
 */
@SpringBootApplication
@EnableCaching
@MapperScan("com.thyme.*.dao")
public class ThymeBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThymeBootApplication.class, args);
    }
}
