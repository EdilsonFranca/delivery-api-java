package br.com.delivery.app.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Settings {
    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}