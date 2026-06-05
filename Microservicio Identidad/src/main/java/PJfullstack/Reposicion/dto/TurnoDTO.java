package PJfullstack.Reposicion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class TurnoDTO {
    @NotBlank(message = "El tipo de turno no puede estar vacio")
    private String tipoTurno;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @JsonFormat(pattern = "HH:mm:ss")
    @NotNull(message = "La hora de termino es obligatoria")
    private LocalTime horaTermino;
}