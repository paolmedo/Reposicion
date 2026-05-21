package PJfullstack.Reposicion.controller;

import PJfullstack.Reposicion.entity.Trabajador;
import PJfullstack.Reposicion.service.TrabajadorService;
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
   public ResponseEntity<Trabajador> crearTrabajador(@RequestBody Trabajador nuevoTrabajador){
       return ResponseEntity.status(HttpStatus.CREATED).body(trabajadorService.guardarTrabajador(nuevoTrabajador));
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
    public ResponseEntity<Trabajador> actualizarTrabajadores(@PathVariable Long id,@RequestBody Trabajador trabajadorActualizado ){
       Trabajador trabajadorGuardado = trabajadorService.actualizarTrabajador(id,trabajadorActualizado );
    return ResponseEntity.ok(trabajadorGuardado);
    }
    // Eliminar trabajador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable Long id){
    trabajadorService.eliminarTrabajador(id);
    return ResponseEntity.noContent().build();
    }
}
