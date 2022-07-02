package br.com.delivery.app.Dto;

import br.com.delivery.app.Model.Additional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalDTO {
    private Long id_additional;

    @NotBlank(message = "Por favor entre com o nome")
    private String name;

    @NotNull(message = "Por favor entre com o preço")
    private BigDecimal price;

    public AdditionalDTO(Additional additional) {
        this.name = additional.getName();
        this.price = additional.getPrice();
    }
}
