package PJfullstack.Inventario.service;

import PJfullstack.Inventario.dto.ProductoDTO;
import PJfullstack.Inventario.entity.Categoria;
import PJfullstack.Inventario.entity.Producto;
import PJfullstack.Inventario.repository.CategoriaRepository;
import PJfullstack.Inventario.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear producto
    public Producto crearProducto(ProductoDTO productoDTO) {
        Producto productoNuevo = new Producto();
        productoNuevo.setCodigoBarra(productoDTO.getCodigoBarra());
        productoNuevo.setNombreProducto(productoDTO.getNombreProducto());
        productoNuevo.setStock(productoDTO.getStock());
        productoNuevo.setDescripcionProducto(productoDTO.getDescripcionProducto());
        productoNuevo.setFechaEntradaProducto(productoDTO.getFechaEntradaProducto());
        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + productoDTO.getCategoriaId()));
            productoNuevo.setCategoria(categoria);
        }
        return productoRepository.save(productoNuevo);
    }

    // listar todos los productos
    public List<Producto> listarTodosProducto() {
        return productoRepository.findAll();
    }

    // Listar un producto en especifico
    public Producto listarUnSoloProducto(Long id){
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado" + id));
    }

    // Actualizar producto
    public Producto actualizarProducto(Long id, ProductoDTO productoActualizado) {
        log.info("Iniciando actualizacion del producto con ID: {}", id);
        Producto productoExistente = productoRepository.findById(id).orElseThrow(() -> {log.error("Error al actualizar producto, ID {} no encontrado", id);
            return new RuntimeException("Producto no encontrado" + id);
        });
        productoExistente.setCodigoBarra(productoActualizado.getCodigoBarra());
        productoExistente.setNombreProducto(productoActualizado.getNombreProducto());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setDescripcionProducto(productoActualizado.getDescripcionProducto());
        productoExistente.setFechaEntradaProducto(productoActualizado.getFechaEntradaProducto());
        if (productoActualizado.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoActualizado.getCategoriaId()).orElseThrow(() -> new RuntimeException("Categoria no encontrada con ID: " + productoActualizado.getCategoriaId()));
            productoExistente.setCategoria(categoria);
        }
        log.info("Producto actualizado exitosamente en la base de datos");
        return productoRepository.save(productoExistente);
    }

    // Eliminar producto
    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }
}