package com.reposicion.sucursal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(basePackages = "com.comercio.sucursal.repository")
@EntityScan(basePackages = "com.comercio.sucursal.model")
public class SucursalApplication{

    public static void main(String[] args){
        SpringApplication.run(SucursalApplication.class, args);
    }
}