package com.reposicion.identidad.controller;

import com.reposicion.identidad.dto.TrabajadorDTO;
import com.reposicion.identidad.model.Trabajador;
import com.reposicion.identidad.service.TrabajadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")
@Tag(name = "Trabajadores", description = "Operaciones relacionadas con los trabajadores.")

public class TrabajadorController {
   @Autowired
    private TrabajadorService trabajadorService;
   // Crear un trabajador
   @PostMapping
   @Operation(summary = "Crear un trabajador", description = "Crear un trabajador con un ID.")
   public ResponseEntity<Trabajador> crearTrabajador(@RequestBody @Valid TrabajadorDTO trabajadorDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(trabajadorService.guardarTrabajador(trabajadorDTO));
   }
   // Listar TODOS los trabajadores registrados
   @GetMapping
   @Operation(summary = "Obtener todos los trabajadores", description = "Obtener todos los trabajadores en una lista.")
   public ResponseEntity<List<Trabajador>> listarTrabajador(){
       return ResponseEntity.ok(trabajadorService.listarTodosTrabajadores());
   }
   // Listar un trabajador en especifico
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un trabajador", description = "Obtener un trabajador mediante su ID.")
    public ResponseEntity<Trabajador> listarUnTrabajador(@PathVariable Long id){
       return ResponseEntity.ok(trabajadorService.listarUnSoloTrabajador(id));
    }
   // Actualizar trabajador
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un trabajador", description = "Actualizar un trabajador mediante su ID.")
    public ResponseEntity<Trabajador> actualizarTrabajadores(@PathVariable Long id,@RequestBody @Valid Trabajador trabajadorActualizado ){
       Trabajador trabajadorGuardado = trabajadorService.actualizarTrabajador(id,trabajadorActualizado );
    return ResponseEntity.ok(trabajadorGuardado);
    }
    // Eliminar trabajador
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un trabajador", description = "Eliminar un trabajador mediante su ID.")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable Long id){
    trabajadorService.eliminarTrabajador(id);
    return ResponseEntity.noContent().build();
    }
    // Asignar turno a trabajador
    @PutMapping("/{trabajadorId}/turno/{turnoId}")
    @Operation(summary = "Asignar un turno", description = "Asignar un turno a un trabajador. Se necesita el ID del turno y el ID del trabajador.")
    public ResponseEntity<Trabajador> asignarTurnoTrabajador(@PathVariable Long trabajadorId, @PathVariable Long turnoId){
       Trabajador trabajadorActualizado = trabajadorService.asignarTurno(trabajadorId, turnoId);
       return ResponseEntity.ok(trabajadorActualizado);
    }
}
