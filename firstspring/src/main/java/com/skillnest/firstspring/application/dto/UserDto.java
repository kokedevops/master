package com.skillnest.firstspring.application.dto;

import java.time.LocalDate;

/**
 * DTO para transferencia de datos de User
 * Se usa para comunicación entre las capas de aplicación e infraestructura
 */
public class UserDto {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private LocalDate birthdate;
    
    // Constructor por defecto
    public UserDto() {}
    
    // Constructor completo
    public UserDto(Long id, String firstName, String lastName, String email, String bio, LocalDate birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bio = bio;
        this.birthdate = birthdate;
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getBio() {
        return bio;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public LocalDate getBirthdate() {
        return birthdate;
    }
    
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
