package com.skillnest.firstspring.domain.port.out;

import com.skillnest.firstspring.domain.model.User;
import java.util.List;
import java.util.Optional;

/**
 * Puerto de salida - Define el contrato para persistencia de User
 * Esta interfaz define QUÉ operaciones de persistencia necesita el dominio
 * La implementación específica (JPA, MongoDB, etc.) será en la capa de infraestructura
 */
public interface UserRepositoryPort {
    
    /**
     * Guardar un usuario (crear o actualizar)
     */
    User save(User user);
    
    /**
     * Buscar usuario por ID
     */
    Optional<User> findById(Long id);
    
    /**
     * Buscar usuario por email
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Obtener todos los usuarios
     */
    List<User> findAll();
    
    /**
     * Eliminar usuario por ID
     */
    void deleteById(Long id);
    
    /**
     * Verificar si existe un usuario con el ID dado
     */
    boolean existsById(Long id);
    
    /**
     * Verificar si existe un usuario con el email dado
     */
    boolean existsByEmail(String email);
}
