package com.comercio.ventas.service;

import com.comercio.ventas.dto.SucursalDTO;
import com.comercio.ventas.client.SucursalClient;
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
    @Autowired
    private SucursalClient sucursalClient;
    public Ventas registrarVenta(VentasDTO ventasDTO) {
        logger.info("Validando existencia de sucursal ID: {}",
                ventasDTO.getSucursalId());
        sucursalClient.obtenerSucursal(
                ventasDTO.getSucursalId()
        );
        logger.info("Sucursal validada correctamente.");
        SucursalDTO sucursal =
                sucursalClient.obtenerSucursal(ventasDTO.getSucursalId());

        if (sucursal == null) {
            throw new RuntimeException("Sucursal no encontrada");
        }
        Ventas venta = new Ventas();
        venta.setSucursalId(ventasDTO.getSucursalId());
        venta.setProducto(ventasDTO.getProducto());
        venta.setCantidad(ventasDTO.getCantidad());
        venta.setTotal(ventasDTO.getTotal());
        venta.setFechaVenta(LocalDateTime.now());
        Ventas guardada = ventaRepository.save(venta);
        logger.info("Venta registrada con éxito. Ticket ID: {}",
                guardada.getId());
        return guardada;
    }
    public Ventas actualizarVenta(Long id, VentasDTO ventasDTO) {
        logger.info("Intentando actualizar venta con ID: {}", id);
        return ventaRepository.findById(id)
                .map(ventaExistente -> {
                    ventaExistente.setSucursalId(
                            ventasDTO.getSucursalId());
                    ventaExistente.setProducto(
                            ventasDTO.getProducto());
                    ventaExistente.setCantidad(
                            ventasDTO.getCantidad());
                    ventaExistente.setTotal(
                            ventasDTO.getTotal());
                    Ventas actualizada =
                            ventaRepository.save(ventaExistente);
                    logger.info("Venta actualizada con ID: {}", id);
                    return actualizada;
                })
                .orElse(null);
    }
    public boolean eliminarVenta(Long id) {
        logger.info("Intentando eliminar venta ID: {}", id);
        if (ventaRepository.existsById(id)) {
            ventaRepository.deleteById(id);
            logger.info("Venta eliminada correctamente.");
            return true;
        }
        logger.warn("No existe venta con ID: {}", id);
        return false;
    }
    public List<Ventas> listarTodas() {
        logger.info("Listando todas las ventas");
        return ventaRepository.findAll();
    }
}