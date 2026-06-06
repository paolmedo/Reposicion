package com.reposicion.sucursal.service;

import com.reposicion.sucursal.dto.SucursalDTO;
import com.reposicion.sucursal.model.Sucursal;
import com.reposicion.sucursal.repository.SucursalRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import java.util.List;

@Service
public class SucursalService {

    private static final Logger logger = LoggerFactory.getLogger(SucursalService.class);

    @Autowired
    private SucursalRepository sucursalRepository;

    public Sucursal guardarSucursal(SucursalDTO sucursalDTO) {
        logger.info("Service: Iniciando registro de nueva sucursal '{}'", sucursalDTO.getNombre());

        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setDireccion(sucursalDTO.getDireccion());
        sucursal.setTelefono(sucursalDTO.getTelefono());
        sucursal.setCiudad(sucursalDTO.getCiudad());

        Sucursal guardada = sucursalRepository.save(sucursal);
        logger.info("Service: Sucursal '{}' guardada exitosamente con ID: {}", guardada.getNombre(), guardada.getId());
        return guardada;
    }
    public Sucursal actualizarSucursal(Long id, SucursalDTO sucursalDTO) {
        logger.info("Service: Intentando actualizar sucursal con ID: {}", id);

        return sucursalRepository.findById(id).map(sucursalExistente -> {
            sucursalExistente.setNombre(sucursalDTO.getNombre());
            sucursalExistente.setDireccion(sucursalDTO.getDireccion());
            sucursalExistente.setTelefono(sucursalDTO.getTelefono());
            sucursalExistente.setCiudad(sucursalDTO.getCiudad());

            Sucursal actualizada = sucursalRepository.save(sucursalExistente);
            logger.info("Service: Sucursal con ID: {} actualizada exitosamente.", id);
            return actualizada;
        }).orElseGet(() -> {
            logger.warn("Service: No se pudo actualizar. No existe sucursal con ID: {}", id);
            return null;
        });
    }

    public List<Sucursal> obtenerTodas(){
        logger.info("Service: Consultando el listado total de sucursales en la base de datos.");
        return sucursalRepository.findAll();
    }

    public boolean eliminarSucursal(Long id) {
        logger.info("Service: Intentando eliminar la sucursal con ID: {}", id);
        if (sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
            logger.info("Service: Sucursal con ID: {} eliminada correctamente.", id);
            return true;
        }
        logger.warn("Service: No se pudo eliminar. No existe sucursal con ID: {}", id);
        return false;
    }
    public Sucursal obtenerPorId(Long id) {
        logger.info("Service: Buscando sucursal ID: {}", id);
        return sucursalRepository.findById(id)
                .orElse(null);
    }
}