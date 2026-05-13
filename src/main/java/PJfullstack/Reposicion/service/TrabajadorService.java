package PJfullstack.Reposicion.service;

import PJfullstack.Reposicion.entity.Trabajador;
import PJfullstack.Reposicion.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;



}