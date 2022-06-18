package com.example.securityjwtwithrefreshtoken.service;

import com.example.securityjwtwithrefreshtoken.dto.UserDto;
import com.example.securityjwtwithrefreshtoken.entity.Role;
import com.example.securityjwtwithrefreshtoken.entity.RoleType;
import com.example.securityjwtwithrefreshtoken.entity.User;
import com.example.securityjwtwithrefreshtoken.exception.ResourceAlreadyExistsException;
import com.example.securityjwtwithrefreshtoken.exception.ResourceNotFoundException;
import com.example.securityjwtwithrefreshtoken.mapper.UserMapper;
import com.example.securityjwtwithrefreshtoken.repository.RoleRepository;
import com.example.securityjwtwithrefreshtoken.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userDto) {
        String username = userDto.getUsername();

        if (userRepository.existsByUsername(username)) {
            throw new ResourceAlreadyExistsException(
                    "User with username" + username + " already exists"
            );
        }

        User userToSave = userMapper.mapToUser(userDto);
        userToSave.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(userToSave);

        return userMapper.mapToUserDto(savedUser);
    }

    @Transactional
    public void assignRoleToUser(String username, String roleName) {
        User user = this.findUserByUsername(username);

        Role role = roleRepository.findByRoleType(RoleType.fromString(roleName))
                .orElseThrow(() ->
                        new ResourceNotFoundException("Role not found with name: " + roleName));

        user.addRole(role);
    }

    public UserDto getUserByUsername(String username) {
        User user = this.findUserByUsername(username);

        return userMapper.mapToUserDto(user);
    }

    private User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not with username: " + username
                        )
                );
    }

}
