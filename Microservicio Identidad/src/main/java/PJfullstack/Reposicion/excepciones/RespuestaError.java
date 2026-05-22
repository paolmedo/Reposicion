package PJfullstack.Reposicion.excepciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaError {
 private String mensaje;
 private Integer codigoHttp;
 private LocalDateTime fechaError;


}