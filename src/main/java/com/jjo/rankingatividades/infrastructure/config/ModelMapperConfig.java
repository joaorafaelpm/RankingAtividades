package com.jjo.rankingatividades.infrastructure.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    // Transformando em um componente spring
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
}
