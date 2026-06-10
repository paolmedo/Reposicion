package com.reposicion.identidad.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TrabajadorDTO {
    @NotBlank(message = "El RUT no puede estar vacio")
    private String rut;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "Debe ser un formato de correo valido")
    private String correo;

    @NotBlank(message = "El rol no puede estar vacio")
    private String rol;

    @Min(value = 18, message = "La edad debe ser mayor a 18 años ")
    @Max(value = 90, message = "La edad debe ser menor a 90 años ")
    private int edad;
    private Long turnoId;
}