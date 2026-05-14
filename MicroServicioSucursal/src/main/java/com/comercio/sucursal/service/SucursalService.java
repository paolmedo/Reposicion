package com.comercio.sucursal.service;

import com.comercio.sucursal.model.Sucursal;
import com.comercio.sucursal.repository.SucursalRepository;
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

    public Sucursal guardarSucursal(Sucursal sucursal){
        logger.info("Service: Registrando sucursal '{}'", sucursal.getNombre());
        return sucursalRepository.save(sucursal);
    }
    public List<Sucursal> obtenerTodas(){
        logger.info("Service: Consultando el listado total de sucursales.");
        return sucursalRepository.findAll();
    }
    public boolean eliminarSucursal(Long id) {
        if (sucursalRepository.existsById(id)) {
            sucursalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
