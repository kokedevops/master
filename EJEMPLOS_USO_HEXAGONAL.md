# Ejemplos de Uso - Arquitectura Hexagonal

## Testing de la Nueva Arquitectura

### 1. Test Unitario del Dominio (Sin Spring)

```java
package com.skillnest.firstspring.domain.service;

import com.skillnest.firstspring.domain.model.User;
import com.skillnest.firstspring.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UserDomainServiceTest {
    
    @Mock
    private UserRepositoryPort userRepositoryPort;
    
    private UserDomainService userDomainService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userDomainService = new UserDomainService(userRepositoryPort);
    }
    
    @Test
    void shouldCreateValidUser() {
        // Given
        User user = new User("John", "Doe", "john@example.com", "Bio", LocalDate.of(1990, 1, 1));
        when(userRepositoryPort.existsByEmail("john@example.com")).thenReturn(false);
        when(userRepositoryPort.save(any(User.class))).thenReturn(user);
        
        // When
        User result = userDomainService.createUser(user);
        
        // Then
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userRepositoryPort).existsByEmail("john@example.com");
        verify(userRepositoryPort).save(user);
    }
    
    @Test
    void shouldThrowExceptionWhenEmailExists() {
        // Given
        User user = new User("John", "Doe", "john@example.com", "Bio", LocalDate.of(1990, 1, 1));
        when(userRepositoryPort.existsByEmail("john@example.com")).thenReturn(true);
        
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            userDomainService.createUser(user);
        });
    }
}
```

### 2. Test de Integración del Adaptador de Persistencia

```java
package com.skillnest.firstspring.infrastructure.adapter.out.persistence;

import com.skillnest.firstspring.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
class UserPersistenceAdapterTest {
    
    // Test implementation
}
```

## Ejemplos de Uso de los Endpoints

### 1. Crear Usuario
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan",
    "lastName": "Pérez",
    "email": "juan@example.com",
    "bio": "Desarrollador Java",
    "birthdate": "1990-05-15"
  }'
```

### 2. Obtener Todos los Usuarios
```bash
curl -X GET http://localhost:8080/api/users
```

### 3. Obtener Usuario por ID
```bash
curl -X GET http://localhost:8080/api/users/1
```

### 4. Actualizar Usuario
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Juan Carlos",
    "lastName": "Pérez González",
    "email": "juan.carlos@example.com",
    "bio": "Senior Java Developer",
    "birthdate": "1990-05-15"
  }'
```

### 5. Eliminar Usuario
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

### 6. Verificar si existe email
```bash
curl -X GET "http://localhost:8080/api/users/exists?email=juan@example.com"
```

## Ejemplos de Respuestas

### Usuario Creado Exitosamente
```json
{
  "id": 1,
  "firstName": "Juan",
  "lastName": "Pérez",
  "email": "juan@example.com",
  "bio": "Desarrollador Java",
  "birthdate": "1990-05-15"
}
```

### Error de Validación
```json
{
  "firstName": "First name is mandatory",
  "email": "must be a well-formed email address"
}
```

### Error de Negocio
```json
{
  "error": "Ya existe un usuario con este email"
}
```

## Demostrando la Flexibilidad

### Cambiar de JPA a MongoDB (Ejemplo Conceptual)

1. **Sin cambios en el dominio**: `User.java`, `UserUseCase.java`, `UserDomainService.java`

2. **Sin cambios en la aplicación**: `UserApplicationService.java`, `UserDto.java`

3. **Sin cambios en los controladores**: `UserController.java`

4. **Solo cambios en infraestructura**:
```java
// Nuevo adaptador para MongoDB
@Component
public class UserMongoPersistenceAdapter implements UserRepositoryPort {
    
    private final UserMongoRepository mongoRepository;
    
    @Override
    public User save(User user) {
        // Implementación específica para MongoDB
    }
    
    // Resto de métodos...
}
```

### Agregar Cache (Ejemplo)

```java
@Component
public class CachedUserPersistenceAdapter implements UserRepositoryPort {
    
    private final UserRepositoryPort delegate;
    private final CacheManager cacheManager;
    
    @Override
    public Optional<User> findById(Long id) {
        // Buscar en cache primero, luego en delegate
        return cacheManager.get(id, () -> delegate.findById(id));
    }
    
    // Resto de métodos...
}
```

## Ventajas Comprobadas

### 1. **Testabilidad Mejorada**
- Tests unitarios rápidos sin Spring Context
- Mocks simples de interfaces
- Tests de dominio aislados

### 2. **Mantenibilidad**
- Cambios localizados por capas
- Responsabilidades claras
- Fácil debugging

### 3. **Flexibilidad**
- Fácil cambio de tecnologías
- Agregado de funcionalidades sin romper existentes
- Adaptadores intercambiables

### 4. **Conformidad con Principios SOLID**
- **S**ingle Responsibility: Cada clase tiene una responsabilidad
- **O**pen/Closed: Abierto para extensión, cerrado para modificación
- **L**iskov Substitution: Los adaptadores son intercambiables
- **I**nterface Segregation: Interfaces específicas por responsabilidad
- **D**ependency Inversion: El dominio no depende de detalles

---

## Comandos Útiles para Desarrollo

### Compilar
```bash
./gradlew compileJava
```

### Ejecutar tests
```bash
./gradlew test
```

### Ejecutar aplicación
```bash
./gradlew bootRun
```

### Ver estructura del proyecto
```bash
tree src/main/java/com/skillnest/firstspring/
```

La nueva arquitectura hexagonal proporciona una base sólida para el crecimiento y mantenimiento del proyecto a largo plazo.
