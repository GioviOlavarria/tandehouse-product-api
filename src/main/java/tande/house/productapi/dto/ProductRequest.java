package tande.house.productapi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductRequest {


    private String id;

    @NotBlank
    private String nombre;

    @NotNull
    @Min(0)
    private Integer precio;

    @NotNull
    @Min(0)
    private Integer stock;

    @NotBlank
    private String categoria;

    @NotNull
    private Boolean oferta;

    @NotBlank
    private String portada;

    @NotBlank
    private String sku;
}
