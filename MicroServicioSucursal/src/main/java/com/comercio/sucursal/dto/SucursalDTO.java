package com.comercio.sucursal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SucursalDTO {

    @NotBlank(message = "El nombre de la sucursal no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "La dirección de la sucursal es obligatoria.")
    private String direccion;

    @NotNull(message = "El teléfono no puede estar vacío.")
    private Integer telefono;

    @NotBlank(message = "La ciudad de la sucursal es obligatoria.")
    private String ciudad;
}