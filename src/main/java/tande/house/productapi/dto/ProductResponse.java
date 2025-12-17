package tande.house.productapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private int precio;
    private int stock;
    private boolean activo;
    private String imagenUrl;
}
