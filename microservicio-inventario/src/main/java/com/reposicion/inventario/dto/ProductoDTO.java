package com.reposicion.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductoDTO {



    @NotBlank(message = "El codigo de barra no puede estar vacio")
    private String CodigoBarra;

    @NotBlank(message = "El nombre del producto no puede estar vacio")
    private String nombreProducto;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El producto no puede ser negativo" )
    private Integer stock;

    @NotBlank(message = "La descripcion no puede estar vacia")
    private String descripcionProducto;

    @NotNull(message = "La fecha de entrada es obligatoria")
    private LocalDate fechaEntradaProducto;

    private Long categoriaId;
}
