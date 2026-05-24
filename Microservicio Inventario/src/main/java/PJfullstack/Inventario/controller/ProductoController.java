package PJfullstack.Inventario.controller;

import PJfullstack.Inventario.dto.ProductoDTO;
import PJfullstack.Inventario.entity.Producto;
import PJfullstack.Inventario.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Crear producto
    @PostMapping
    public ResponseEntity<Producto> productoCreado(@RequestBody @Valid ProductoDTO productoDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(productoDTO));
    }
    // Listar todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.listarTodosProducto();
        return ResponseEntity.ok(productos);
    }

    // Listar un producto en específico
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.listarUnSoloProducto(id);
        return ResponseEntity.ok(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody @Valid ProductoDTO productoDTO) {
        Producto productoActualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
