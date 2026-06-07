package com.reposicion.ventas.controller;

import com.reposicion.ventas.dto.VentasDTO;
import com.reposicion.ventas.model.Ventas;
import com.reposicion.ventas.service.VentasService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentasController {

    private static final Logger logger = LoggerFactory.getLogger(VentasController.class);

    @Autowired
    private VentasService ventaService;

    @PostMapping
    public ResponseEntity<Ventas> crearVenta(@Valid @RequestBody VentasDTO ventasDTO) {
        logger.info("Controller: Solicitud POST recibida en '/api/ventas'");
        Ventas nuevaVenta = ventaService.registrarVenta(ventasDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @GetMapping
    public ResponseEntity<List<Ventas>> obtenerHistorial() {
        logger.info("Controller: Solicitud GET recibida en '/api/ventas'");
        return ResponseEntity.ok(ventaService.listarTodas());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ventas> actualizarVenta(@PathVariable Long id, @Valid @RequestBody VentasDTO ventasDTO) {
        logger.info("Controller: Petición PUT recibida para actualizar venta ID: {}", id);
        Ventas actualizada = ventaService.actualizarVenta(id, ventasDTO);

        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id) {
        logger.info("Controller: Petición DELETE recibida para venta ID: {}", id);
        boolean eliminado = ventaService.eliminarVenta(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}