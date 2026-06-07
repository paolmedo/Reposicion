package com.reposicion.inventario.controller;

import com.reposicion.inventario.dto.ProductoDTO;
import com.reposicion.inventario.model.Producto;
import com.reposicion.inventario.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos.")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Crear producto
    @PostMapping
    @Operation(summary = "Crear un producto", description = "Crear un producto con un ID.")
    public ResponseEntity<Producto> productoCreado(@RequestBody @Valid ProductoDTO productoDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(productoDTO));
    }
    // Listar todos los productos
    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Obtener todos los productos en una lista.")
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.listarTodosProducto();
        return ResponseEntity.ok(productos);
    }

    // Listar un producto en específico
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto", description = "Obtener un producto mediante su ID.")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.listarUnSoloProducto(id);
        return ResponseEntity.ok(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Actualizar un producto mediante su ID.")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody @Valid ProductoDTO productoDTO) {
        Producto productoActualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Eliminar un producto mediante su ID.")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
