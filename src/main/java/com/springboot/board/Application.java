package com.springboot.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//JPA Auditing 기능을 사용하기 위해 main 클래스에 @EnableJpaAuditing 애노테이션을 붙여줌
@EnableJpaAuditing
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
