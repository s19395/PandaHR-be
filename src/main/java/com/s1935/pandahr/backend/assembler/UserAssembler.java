package com.s1935.pandahr.backend.assembler;

import com.s1935.pandahr.backend.SignUpDto;
import com.s1935.pandahr.backend.User;
import com.s1935.pandahr.backend.UserDto;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserAssembler {

    public UserDto toDto(User user) {
        if (isNull(user)) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setLogin(user.getLogin());
        userDto.setToken(user.getToken());
        userDto.setRole(user.getRole());

        return userDto;
    }

    public User fromSignUpDto(SignUpDto signUpDto) {
        if (isNull(signUpDto)) {
            return null;
        }

        User user = new User();

        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setLogin(signUpDto.getLogin());

        return user;
    }
}