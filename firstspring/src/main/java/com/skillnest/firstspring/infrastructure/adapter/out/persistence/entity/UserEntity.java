package com.skillnest.firstspring.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Entidad JPA - Representa la tabla en la base de datos
 * Esta clase está en la capa de infraestructura y contiene anotaciones específicas de JPA
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name")
    private String firstName;
    
    @Size(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;
    
    @Email
    @Column(unique = true)
    private String email;
    
    @Column(columnDefinition = "TEXT")
    private String bio;
    
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
}
