package com.reposicion.identidad.excepciones;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class GlobalException {

    // ERROR 400 BAD REQUEST (ERROR DE RESTRICCION)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaError> manejoPeticionIncorrecta(MethodArgumentNotValidException excep){
        var errorDeCampo = excep.getBindingResult().getFieldError();
        String mensajeClaro = (errorDeCampo != null) ? errorDeCampo.getDefaultMessage() : "Datos de petición inválidos.";
        RespuestaError error = new RespuestaError(mensajeClaro, 400, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ERROR 400 BAD REQUEST (ERROR DE TIPO - ej. Enviar un double donde deberia long)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<RespuestaError> manejoTipoDatoIncorrecto(MethodArgumentTypeMismatchException excep){
        RespuestaError error = new RespuestaError("El " + excep.getName() + " enviado es un tipo de dato incompatible.",400, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ERROR 404 NOT FOUND
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RespuestaError> manejoDatoNoEncontrado(RuntimeException excep){
         RespuestaError error = new RespuestaError(excep.getMessage(), 404, LocalDateTime.now());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // ERROR 409 CONFLICT
    @ExceptionHandler(ExceptionConflict.class)
    public ResponseEntity<RespuestaError> manejoDuplicado(ExceptionConflict excep){
        RespuestaError error = new RespuestaError(excep.getMessage(), 409, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // ERROR 409 CONFLICT (Integracion de datos - ej. borrar un dato con llave FK)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<RespuestaError> manejoIntegracionDatos(DataIntegrityViolationException excep){
        log.error("Violación de integridad en la base de datos: ", excep);
        String mensajeClaro = "No se puede eliminar o modificar el registro porque tiene dependencias activas.";
        RespuestaError error = new RespuestaError(mensajeClaro, 409, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // ERROR 500 INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError> manejoErrorServidor(Exception excep){
        RespuestaError error = new RespuestaError(excep.getMessage(), 500, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}