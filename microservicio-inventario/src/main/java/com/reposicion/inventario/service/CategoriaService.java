package com.reposicion.inventario.service;

import com.reposicion.inventario.dto.CategoriaDTO;
import com.reposicion.inventario.excepciones.ExceptionConflict;
import com.reposicion.inventario.model.Categoria;
import com.reposicion.inventario.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    // Crear categoria
    public Categoria crearCategoria(CategoriaDTO categoriaDTO){
        log.info("Creando nueva categoria");
        if(categoriaRepository.existsByNombreCategoria(categoriaDTO.getNombreCategoria())){
            throw new ExceptionConflict("El nombre: '" + categoriaDTO.getNombreCategoria() + "' ya existe en otra categoria.");
        }
        Categoria categoriaCreada = new Categoria();
        categoriaCreada.setNombreCategoria(categoriaDTO.getNombreCategoria());
        categoriaCreada.setDescripcionCategoria(categoriaDTO.getDescripcionCategoria());
        return categoriaRepository.save(categoriaCreada);
    }
    // Listar categorias
    public List<Categoria> listarCategorias(){
        log.debug("Iniciando busqueda de todas las categorias");
        return categoriaRepository.findAll();
    }
    // Listar una categoria
    public Categoria listarUnaCategoria(Long id){
        log.debug("Iniciando busqueda de categoria con ID {}", id);
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada por el ID: " + id));
    }
    // Actualizar una categoria
    public Categoria actualizarUnaCategoria(Long id,CategoriaDTO categoriaDTO){
        log.info("Modificar una categoria con ID {}", id);
        Categoria categoriaExistente = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada por ID: " + id));
        if(categoriaRepository.existsByNombreCategoria(categoriaDTO.getNombreCategoria())
                && !categoriaExistente.getNombreCategoria().equals(categoriaDTO.getNombreCategoria())){
            throw new ExceptionConflict("El nombre: '" + categoriaDTO.getNombreCategoria() + "' ya existe en otra categoria.");
        }
        categoriaExistente.setNombreCategoria(categoriaDTO.getNombreCategoria());
        categoriaExistente.setDescripcionCategoria(categoriaDTO.getDescripcionCategoria());
        return categoriaRepository.save(categoriaExistente);
    }
    // Eliminar una categoria
    public void eliminarCategoria(Long id){
        log.info("Eliminando categoria con ID {}", id);
        if (!categoriaRepository.existsById(id)){
            throw new RuntimeException("Categoria no encontrada con ID: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
