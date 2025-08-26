package com.skillnest.firstspring.infrastructure.configuration;

import com.skillnest.firstspring.application.service.UserApplicationService;
import com.skillnest.firstspring.domain.port.in.UserUseCase;
import com.skillnest.firstspring.domain.port.out.UserRepositoryPort;
import com.skillnest.firstspring.domain.service.UserDomainService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de beans - Inyección de dependencias
 * Aquí se configuran las dependencias entre las diferentes capas
 */
@Configuration
public class BeanConfiguration {
    
    /**
     * Bean para el servicio de dominio
     * Implementa los casos de uso del dominio
     */
    @Bean
    public UserUseCase userUseCase(UserRepositoryPort userRepositoryPort) {
        return new UserDomainService(userRepositoryPort);
    }
    
    /**
     * Bean para el servicio de aplicación
     * Orquesta los casos de uso y maneja DTOs
     */
    @Bean
    public UserApplicationService userApplicationService(UserUseCase userUseCase) {
        return new UserApplicationService(userUseCase);
    }
}
