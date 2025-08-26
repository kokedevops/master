package com.skillnest.firstspring.infrastructure.adapter.out.persistence;

import com.skillnest.firstspring.domain.model.User;
import com.skillnest.firstspring.domain.port.out.UserRepositoryPort;
import com.skillnest.firstspring.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de persistencia - Implementa el puerto de salida UserRepositoryPort
 * Convierte entre entidades de dominio y entidades JPA
 * Usa el repository JPA para operaciones de base de datos
 */
@Component
public class UserPersistenceAdapter implements UserRepositoryPort {
    
    private final UserJpaRepository userJpaRepository;
    
    public UserPersistenceAdapter(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }
    
    @Override
    public User save(User user) {
        UserEntity userEntity = toEntity(user);
        UserEntity savedEntity = userJpaRepository.save(userEntity);
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(this::toDomain);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(this::toDomain);
    }
    
    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
    
    /**
     * Convertir entidad de dominio a entidad JPA
     */
    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBio(),
                user.getBirthdate()
        );
    }
    
    /**
     * Convertir entidad JPA a entidad de dominio
     */
    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getBio(),
                entity.getBirthdate()
        );
    }
}
