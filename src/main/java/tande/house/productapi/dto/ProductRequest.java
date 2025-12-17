package tande.house.productapi.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequest {

    @NotBlank
    private String nombre;

    @NotBlank
    private String descripcion;

    @Min(0)
    private int precio;

    @Min(0)
    private int stock;

    @NotBlank
    private String imagenUrl;

    private boolean activo = true;
}
