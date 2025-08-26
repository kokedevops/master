package com.skillnest.firstspring.domain.service;

import com.skillnest.firstspring.domain.model.User;
import com.skillnest.firstspring.domain.port.in.UserUseCase;
import com.skillnest.firstspring.domain.port.out.UserRepositoryPort;

import java.util.List;
import java.util.Optional;

/**
 * Servicio de dominio - Implementa la lógica de negocio
 * No tiene dependencias de frameworks externos
 * Coordina las operaciones del dominio usando los puertos
 */
public class UserDomainService implements UserUseCase {
    
    private final UserRepositoryPort userRepositoryPort;
    
    public UserDomainService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepositoryPort.findAll();
    }
    
    @Override
    public Optional<User> getUserById(Long id) {
        if (id == null || id <= 0) {
            return Optional.empty();
        }
        return userRepositoryPort.findById(id);
    }
    
    @Override
    public Optional<User> getUserByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        return userRepositoryPort.findByEmail(email);
    }
    
    @Override
    public User createUser(User user) {
        // Validaciones de negocio
        if (user == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
        }
        
        if (!user.isValid()) {
            throw new IllegalArgumentException("Los datos del usuario no son válidos");
        }
        
        // Verificar que el email no esté en uso
        if (userRepositoryPort.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con este email");
        }
        
        // Lógica de negocio: establecer ID como null para nuevos usuarios
        user.setId(null);
        
        return userRepositoryPort.save(user);
    }
    
    @Override
    public Optional<User> updateUser(Long id, User user) {
        if (id == null || id <= 0) {
            return Optional.empty();
        }
        
        if (user == null || !user.isValid()) {
            return Optional.empty();
        }
        
        // Verificar que el usuario existe
        Optional<User> existingUser = userRepositoryPort.findById(id);
        if (existingUser.isEmpty()) {
            return Optional.empty();
        }
        
        // Verificar que el email no esté en uso por otro usuario
        Optional<User> userWithEmail = userRepositoryPort.findByEmail(user.getEmail());
        if (userWithEmail.isPresent() && !userWithEmail.get().getId().equals(id)) {
            throw new IllegalArgumentException("Ya existe otro usuario con este email");
        }
        
        // Establecer el ID para la actualización
        user.setId(id);
        
        return Optional.of(userRepositoryPort.save(user));
    }
    
    @Override
    public boolean deleteUser(Long id) {
        if (id == null || id <= 0) {
            return false;
        }
        
        if (!userRepositoryPort.existsById(id)) {
            return false;
        }
        
        userRepositoryPort.deleteById(id);
        return true;
    }
    
    @Override
    public boolean existsByEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return userRepositoryPort.existsByEmail(email);
    }
}
