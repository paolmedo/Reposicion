package PJfullstack.Inventario.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoriaDTO {

    @NotBlank(message = "La categoria no puede estar vacia")
    private String nombreCategoria;

    @NotBlank(message = "Debe haber descripcion")
    private String descripcionCategoria;

    private Long categoriaId;
}
