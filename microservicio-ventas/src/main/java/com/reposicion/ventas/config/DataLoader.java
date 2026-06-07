package com.reposicion.ventas.config;

import com.reposicion.ventas.model.Ventas;
import com.reposicion.ventas.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private VentasRepository ventaRepository;

    @Override
    public void run(String ... args) {
        if (ventaRepository.count() == 0) {

            Ventas v1 = new Ventas();
            v1.setSucursalId(1L);
            v1.setProducto("Laptop Gamer");
            v1.setCantidad(1);
            v1.setTotal(850000.0);
            v1.setFechaVenta(LocalDateTime.now());

            Ventas v2 = new Ventas();
            v2.setSucursalId(2L);
            v2.setProducto("Mouse Inalámbrico");
            v2.setCantidad(2);
            v2.setTotal(30000.0);
            v2.setFechaVenta(LocalDateTime.now());

            ventaRepository.save(v1);
            ventaRepository.save(v2);

            System.out.println("Los datos de prueba han sido cargados exitosamente.");
        } else {
            System.out.println("La tabla ya contiene estos datos, no se cargaron duplicados.");
        }
    }
}