package com.s1935.pandahr.service;

import com.s1935.pandahr.backend.CredentialsDto;
import com.s1935.pandahr.backend.SignUpDto;
import com.s1935.pandahr.backend.UserDto;
import com.s1935.pandahr.infrastructure.exception.AppException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    private SignUpDto signUpDto;
    private CredentialsDto credentialsDto;

    @BeforeEach
    public void setup() {
        signUpDto = new SignUpDto();
        signUpDto.setFirstName("firstName");
        signUpDto.setLastName("lastName");
        signUpDto.setLogin("testUser");
        signUpDto.setPassword("testPassword".toCharArray());

        credentialsDto = new CredentialsDto();
        credentialsDto.setLogin("testUser");
        credentialsDto.setPassword("testPassword".toCharArray());
    }

    @Test
    public void registerAndLoginWithValidCredentialsReturnsUserDto() {
        UserDto registeredUser = userService.register(signUpDto);
        assertNotNull(registeredUser);

        UserDto loggedInUser = userService.login(credentialsDto);
        assertNotNull(loggedInUser);

        assertEquals(registeredUser.getLogin(), loggedInUser.getLogin());
    }

    @Test
    @Transactional
    public void registerWithExistingLoginThrowsAppException() {
        userService.register(signUpDto);

        assertThrows(AppException.class, () -> userService.register(signUpDto));
    }

    @Test
    @Transactional
    public void loginWithInvalidCredentialsThrowsAppException() {
        assertThrows(AppException.class, () -> userService.login(credentialsDto));
    }

    @Test
    @Transactional
    public void findByLoginWithExistingUserReturnsUserDto() {
        userService.register(signUpDto);

        UserDto userDto = userService.findByLogin("testUser");
        assertNotNull(userDto);
        assertEquals("testUser", userDto.getLogin());
    }

    @Test
    @Transactional
    public void findByLoginWithNonExistingUserThrowsAppException() {
        assertThrows(AppException.class, () -> userService.findByLogin("nonExistingUser"));
    }
}