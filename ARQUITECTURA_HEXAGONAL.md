# Migración a Arquitectura Hexagonal - Proyecto FirstSpring

## Índice
1. [Introducción a la Arquitectura Hexagonal](#introducción)
2. [Análisis de la Estructura Actual](#análisis-actual)
3. [Planificación de la Nueva Estructura](#planificación)
4. [Proceso de Migración Paso a Paso](#migración)
5. [Estructura Final](#estructura-final)
6. [Beneficios de la Nueva Arquitectura](#beneficios)

## Introducción a la Arquitectura Hexagonal

La **Arquitectura Hexagonal** (también conocida como Ports and Adapters) es un patrón arquitectónico que:

- **Separa la lógica de negocio** de los detalles de implementación
- **Hace el código más testeable** mediante el uso de interfaces
- **Permite cambiar fácilmente** las tecnologías de persistencia o presentación
- **Mejora la mantenibilidad** y escalabilidad del código

### Componentes principales:

- **Domain (Dominio)**: Entidades y lógica de negocio pura
- **Application (Aplicación)**: Casos de uso y servicios de aplicación
- **Infrastructure (Infraestructura)**: Adaptadores para bases de datos, APIs, etc.
- **Ports**: Interfaces que definen contratos entre capas

## Análisis de la Estructura Actual

### Estructura actual del proyecto FirstSpring:

```
src/main/java/com/skillnest/firstspring/
├── controller/
│   ├── FirstController.java
│   └── UserController.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   └── User.java
├── repository/
│   └── UserRepository.java
├── service/
│   └── UserService.java
└── FirstspringApplication.java
```

### Problemas identificados:

1. **Acoplamiento alto**: Los controladores dependen directamente de los servicios de Spring
2. **Lógica de negocio mezclada**: La lógica está en los servicios que también manejan persistencia
3. **Dependencias externas**: El dominio depende de tecnologías específicas (JPA, Spring)
4. **Difícil de testear**: Requiere levantar todo el contexto de Spring para pruebas

## Planificación de la Nueva Estructura

### Nueva estructura hexagonal:

```
src/main/java/com/skillnest/firstspring/
├── domain/                           # NÚCLEO DEL DOMINIO
│   ├── model/                        # Entidades de dominio
│   │   └── User.java
│   ├── port/                         # Interfaces (Puertos)
│   │   ├── in/                       # Puertos de entrada
│   │   │   └── UserUseCase.java
│   │   └── out/                      # Puertos de salida
│   │       └── UserRepository.java
│   └── service/                      # Lógica de negocio pura
│       └── UserDomainService.java
├── application/                      # CAPA DE APLICACIÓN
│   ├── service/                      # Servicios de aplicación
│   │   └── UserApplicationService.java
│   └── dto/                          # DTOs para transferencia
│       └── UserDto.java
├── infrastructure/                   # CAPA DE INFRAESTRUCTURA
│   ├── adapter/
│   │   ├── in/                       # Adaptadores de entrada
│   │   │   └── web/
│   │   │       ├── UserController.java
│   │   │       └── FirstController.java
│   │   └── out/                      # Adaptadores de salida
│   │       └── persistence/
│   │           ├── UserJpaRepository.java
│   │           ├── UserPersistenceAdapter.java
│   │           └── entity/
│   │               └── UserEntity.java
│   ├── configuration/                # Configuración de Spring
│   │   └── BeanConfiguration.java
│   └── exception/                    # Manejo de excepciones
│       └── GlobalExceptionHandler.java
└── FirstspringApplication.java
```

## Proceso de Migración Paso a Paso

### Paso 1: Crear la estructura de directorios
### Paso 2: Definir el modelo de dominio
### Paso 3: Crear los puertos (interfaces)
### Paso 4: Implementar los servicios de dominio
### Paso 5: Crear los servicios de aplicación
### Paso 6: Implementar los adaptadores de persistencia
### Paso 7: Implementar los adaptadores web
### Paso 8: Configurar la inyección de dependencias
### Paso 9: Migrar las pruebas
### Paso 10: Verificar y refactorizar

*[Los pasos detallados se implementarán a continuación]*

## Estructura Final

La estructura final seguirá el patrón hexagonal donde:

- **El dominio** no conoce la infraestructura
- **Los puertos** definen contratos claros
- **Los adaptadores** implementan los detalles técnicos
- **La aplicación** orquesta los casos de uso

## Beneficios de la Nueva Arquitectura

1. **Independencia de frameworks**: El dominio no depende de Spring o JPA
2. **Facilidad de testing**: Se pueden hacer pruebas unitarias sin contexto de Spring
3. **Flexibilidad**: Fácil cambio de base de datos o framework web
4. **Claridad**: Separación clara de responsabilidades
5. **Mantenibilidad**: Código más organizado y fácil de modificar

---

*Este documento se actualizará conforme se vaya implementando cada paso de la migración.*
