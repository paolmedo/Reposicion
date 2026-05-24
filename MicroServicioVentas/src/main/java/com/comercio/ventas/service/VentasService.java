package com.comercio.ventas.service;

import com.comercio.ventas.dto.VentasDTO;
import com.comercio.ventas.model.Ventas;
import com.comercio.ventas.repository.VentasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentasService {

    private static final Logger logger = LoggerFactory.getLogger(VentasService.class);

    @Autowired
    private VentasRepository ventaRepository;

    public Ventas registrarVenta(VentasDTO ventasDTO) {
        logger.info("Service: Procesando nueva venta del producto '{}' para la sucursal ID: {}",
                ventasDTO.getProducto(), ventasDTO.getSucursalId());

        Ventas venta = new Ventas();
        venta.setSucursalId(ventasDTO.getSucursalId());
        venta.setProducto(ventasDTO.getProducto());
        venta.setCantidad(ventasDTO.getCantidad());
        venta.setTotal(ventasDTO.getTotal());
        venta.setFechaVenta(LocalDateTime.now());

        Ventas guardada = ventaRepository.save(venta);
        logger.info("Service: Venta registrada con éxito. Ticket ID: {}", guardada.getId());
        return guardada;
    }
    public Ventas actualizarVenta(Long id, VentasDTO ventasDTO) {
        logger.info("Service: Intentando actualizar venta con ID: {}", id);

        return ventaRepository.findById(id).map(ventaExistente -> {
            ventaExistente.setSucursalId(ventasDTO.getSucursalId());
            ventaExistente.setProducto(ventasDTO.getProducto());
            ventaExistente.setCantidad(ventasDTO.getCantidad());
            ventaExistente.setTotal(ventasDTO.getTotal());

            Ventas actualizada = ventaRepository.save(ventaExistente);
            logger.info("Service: Venta con ID: {} actualizada exitosamente.", id);
            return actualizada;
        }).orElseGet(() -> {
            logger.warn("Service: No se pudo actualizar. No existe venta con ID: {}", id);
            return null;
        });
    }

    public boolean eliminarVenta(Long id) {
        logger.info("Service: Intentando eliminar la venta con ID: {}", id);
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            logger.info("Service: Venta con ID: {} eliminada correctamente.", id);
            return true;
        }
        logger.warn("Service: No se pudo eliminar. No existe venta con ID: {}", id);
        return false;
    }

    public List<Ventas> listarTodas() {
        logger.info("Service: Extrayendo el historial completo de ventas.");
        return ventaRepository.findAll();
    }
}