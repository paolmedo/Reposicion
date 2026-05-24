package com.comercio.sucursal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "sucursales")
@Data
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la sucursal no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 carácteres.")
    private String nombre;

    @NotBlank(message = "La dirección no puede estar vacía.")
    private String direccion;

    @NotNull(message = "El teléfono no puede estar vacío.")
    private Integer telefono;

    @NotBlank(message = "La ciudad no puede estar vacía.")
    private String ciudad;
}