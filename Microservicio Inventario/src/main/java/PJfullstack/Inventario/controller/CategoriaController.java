package PJfullstack.Inventario.controller;

import PJfullstack.Inventario.dto.CategoriaDTO;
import PJfullstack.Inventario.entity.Categoria;
import PJfullstack.Inventario.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    // Crear categoria
    @PostMapping
    public ResponseEntity<Categoria> categoriaCreada(@RequestBody @Valid CategoriaDTO categoriaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.crearCategoria(categoriaDTO));
    }
    // Listar categorias
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(){
        List<Categoria> categorias = categoriaService.listarCategorias();
        return  ResponseEntity.ok(categorias);
    }
    // Listar una categoria
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> listarUnaCategoria(@PathVariable Long id){
        Categoria categoria = categoriaService.listarUnaCategoria(id);
        return ResponseEntity.ok(categoria);
    }
    // Actualizar una categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> categoriaModificada(@PathVariable Long id, @RequestBody @Valid CategoriaDTO categoriaDTO){
        Categoria categoriaActualizada = categoriaService.actualizarUnaCategoria(id, categoriaDTO);
        return ResponseEntity.ok(categoriaActualizada);
    }
    // Eliminar una categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> categoriaEliminado(@PathVariable Long id){
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
