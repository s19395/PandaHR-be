package com.s1935.pandahr.assembler;

import com.s1935.pandahr.backend.Role;
import com.s1935.pandahr.backend.SignUpDto;
import com.s1935.pandahr.backend.User;
import com.s1935.pandahr.backend.UserDto;
import com.s1935.pandahr.backend.assembler.UserAssembler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserAssemblerTest {

    private UserAssembler userAssembler;

    @BeforeEach
    void setUp() {
        userAssembler = new UserAssembler();
    }

    @Test
    void shouldReturnCorrectUserDtoFromUser() {
        // Given
        User user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setLogin("johndoe");
        user.setToken("token");
        user.setRole(Role.valueOf("USER"));

        // When
        UserDto userDto = userAssembler.toDto(user);

        // Given
        assertEquals(1L, userDto.getId());
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("johndoe", userDto.getLogin());
        assertEquals("token", userDto.getToken());
        assertEquals(Role.valueOf("USER"), userDto.getRole());
    }

    @Test
    @DisplayName("returns null when converting null User to UserDto")

    void shouldReturnNullUserDtoWhenUserDtoIsNull() {
        // Given

        // When
        UserDto userDto = userAssembler.toDto(null);

        // Then
        assertNull(userDto);
    }

    @Test
    void shouldReturnUserFromSignUpDto() {
        // Given
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setFirstName("John");
        signUpDto.setLastName("Doe");
        signUpDto.setLogin("johndoe");

        // When
        User user = userAssembler.fromSignUpDto(signUpDto);

        // Then
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("johndoe", user.getLogin());
    }

    @Test
    void shouldReturnUserNullWhenSignUpDtoIsNull() {
        // Given

        // When
        User user = userAssembler.fromSignUpDto(null);

        // Then
        assertNull(user);
    }
}