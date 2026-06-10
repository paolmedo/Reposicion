package com.reposicion.inventario.controller;

import com.reposicion.inventario.dto.ProductoDTO;
import com.reposicion.inventario.excepciones.RespuestaError;
import com.reposicion.inventario.model.Producto;
import com.reposicion.inventario.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones relacionadas con los productos.")
public class ProductoController {

    private final ProductoService productoService;

    // Crear producto
    @PostMapping
    @Operation(summary = "Crear un producto", description = "Crear un producto con un ID.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Producto creado con exito.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Sintaxis no valida.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "409", description = "No puede haber dos productos distintos con el mismo codigo de barra.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class)))
    })
    public ResponseEntity<Producto> productoCreado(@RequestBody @Valid ProductoDTO productoDTO){
    return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(productoDTO));
    }

    // Listar todos los productos
    @GetMapping
    @Operation(summary = "Obtener todos los productos", description = "Obtener todos los productos en una lista.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Productos listados con exito.",
                content = @Content(mediaType = "application/json",
                array = @ArraySchema (schema = @Schema(implementation = Producto.class)))),

    })
    public ResponseEntity<List<Producto>> obtenerTodosLosProductos() {
        List<Producto> productos = productoService.listarTodosProducto();
        return ResponseEntity.ok(productos);
    }

    // Listar un producto en específico
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un producto", description = "Obtener un producto mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto obtenido con exito.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Sintaxis no valida.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "404", description = "ID no encontrado.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class)))
    })
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.listarUnSoloProducto(id);
        return ResponseEntity.ok(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un producto", description = "Actualizar un producto mediante su ID.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Producto actualizado con exito.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "Sintaxis no valida.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "404", description = "ID no encontrado",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "409", description = "El codigo de barra le pertenece a otro producto.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class)))
    })
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody @Valid ProductoDTO productoDTO) {
        Producto productoActualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un producto", description = "Eliminar un producto mediante su ID.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "204", description = "Producto eliminado con exito."),
            @ApiResponse(responseCode = "400", description = "Sintaxis no valida.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "404", description = "ID no encontrado.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class)))
    })
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
