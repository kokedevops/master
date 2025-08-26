package com.skillnest.firstspring.domain.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad de dominio User - Sin dependencias externas
 * Contiene solo la lógica de negocio pura
 */
public class User {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private LocalDate birthdate;
    
    // Constructor por defecto
    public User() {}
    
    // Constructor completo
    public User(Long id, String firstName, String lastName, String email, String bio, LocalDate birthdate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bio = bio;
        this.birthdate = birthdate;
    }
    
    // Constructor para crear usuario (sin ID)
    public User(String firstName, String lastName, String email, String bio, LocalDate birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bio = bio;
        this.birthdate = birthdate;
    }
    
    /**
     * Lógica de negocio: validar que el usuario es válido
     */
    public boolean isValid() {
        return firstName != null && !firstName.trim().isEmpty() &&
               lastName != null && !lastName.trim().isEmpty() &&
               email != null && isValidEmail(email);
    }
    
    /**
     * Lógica de negocio: verificar si es mayor de edad
     */
    public boolean isOfAge() {
        if (birthdate == null) return false;
        return LocalDate.now().minusYears(18).isAfter(birthdate) || 
               LocalDate.now().minusYears(18).isEqual(birthdate);
    }
    
    /**
     * Lógica de negocio: obtener nombre completo
     */
    public String getFullName() {
        if (firstName == null && lastName == null) return "";
        if (firstName == null) return lastName;
        if (lastName == null) return firstName;
        return firstName + " " + lastName;
    }
    
    /**
     * Validación básica de email
     */
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
