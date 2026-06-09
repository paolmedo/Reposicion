package com.reposicion.inventario.controller;

import com.reposicion.inventario.dto.CategoriaDTO;
import com.reposicion.inventario.excepciones.RespuestaError;
import com.reposicion.inventario.model.Categoria;
import com.reposicion.inventario.service.CategoriaService;
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
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Operaciones relacionadas con las categorias.")
public class CategoriaController {

    private final CategoriaService categoriaService;

    // Crear categoria
    @PostMapping
    @Operation(summary = "Crear categoria", description = "Crear una categoria con un ID.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "201", description = "Categoria creada con exito.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "400", description = "Sintaxis no valida.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "409", description = "No puede haber dos categorias con el mismo nombre.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = RespuestaError.class)))
    })
    public ResponseEntity<Categoria> categoriaCreada(@RequestBody @Valid CategoriaDTO categoriaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crearCategoria(categoriaDTO));
    }
    // Listar TODAS las categorias
    @GetMapping
    @Operation(summary = "Obtener todas las categorias", description = "Obtener todas las categorias en una lista.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Categorias obtenidas en una lista con exito.",
                    content = @Content(mediaType = "application/json",
                    array = @ArraySchema (schema = @Schema(implementation = Categoria.class))))
    })
    public ResponseEntity<List<Categoria>> listarCategorias(){
        List<Categoria> categorias = categoriaService.listarCategorias();
        return  ResponseEntity.ok(categorias);
    }

    // Listar una categoria en especifico
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoria", description = "Obtener una categoria mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria obtenida con exito.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "400", description = "Sintaxis no valida.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "404", description = "ID no encontrado.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class)))
    })
    public ResponseEntity<Categoria> listarUnaCategoria(@PathVariable Long id){
        Categoria categoria = categoriaService.listarUnaCategoria(id);
        return ResponseEntity.ok(categoria);
    }

    // Actualizar una categoria
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoria", description = "Actualizar una categoria mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria actualizada con exito.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Categoria.class))),
            @ApiResponse(responseCode = "400", description = "Sintaxis no valida.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "404", description = "ID no encontrado.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "409", description = "No se puede actualizar el nombre de la categoria, a una ya existente.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class)))
    })
    public ResponseEntity<Categoria> categoriaModificada(@PathVariable Long id, @RequestBody @Valid CategoriaDTO categoriaDTO){
        Categoria categoriaActualizada = categoriaService.actualizarUnaCategoria(id, categoriaDTO);
        return ResponseEntity.ok(categoriaActualizada);
    }
    // Eliminar una categoria
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoria", description = "Eliminar una categoria mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria eliminada con exito."),
            @ApiResponse(responseCode = "400", description = "Sintaxis no valida.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class))),
            @ApiResponse(responseCode = "404", description = "ID no encontrado.",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = RespuestaError.class)))
    })
    public ResponseEntity<Void> categoriaEliminado(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
