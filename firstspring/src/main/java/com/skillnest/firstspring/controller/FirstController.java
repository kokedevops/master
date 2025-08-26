package com.skillnest.firstspring.controller;

import com.skillnest.firstspring.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //Dicta que la clase es controlador -> API
@RequestMapping("/api") //Todas las rutas que se generan van a comenzar con /api
public class FirstController {

    @GetMapping("/") //@RequestMapping(value="/", method=RequestMethod.GET)
    public String home() {
        return "¡Hola desde el controlador!";
    }

    @GetMapping("/respuesta")
    public ResponseEntity<String> respuesta() {
        return new ResponseEntity<>("Esta es mi respuesta", HttpStatus.ACCEPTED);
    }

    @GetMapping("/search") // localhost:8080/api/search?q=spring
    public ResponseEntity<String> search(@RequestParam("q") String termino) { //termino = "spring";
        //@RequestParam(value="q", required=false, defaultValue="") String termino
        return new ResponseEntity<>("Buscando: "+termino, HttpStatus.OK);
    }

    //@GetMapping(value={"/busqueda", "/busqueda/{termino}")
    //@PathVariable(value="termino", required=false)
    @GetMapping("/busqueda/{termino}") // localhost:8080/api/busqueda/SpringBoot
    public ResponseEntity<String> busqueda(@PathVariable("termino") String terminoDeBusqueda) { //terminoDeBusqueda = "SpringBoot"
        //@PathVariable String termino
        return new ResponseEntity<>("Buscando: "+terminoDeBusqueda, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        return new ResponseEntity<>("Guardado usuario: "+user.getFirstName(), HttpStatus.CREATED);
    }

}
