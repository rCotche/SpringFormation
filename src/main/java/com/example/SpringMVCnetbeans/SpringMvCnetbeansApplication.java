package com.example.SpringMVCnetbeans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.*"})
@EntityScan("com.example.*")
@EnableJpaRepositories("Repository")
public class SpringMvCnetbeansApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplicationrun(SpringMvCnetbeansApplication.class, args);
    }
}
