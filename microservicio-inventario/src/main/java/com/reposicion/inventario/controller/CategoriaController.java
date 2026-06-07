package com.reposicion.inventario.controller;

import com.reposicion.inventario.dto.CategoriaDTO;
import com.reposicion.inventario.model.Categoria;
import com.reposicion.inventario.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "Operaciones relacionadas con los trabajadores.")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    // Crear categoria
    @PostMapping
    @Operation(summary = "Crear categoria", description = "Crear una categoria con un ID.")
    public ResponseEntity<Categoria> categoriaCreada(@RequestBody @Valid CategoriaDTO categoriaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crearCategoria(categoriaDTO));
    }
    // Listar categorias
    @GetMapping
    @Operation(summary = "Obtener todas las categorias", description = "Obtener todas las categorias en una lista.")
    public ResponseEntity<List<Categoria>> listarCategorias(){
        List<Categoria> categorias = categoriaService.listarCategorias();
        return  ResponseEntity.ok(categorias);
    }
    // Listar una categoria
    @GetMapping("/{id}")
    @Operation(summary = "Obtener una categoria", description = "Obtener una categoria mediante su ID.")
    public ResponseEntity<Categoria> listarUnaCategoria(@PathVariable Long id){
        Categoria categoria = categoriaService.listarUnaCategoria(id);
        return ResponseEntity.ok(categoria);
    }
    // Actualizar una categoria
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una categoria", description = "Actualizar una categoria mediante su ID.")
    public ResponseEntity<Categoria> categoriaModificada(@PathVariable Long id, @RequestBody @Valid CategoriaDTO categoriaDTO){
        Categoria categoriaActualizada = categoriaService.actualizarUnaCategoria(id, categoriaDTO);
        return ResponseEntity.ok(categoriaActualizada);
    }
    // Eliminar una categoria
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una categoria", description = "Eliminar una categoria mediante su ID.")
    public ResponseEntity<Void> categoriaEliminado(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
