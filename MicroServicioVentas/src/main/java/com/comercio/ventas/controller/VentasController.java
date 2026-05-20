package com.comercio.ventas.controller;

import com.comercio.ventas.dto.VentasDTO;
import com.comercio.ventas.model.Ventas;
import com.comercio.ventas.service.VentasService;
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
    public ResponseEntity<Ventas> crearVenta(@Valid @RequestBody VentasDTO ventaDTO) {
        logger.info("Controller: Solicitud POST recibida en '/api/ventas'");
        Ventas nuevaVenta = ventaService.registrarVenta(ventaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }

    @GetMapping
    public ResponseEntity<List<Ventas>> obtenerHistorial() {
        logger.info("Controller: Solicitud GET recibida en '/api/ventas'");
        return ResponseEntity.ok(ventaService.listarTodas());
    }
}