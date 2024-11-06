package com.yohans.userbankinfo.controller;

import com.yohans.userbankinfo.model.User;
import com.yohans.userbankinfo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User newUser = new User(null, "Solomon", "Nigus", "solnig@example.com", "251723456789");
        User savedUser = new User(UUID.randomUUID(), "Solomon", "Nigus", "solnig@example.com", "251723456789");

        when(userService.createUser(newUser)).thenReturn(savedUser);

        User response = userController.createUser(newUser);

        assertEquals(savedUser, response);
        verify(userService, times(1)).createUser(newUser);
    }

    @Test
    void testGetUserById() {
        // Arrange
        UUID userId = UUID.fromString("8fab3e30-551a-4672-ada7-921aae6193ba");
        User expectedUser = new User(userId, "Abebe", "Kebede", "abekebe@example.com", "251999887766");
        when(userService.getUserById(userId)).thenReturn(expectedUser);

        // Act
        ResponseEntity<User> response = userController.getUserById(userId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        assertNotNull(response.getBody());  // Ensure the response body is not null
        assertEquals(expectedUser, response.getBody());  // Compare the expected user with the actual response body
    }


    @Test
    void testUpdateUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User updatedUserDetails = new User(userId, "Abebe", "Abebaw", "abeabe@example.com", "987654321");

        when(userService.updateUser(userId, updatedUserDetails)).thenReturn(updatedUserDetails);

        // Act
        ResponseEntity<User> response = userController.updateUser(userId, updatedUserDetails);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure the status code is OK
        assertNotNull(response.getBody());  // Ensure the response body is not null
        assertEquals(updatedUserDetails, response.getBody());  // Compare only the User object (body) with the expected user
        verify(userService, times(1)).updateUser(userId, updatedUserDetails);
    }


    @Test
    void testDeleteUser() {
        UUID userId = UUID.randomUUID();
        doNothing().when(userService).deleteUser(userId);

        userController.deleteUser(userId);

        verify(userService, times(1)).deleteUser(userId);
    }
}