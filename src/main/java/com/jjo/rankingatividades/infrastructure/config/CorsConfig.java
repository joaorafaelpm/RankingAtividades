package com.jjo.rankingatividades.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mapeia todas as rotas da sua aplicação
                .allowedOrigins("*") // Permite requisições de qualquer origem (requer cuidado em produção)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite os métodos HTTP especificados
                .allowedHeaders("*"); // Permite todos os cabeçalhos
                //.allowCredentials(true); // Permite o envio de credenciais (como cookies e autenticação)
    }
}
