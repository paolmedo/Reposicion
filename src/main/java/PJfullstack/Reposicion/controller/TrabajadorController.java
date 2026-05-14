package PJfullstack.Reposicion.controller;

import PJfullstack.Reposicion.entity.Trabajador;
import PJfullstack.Reposicion.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/trabajadores")

public class TrabajadorController {
   @Autowired
    private TrabajadorService trabajadorService;
   @PostMapping
   public Trabajador crearTrabajador(@RequestBody Trabajador nuevoTrabajador){
       return trabajadorService.guardarTrabajador(nuevoTrabajador);
   }
}
