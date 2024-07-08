package com.example.platform;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
//@ComponentScan
@Configuration
@MapperScan("com/example/platform.mapper")
public class PetHomePlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetHomePlatformApplication.class, args);
    }
}
