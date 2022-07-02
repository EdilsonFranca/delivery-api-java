package br.com.delivery.app.Dto;

import javax.validation.constraints.NotBlank;

public class ClientDTO {

    private Long id_client;

    @NotBlank(message = "Por favor entre com o nome")
    private String name;

    @NotBlank(message = "Por favor entre com o telefone")
    private String phone;

    @NotBlank(message = "Por favor entre com o endereço")
    private String address;
}
