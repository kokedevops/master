package com.skillnest.firstspring.infrastructure.adapter.out.persistence;

import com.skillnest.firstspring.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository JPA - Interface para operaciones de base de datos
 * Extiende JpaRepository para operaciones CRUD básicas
 */
@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    
    /**
     * Buscar usuario por email
     */
    Optional<UserEntity> findByEmail(String email);
    
    /**
     * Verificar si existe un usuario con el email dado
     */
    boolean existsByEmail(String email);
}
