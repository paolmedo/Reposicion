package com.reposicion.inventario.service;

import com.reposicion.inventario.dto.ProductoDTO;
import com.reposicion.inventario.excepciones.ExceptionConflict;
import com.reposicion.inventario.model.Categoria;
import com.reposicion.inventario.model.Producto;
import com.reposicion.inventario.repository.CategoriaRepository;
import com.reposicion.inventario.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    // Crear producto
    public Producto crearProducto(ProductoDTO productoDTO) {
        log.info("Iniciando creacion de un nuevo producto con codigo de barra {}", productoDTO.getCodigoBarra());
        if(productoRepository.existsByCodigoBarra(productoDTO.getCodigoBarra())){
            throw new ExceptionConflict("El codigo de barra " + productoDTO.getCodigoBarra() + " Ya existe en otro producto.");
        }
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
        log.debug("Iniciando busqueda de todos los productos");
        return productoRepository.findAll();
    }

    // Listar un producto en especifico
    public Producto listarUnSoloProducto(Long id){
        log.debug("Iniciando busqueda de producto con ID {}", id);
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    // Actualizar producto
    public Producto actualizarProducto(Long id, ProductoDTO productoDTO) {
        log.info("Iniciando actualizacion del producto con ID: {}", id);
        Producto productoExistente = productoRepository.findById(id).orElseThrow(() -> {log.error("Error al actualizar producto, ID {} no encontrado", id);
            return new RuntimeException("Producto no encontrado con ID: " + id);
        });
        if (productoRepository.existsByCodigoBarra(productoDTO.getCodigoBarra())
            && !productoExistente.getCodigoBarra().equals(productoDTO.getCodigoBarra())){
            throw new ExceptionConflict("El codigo de barra " + productoDTO.getCodigoBarra() + " Ya existe en otro producto.");
        }
        productoExistente.setCodigoBarra(productoDTO.getCodigoBarra());
        productoExistente.setNombreProducto(productoDTO.getNombreProducto());
        productoExistente.setStock(productoDTO.getStock());
        productoExistente.setDescripcionProducto(productoDTO.getDescripcionProducto());
        productoExistente.setFechaEntradaProducto(productoDTO.getFechaEntradaProducto());
        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId()).orElseThrow(() ->
                    new RuntimeException("Categoria no encontrada con ID: " + productoDTO.getCategoriaId()));
            productoExistente.setCategoria(categoria);
        }
        log.info("Producto actualizado exitosamente en la base de datos");
        return productoRepository.save(productoExistente);
    }

    // Eliminar producto
    public void eliminarProducto(Long id){
        log.info("Iniciando eliminacion de producto con ID {}", id);
        if(!productoRepository.existsById(id)){
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }
}