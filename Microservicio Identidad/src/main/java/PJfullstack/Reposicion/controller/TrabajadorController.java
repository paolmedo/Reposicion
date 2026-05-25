package PJfullstack.Reposicion.controller;

import PJfullstack.Reposicion.dto.TrabajadorDTO;
import PJfullstack.Reposicion.model.Trabajador;
import PJfullstack.Reposicion.service.TrabajadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trabajadores")

public class TrabajadorController {
   @Autowired
    private TrabajadorService trabajadorService;
   // Crear un trabajador
   @PostMapping
   public ResponseEntity<Trabajador> crearTrabajador(@RequestBody @Valid TrabajadorDTO trabajadorDTO){
       return ResponseEntity.status(HttpStatus.CREATED).body(trabajadorService.guardarTrabajador(trabajadorDTO));
   }
   // Listar TODOS los trabajadores registrados
   @GetMapping
   public ResponseEntity<List<Trabajador>> listarTrabajador(){
       return ResponseEntity.ok(trabajadorService.listarTodosTrabajadores());
   }
   // Listar un trabajador en especifico
    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> listarUnTrabajador(@PathVariable Long id){
       return ResponseEntity.ok(trabajadorService.listarUnSoloTrabajador(id));
    }
   // Actualizar trabajador
    @PutMapping("/{id}")
    public ResponseEntity<Trabajador> actualizarTrabajadores(@PathVariable Long id,@RequestBody @Valid Trabajador trabajadorActualizado ){
       Trabajador trabajadorGuardado = trabajadorService.actualizarTrabajador(id,trabajadorActualizado );
    return ResponseEntity.ok(trabajadorGuardado);
    }
    // Eliminar trabajador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable Long id){
    trabajadorService.eliminarTrabajador(id);
    return ResponseEntity.noContent().build();
    }
    // Asignar turno a trabajador
    @PutMapping("/{trabajadorId}/turno/{turnoId}")
    public ResponseEntity<Trabajador> asignarTurnoTrabajador(@PathVariable Long trabajadorId, @PathVariable Long turnoId){
       Trabajador trabajadorActualizado = trabajadorService.asignarTurno(trabajadorId, turnoId);
       return ResponseEntity.ok(trabajadorActualizado);
    }
}
