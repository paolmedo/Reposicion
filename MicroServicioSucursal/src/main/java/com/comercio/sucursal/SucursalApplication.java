package com.comercio.sucursal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.comercio.sucursal.repository")
@EntityScan(basePackages = "com.comercio.sucursal.model")
public class SucursalApplication{

    public static void main(String[] args){
        SpringApplication.run(SucursalApplication.class, args);
    }
}