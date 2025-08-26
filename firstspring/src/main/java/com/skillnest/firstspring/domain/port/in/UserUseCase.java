package com.skillnest.firstspring.domain.port.in;

import com.skillnest.firstspring.domain.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de entrada - Define los casos de uso para User
 * Esta interfaz define QUÉ se puede hacer, no CÓMO se hace
 */
public interface UserUseCase {
    
    /**
     * Obtener todos los usuarios
     */
    List<User> getAllUsers();
    
    /**
     * Obtener un usuario por ID
     */
    Optional<User> getUserById(Long id);
    
    /**
     * Obtener un usuario por email
     */
    Optional<User> getUserByEmail(String email);
    
    /**
     * Crear un nuevo usuario
     */
    User createUser(User user);
    
    /**
     * Actualizar un usuario existente
     */
    Optional<User> updateUser(Long id, User user);
    
    /**
     * Eliminar un usuario
     */
    boolean deleteUser(Long id);
    
    /**
     * Verificar si existe un usuario con el email dado
     */
    boolean existsByEmail(String email);
}
