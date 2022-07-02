package br.com.delivery.app.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemAdditionalDTO {
    private Long id_item_additional;

    @NotBlank(message = "Por favor entre com o nome")
    private String name;

    @NotNull(message = "Por favor entre com o preço")
    private BigDecimal price;
}
