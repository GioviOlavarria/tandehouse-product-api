package tande.house.productapi.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class StockReserveRequest {

    @NotBlank
    private String commerceOrder;

    @Valid
    @NotEmpty
    private List<StockItemRequest> items;
}
