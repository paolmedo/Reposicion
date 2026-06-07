package com.reposicion.identidad.controller;

import com.reposicion.identidad.dto.TurnoDTO;
import com.reposicion.identidad.model.TurnoTrabajador;
import com.reposicion.identidad.service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
@Tag(name = "Turnos", description = "Operaciones relacionadas con los turnos.")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    // Crear turno
    @PostMapping
    @Operation(summary = "Crear un turno", description = "Crear un turno con un ID")
    public ResponseEntity<TurnoTrabajador> turnoCreado(@RequestBody @Valid TurnoDTO turnoDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoService.crearTurno(turnoDTO));
    }
    // Listar turnos
    @GetMapping
    @Operation(summary = "Obtener todos los turnos", description = "Obtener todos los turnos en una lista.")
    public ResponseEntity<List<TurnoTrabajador>> listarTurno(){
        return ResponseEntity.ok(turnoService.listarTodosTurnos());
    }
    // Listar un turno en especifico
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un turno", description = "Obtener un turno mediante su ID.")
    public ResponseEntity<TurnoTrabajador> listarSoloUnTurno(@PathVariable Long id){
        return ResponseEntity.ok(turnoService.listarUnSoloTurno(id));
    }
    // Actualizar turno
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un turno", description = "Actualizar un turno mediante su ID.")
    public ResponseEntity<TurnoTrabajador> actualizarTurnos(@PathVariable Long id, @RequestBody @Valid TurnoTrabajador turnoTrabajadorActualizado){
    TurnoTrabajador turnoGuardado =   turnoService.actualizarTurno(id,turnoTrabajadorActualizado);
    return ResponseEntity.ok(turnoGuardado);
    }
    // Eliminar turno
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un turno", description = "Eliminar un turno mediante su ID.")
    public ResponseEntity<Void> turnoEliminado(@PathVariable Long id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }
}
