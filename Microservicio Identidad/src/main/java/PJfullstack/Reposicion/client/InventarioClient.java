package PJfullstack.Reposicion.client;

import PJfullstack.Reposicion.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "inventario-service",
        url = "http://localhost:8082"
)
public interface InventarioClient {

    @GetMapping("/productos/{id}")
    ProductoDTO obtenerProducto(@PathVariable Long id);
}