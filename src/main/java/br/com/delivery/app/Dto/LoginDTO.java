package br.com.delivery.app.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "o Email não pode ser vazios")
    private String email;

    @NotBlank(message = "a Senha não pode ser vazios")
    private String password;
}
