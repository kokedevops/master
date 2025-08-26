package com.skillnest.firstspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity //Entidad: Objeto persistente/Instancia DB
@Table(name="users")
//LOMBOK: librería que automatiza getters, setters, contructores, etc.
@Data //Getters, Setters, ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AI
    private Long id;

    //NotNull -> No permite que sea nulo, Cualquier tipo de Objeto
    //NotEmpty -> Tamaño sea mayor a 0, " ", Strings, Colecciones, Arrays
    @NotBlank(message="First name is mandatory") //String
    private String firstName; //first_name
    
    @Size(min=2, max=50) //Strings, Listas, Colecciones
    private String lastName; //last_name

    @Email
    private String email;

    @Column(columnDefinition="TEXT") //name, length
    private String bio;

    @Past
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthdate;

    /*
    @Min(value)
    @Max(value)
    @Positive
    @PositiveOrZero
    @Negative
    @NegativeOrZero
    @Past
    @PastOrPresent
    @Future
    @FutureOrPresent
    * */
    
    //@PrePersist ->método que agregue la fecha
    //@PreUpdate -> método que coloque fecha manualmente
    
}
