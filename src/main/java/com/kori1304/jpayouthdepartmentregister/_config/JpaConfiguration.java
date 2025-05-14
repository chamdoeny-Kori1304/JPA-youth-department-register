package com.kori1304.jpayouthdepartmentregister._config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.kori1304.jpayouthdepartmentregister")
@EnableJpaRepositories(basePackages = "com.kori1304.jpayouthdepartmentregister")
public class JpaConfiguration {

}