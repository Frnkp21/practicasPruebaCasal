package com.example.Controllers;

import com.example.Errors.ErrorResponse;
import com.example.Errors.ResourceNotFoundException;
import com.example.Main;
import com.example.Services.UserService;
import com.example.Entities.User;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    private static Logger logger = Logger.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> readAll() {
        try {
            List<User> users = userService.readAllUsers();
            logger.info("GET ALL");
            return ResponseEntity.ok(users);
        } catch (Exception ex) {
            logger.error("Error para mostrar a todos los usuarios: " + ex.getMessage());
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> readUserById(@PathVariable Integer id) {
        User user = userService.readUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            logger.info("GET BY ID");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            logger.info("CREATE USER DONE...");
            return ResponseEntity.ok(createdUser);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el usuario: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        logger.info("DELETING USER BY ID");
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        User user = userService.updateUser(id, updatedUser);
        logger.info("UPDATING USER BY ID");
        return ResponseEntity.ok(user);
    }
}