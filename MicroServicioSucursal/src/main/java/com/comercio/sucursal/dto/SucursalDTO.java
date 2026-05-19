package com.comercio.sucursal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SucursalDTO {

    @NotBlank(message = "El nombre de la sucursal no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres.")
    private String nombre;

    @NotBlank(message = "La dirección de la sucursal es obligatoria.")
    private String direccion;

    @NotNull(message = "El teléfono no puede estar vacío.")
    private Integer telefono;

    public SucursalDTO() {}

    public SucursalDTO(String nombre, String direccion, Integer telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public Integer getTelefono() { return telefono; }
    public void setTelefono(Integer telefono) { this.telefono = telefono; }
}