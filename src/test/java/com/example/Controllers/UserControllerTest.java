package com.example.Controllers;

import com.example.Entities.User;
import com.example.Services.UserService;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@DataJpaTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testReadAll() {
        List<User> users = Arrays.asList(new User(), new User());
        when(userService.readAllUsers()).thenReturn(users);
        ResponseEntity<Object> response = userController.readAll();
        assertEquals(users, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testReadUserById() {
        User user = new User();
        when(userService.readUserById(any())).thenReturn(user);
        ResponseEntity<User> response = userController.readUserById(1);
        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testReadUserById_NotFound() {
        int nonExistentUserId = 123;
        when(userService.readUserById(nonExistentUserId)).thenThrow(NoResourceFoundException.class);
        assertThrows(NoResourceFoundException.class, () -> {
            userController.readUserById(nonExistentUserId);
        });
    }

    @Test
    void testCreateUser() throws ParseException {
        // Datos de ejemplo para el usuario
        User user = new User();
        user.setContraseña("contraseña123");
        user.setActivo(true);
        user.setNombre("preuba");
        user.setApellidos("Prueba");
        user.setFecha_nacimiento(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse("1990-05-14T22:00:00.000+00:00"));
        user.setSexo('M');
        user.setEmail("juan@example.com");
        user.setNum_tarjeta_sanitaria(123456789);
        user.setComentarios("Ninguno");
        user.setAlergias("Ninguna");
        user.setContacto1_nombre_completo("María López");
        user.setContacto1_telefono("987654321");
        user.setContacto1_email("maria@example.com");
        user.setContacto1_relacion("Madre");
        user.setContacto1_comentarios("Sin comentarios");
        user.setContacto2_nombre_completo("Pedro Martínez");
        user.setContacto2_telefono("654321987");
        user.setContacto2_email("pedro@example.com");
        user.setContacto2_relacion("Padre");
        user.setContacto2_comentarios("Sin comentarios");
        user.setFecha_alta(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").parse("2024-04-28T22:00:00.000+00:00"));
        user.setDNI("99999999A");

        when(userService.createUser(any())).thenReturn(user);

        ResponseEntity<Object> response = userController.createUser(user);

        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    void testDeleteUser() {
        ResponseEntity<Void> response = userController.deleteUser(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1);
    }


    @Test
    void testUpdateUser() {
        User updatedUser = new User();
        when(userService.updateUser(anyInt(), any())).thenReturn(updatedUser);
        ResponseEntity<User> response = userController.updateUser(1, new User());
        assertEquals(updatedUser, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdateUser_Error() {
        int userId = 1;
        User userToUpdate = new User();
        when(userService.updateUser(userId, userToUpdate)).thenThrow(ConstraintViolationException.class);
        assertThrows(ConstraintViolationException.class, () -> {
            userController.updateUser(userId, userToUpdate);
        });
    }

    @Test
    void testDeleteUser_Error() {
        int userId = 1;
        doThrow(RuntimeException.class).when(userService).deleteUser(userId);
        assertThrows(RuntimeException.class, () -> {
            userController.deleteUser(userId);
        });
    }
    @Test
    void testCreateUser_Error() {
        User userToCreate = new User();
        when(userService.createUser(any())).thenThrow(RuntimeException.class);
        assertThrows(RuntimeException.class, () -> {
            userController.createUser(userToCreate);
        });
    }


}