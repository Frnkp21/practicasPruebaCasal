package com.example.Controllers;

import com.example.Entities.User;
import com.example.Errors.ErrorResponse;
import com.example.Services.UserService;
import com.example.Errors.ResourceNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
    public void testReadUserById_NotFound() {
        int nonExistentUserId = 123;
        when(userService.readUserById(nonExistentUserId)).thenThrow(ResourceNotFoundException.class);
        assertThrows(ResourceNotFoundException.class, () -> {
            userController.readUserById(nonExistentUserId);
        });
    }

    @Test
    void testCreateUser() {
        User user = new User();
        when(userService.createUser(any())).thenReturn(user);
        ResponseEntity<Object> response = userController.createUser(new User());
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
