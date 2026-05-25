package PJfullstack.Reposicion.dto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Long productoId;
    private String codigoBarra;
    private String nombreProducto;
    private Integer stock;
    private String descripcionProducto;
}