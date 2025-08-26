package com.skillnest.firstspring.infrastructure.adapter.in.web;

import com.skillnest.firstspring.application.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador básico - Mantiene la funcionalidad original del FirstController
 * Adaptador de entrada para endpoints simples
 */
@RestController
@RequestMapping("/api")
public class FirstController {
    
    /**
     * Endpoint básico de saludo
     * GET /api/
     */
    @GetMapping("/")
    public String home() {
        return "¡Hola desde el controlador hexagonal!";
    }
    
    /**
     * Endpoint de respuesta con ResponseEntity
     * GET /api/respuesta
     */
    @GetMapping("/respuesta")
    public ResponseEntity<String> respuesta() {
        return new ResponseEntity<>("Esta es mi respuesta desde arquitectura hexagonal", HttpStatus.ACCEPTED);
    }
    
    /**
     * Endpoint de búsqueda con query parameter
     * GET /api/search?q=spring
     */
    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam("q") String termino) {
        return new ResponseEntity<>("Buscando: " + termino, HttpStatus.OK);
    }
    
    /**
     * Endpoint de búsqueda con path variable
     * GET /api/busqueda/{termino}
     */
    @GetMapping("/busqueda/{termino}")
    public ResponseEntity<String> busqueda(@PathVariable("termino") String terminoDeBusqueda) {
        return new ResponseEntity<>("Buscando: " + terminoDeBusqueda, HttpStatus.OK);
    }
    
    /**
     * Endpoint para recibir datos de usuario (simplificado)
     * POST /api/user
     * Nota: Este endpoint es para mantener compatibilidad, para operaciones reales usar /api/users
     */
    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody UserDto user) {
        return new ResponseEntity<>("Recibido usuario: " + user.getFirstName(), HttpStatus.CREATED);
    }
}
