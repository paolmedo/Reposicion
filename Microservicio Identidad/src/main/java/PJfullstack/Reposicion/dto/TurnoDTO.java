package PJfullstack.Reposicion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class TurnoDTO {
    @NotBlank(message = "El tipo de turno no puede estar vacio")
    private String tipoTurno;

    @NotNull(message = "La hora de inicio es obligatoria")
    private LocalTime horaInicio;

    @NotNull(message = "La hora de termino es obligatoria")
    private LocalTime horaTermino;
}
