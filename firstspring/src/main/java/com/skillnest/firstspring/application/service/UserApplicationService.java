package com.skillnest.firstspring.application.service;

import com.skillnest.firstspring.application.dto.UserDto;
import com.skillnest.firstspring.domain.model.User;
import com.skillnest.firstspring.domain.port.in.UserUseCase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Servicio de aplicación - Orquesta los casos de uso
 * Actúa como fachada entre la infraestructura y el dominio
 * Maneja la conversión entre DTOs y entidades de dominio
 */
public class UserApplicationService {
    
    private final UserUseCase userUseCase;
    
    public UserApplicationService(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }
    
    public List<UserDto> getAllUsers() {
        return userUseCase.getAllUsers()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    
    public Optional<UserDto> getUserById(Long id) {
        return userUseCase.getUserById(id)
                .map(this::toDto);
    }
    
    public Optional<UserDto> getUserByEmail(String email) {
        return userUseCase.getUserByEmail(email)
                .map(this::toDto);
    }
    
    public UserDto createUser(UserDto userDto) {
        User user = toDomain(userDto);
        User createdUser = userUseCase.createUser(user);
        return toDto(createdUser);
    }
    
    public Optional<UserDto> updateUser(Long id, UserDto userDto) {
        User user = toDomain(userDto);
        return userUseCase.updateUser(id, user)
                .map(this::toDto);
    }
    
    public boolean deleteUser(Long id) {
        return userUseCase.deleteUser(id);
    }
    
    public boolean existsByEmail(String email) {
        return userUseCase.existsByEmail(email);
    }
    
    /**
     * Convertir entidad de dominio a DTO
     */
    private UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBio(),
                user.getBirthdate()
        );
    }
    
    /**
     * Convertir DTO a entidad de dominio
     */
    private User toDomain(UserDto dto) {
        return new User(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getBio(),
                dto.getBirthdate()
        );
    }
}
