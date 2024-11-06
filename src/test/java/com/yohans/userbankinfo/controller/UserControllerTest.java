//package com.yohans.userbankinfo.controller;
//
//import com.yohans.userbankinfo.model.User;
//import com.yohans.userbankinfo.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class UserControllerTest {
//
//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testCreateUser() {
//        User newUser = new User(null, "Abebe", "Kebede", "abebe@example.com", "123456789");
//        User savedUser = new User(UUID.randomUUID(), "Abebe", "Kebede", "abebe@example.com", "123456789");
//
//        when(userService.createUser(newUser)).thenReturn(savedUser);
//
//        User response = userController.createUser(newUser);
//
//        assertEquals(savedUser, response);
//        verify(userService, times(1)).createUser(newUser);
//    }
//
//    @Test
//    void testGetUserById() {
//        UUID userId = UUID.randomUUID();
//        User mockUser = new User(userId, "Abebe", "Kebede", "abebe@example.com", "123456789");
//
//        when(userService.getUserById(userId)).thenReturn(mockUser);
//
//        ResponseEntity<User> response = userController.getUserById(userId);
//
//        assertEquals(mockUser, response);
//        verify(userService, times(1)).getUserById(userId);
//    }
//
//    @Test
//    void testUpdateUser() {
//        UUID userId = UUID.randomUUID();
//        User updatedUserDetails = new User(userId, "Updated", "Name", "updated@example.com", "987654321");
//
//        when(userService.updateUser(userId, updatedUserDetails)).thenReturn(updatedUserDetails);
//
//        ResponseEntity<User> response = userController.updateUser(userId, updatedUserDetails);
//
//        assertEquals(updatedUserDetails, response);
//        verify(userService, times(1)).updateUser(userId, updatedUserDetails);
//    }
//
//    @Test
//    void testDeleteUser() {
//        UUID userId = UUID.randomUUID();
//        doNothing().when(userService).deleteUser(userId);
//
//        userController.deleteUser(userId);
//
//        verify(userService, times(1)).deleteUser(userId);
//    }
//}