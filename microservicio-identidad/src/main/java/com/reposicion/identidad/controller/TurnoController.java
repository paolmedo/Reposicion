package com.reposicion.identidad.controller;

import com.reposicion.identidad.dto.TurnoDTO;
import com.reposicion.identidad.model.TurnoTrabajador;
import com.reposicion.identidad.service.TurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")

public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    // Crear turno
    @PostMapping
    public ResponseEntity<TurnoTrabajador> turnoCreado(@RequestBody @Valid TurnoDTO turnoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.crearTurno(turnoDTO));
    }
    // Listar turnos
    @GetMapping
    public ResponseEntity<List<TurnoTrabajador>> listarTurno(){
        return ResponseEntity.ok(turnoService.listarTodosTurnos());
    }
    // Listar un turno en especifico
    @GetMapping("/{id}")
    public ResponseEntity<TurnoTrabajador> listarSoloUnTurno(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.listarUnSoloTurno(id));
    }
    // Actualizar turno
    @PutMapping("/{id}")
    public ResponseEntity<TurnoTrabajador> actualizarTurnos(@PathVariable Long id, @RequestBody @Valid TurnoTrabajador turnoTrabajadorActualizado){
    TurnoTrabajador turnoGuardado =   turnoService.actualizarTurno(id,turnoTrabajadorActualizado);
    return ResponseEntity.ok(turnoGuardado);
    }
    // Eliminar turno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> turnoEliminado(@PathVariable Long id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }
}
