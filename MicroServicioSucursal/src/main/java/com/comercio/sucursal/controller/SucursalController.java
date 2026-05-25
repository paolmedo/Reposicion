package com.comercio.sucursal.controller;

import com.comercio.sucursal.dto.SucursalDTO;
import com.comercio.sucursal.model.Sucursal;
import com.comercio.sucursal.service.SucursalService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    private static final Logger logger = LoggerFactory.getLogger(SucursalController.class);

    @Autowired
    private SucursalService sucursalService;

    @PostMapping
    public ResponseEntity<Sucursal> crear(@Valid @RequestBody SucursalDTO sucursalDTO) {
        logger.info("Controller: Petición POST recibida en '/api/sucursales'");
        Sucursal nueva = sucursalService.guardarSucursal(sucursalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Long id, @Valid @RequestBody SucursalDTO sucursalDTO) {
        logger.info("Controller: Petición PUT recibida para actualizar ID: {}", id);
        Sucursal actualizada = sucursalService.actualizarSucursal(id, sucursalDTO);

        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> listar() {
        logger.info("Controller: Petición GET recibida en '/api/sucursales'");
        return ResponseEntity.ok(sucursalService.obtenerTodas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> obtenerPorId(@PathVariable Long id) {
        logger.info("Controller: Buscando sucursal ID: {}", id);
        Sucursal sucursal = sucursalService.obtenerPorId(id);
        if (sucursal != null) {
            return ResponseEntity.ok(sucursal);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Long id){
        logger.info("Controller: Petición DELETE recibida para ID: {}", id);
        boolean eliminado = sucursalService.eliminarSucursal(id);
        if (eliminado){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}