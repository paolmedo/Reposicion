package com.reposicion.sucursal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SucursalDTO {

    @JsonProperty("nombre")
    @NotBlank(message = "El nombre de la sucursal no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @JsonProperty("direccion")
    @NotBlank(message = "La dirección de la sucursal es obligatoria.")
    private String direccion;

    @JsonProperty("telefono")
    @NotNull(message = "El teléfono no puede estar vacío.")
    private Integer telefono;

    @JsonProperty("ciudad")
    @NotBlank(message = "La ciudad de la sucursal es obligatoria.")
    private String ciudad;

    public SucursalDTO() {}
}