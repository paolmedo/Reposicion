package PJfullstack.Inventario.service;

import PJfullstack.Inventario.dto.CategoriaDTO;
import PJfullstack.Inventario.model.Categoria;
import PJfullstack.Inventario.repository.CategoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear categoria
    public Categoria crearCategoria(CategoriaDTO categoriaDTO){
        log.info("Creando nueva categoria con ID {}", categoriaDTO.getCategoriaId());
        Categoria categoriacCreada = new Categoria();
        categoriacCreada.setNombreCategoria(categoriaDTO.getNombreCategoria());
        categoriacCreada.setDescripcionCategoria(categoriaDTO.getDescripcionCategoria());
        return categoriaRepository.save(categoriacCreada);
    }
    // Listar categorias
    public List<Categoria> listarCategorias(){
        log.debug("Iniciando busqueda de todas las categorias");
        return categoriaRepository.findAll();
    }
    // Listar una categoria
    public Categoria listarUnaCategoria(Long id){
        log.debug("Iniciando busqueda de categoria con ID {}", id);
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada por el ID" + id));
    }
    // Actualizar una categoria
    public Categoria actualizarUnaCategoria(Long id,CategoriaDTO categoriaDTO){
        log.info("Modificar una categoria con ID {}", id);
        Categoria categoriaExistente = categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Categoria no encontrada por ID" + id));
        categoriaExistente.setNombreCategoria(categoriaDTO.getNombreCategoria());
        categoriaExistente.setDescripcionCategoria(categoriaDTO.getDescripcionCategoria());
        return categoriaRepository.save(categoriaExistente);
    }
    // Eliminar una categoria
    public void eliminarCategoria(Long id){
        log.info("Eliminando categoria con ID {}", id);
        categoriaRepository.deleteById(id);
    }
}
