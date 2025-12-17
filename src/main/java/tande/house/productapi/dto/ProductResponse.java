package tande.house.productapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String nombre;
    private Integer precio;
    private Integer stock;
    private String categoria;
    private Boolean oferta;
    private String portada;
    private String sku;
}
