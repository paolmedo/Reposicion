package com.reposicion.identidad.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

    // ERROR 400 BAD REQUEST
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaError> manejoPeticionIncorrecta(MethodArgumentNotValidException excep){
        var errorDeCampo = excep.getBindingResult().getFieldError();
        String mensajeClaro = (errorDeCampo != null) ? errorDeCampo.getDefaultMessage() : "Datos de petición inválidos.";
        RespuestaError error = new RespuestaError(mensajeClaro, 400, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // ERROR 404 NOT FOUND
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<RespuestaError> manejoUsuarioNoEncontrado(RuntimeException excep){
         RespuestaError error = new RespuestaError(excep.getMessage(), 404, LocalDateTime.now());
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // ERROR 409 CONFLICT
    @ExceptionHandler(ExceptionConflict.class)
    public ResponseEntity<RespuestaError> manejoDuplicado(ExceptionConflict excep){
        RespuestaError error = new RespuestaError(excep.getMessage(), 409, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // ERROR 500 INTERNAL SERVER ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaError> manejoErrorServidor(Exception excep){
        RespuestaError error = new RespuestaError(excep.getMessage(), 500, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}