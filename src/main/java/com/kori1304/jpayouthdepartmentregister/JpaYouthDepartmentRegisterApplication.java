package com.kori1304.jpayouthdepartmentregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JpaYouthDepartmentRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaYouthDepartmentRegisterApplication.class, args);
    }

}
