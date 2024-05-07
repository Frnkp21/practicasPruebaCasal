package com.example.Controllers;

import com.example.Services.UserService;
import com.example.Entities.User;
import io.swagger.models.Path;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    UserService userService;
    private static Logger logger = Logger.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Object> readAll() {
            List<User> users = userService.readAllUsers();
            logger.info("Obteniendo todos los usuarios");
            logger.debug("Todo los users cargados correctamente");
            return ResponseEntity.ok(users);

    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> readUserById(@PathVariable Integer id) {
        User user = userService.readUserById(id);
        if (user != null) {
            logger.info("Obteniendo usuarios por el id: " + id );
            logger.debug("Recuperando users by id " + id);
            return ResponseEntity.ok(user);
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            logger.info("CREATE USER DONE...");
            logger.debug("Nuevo user creado");
            return ResponseEntity.ok(createdUser);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el usuario: " + ex.getMessage());
        }
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        logger.info("Borrando usuarios por el id" + id);
        logger.debug("Usuario "+ id + " borrado correctamente");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        logger.debug("Actualizando users by id " + id);
        logger.info("Actualizando usuarios por el id "+id);

        return ResponseEntity.ok(user);
    }
}