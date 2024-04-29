package com.example.Controllers;

import com.example.Entities.User;
import com.example.Services.UserService;
import com.example.Errors.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    void testReadUserById_NotFound() {
        when(userService.readUserById(any())).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> userController.readUserById(1));
    }

    @Test
    void testReadUserById_InvalidId() {
        assertThrows(IllegalArgumentException.class, () -> userController.readUserById(null));
    }

    @Test
    void testCreateUser() {
        User user = new User();
        when(userService.createUser(any())).thenReturn(user);

        ResponseEntity<User> response = userController.createUser(new User());

        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCreateUser_Error() {
        when(userService.createUser(any())).thenThrow(Exception.class);

        ResponseEntity<User> response = userController.createUser(new User());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void testDeleteUser() {
        ResponseEntity<Void> response = userController.deleteUser(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(userService, times(1)).deleteUser(1);
    }

    @Test
    void testDeleteUser_NotFound() {
        doThrow(ResourceNotFoundException.class).when(userService).deleteUser(anyInt());

        ResponseEntity<Void> response = userController.deleteUser(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
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
    void testUpdateUser_NotFound() {
        when(userService.updateUser(anyInt(), any())).thenThrow(ResourceNotFoundException.class);

        ResponseEntity<User> response = userController.updateUser(1, new User());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
