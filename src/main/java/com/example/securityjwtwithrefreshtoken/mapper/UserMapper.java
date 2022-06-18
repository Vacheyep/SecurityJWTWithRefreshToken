package com.example.securityjwtwithrefreshtoken.mapper;

import com.example.securityjwtwithrefreshtoken.dto.UserDto;
import com.example.securityjwtwithrefreshtoken.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());

        return user;
    }

    public UserDto mapToUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}
