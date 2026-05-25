package com.comercio.ventas.client;

import com.comercio.ventas.dto.SucursalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "sucursal-service",
        url = "http://localhost:8080"
)
public interface SucursalClient {

    @GetMapping("/api/sucursales/{id}")
    SucursalDTO obtenerSucursal(@PathVariable Long id);
}