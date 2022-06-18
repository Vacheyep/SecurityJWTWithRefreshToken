package com.example.securityjwtwithrefreshtoken;

import com.example.securityjwtwithrefreshtoken.dto.UserDto;
import com.example.securityjwtwithrefreshtoken.entity.Role;
import com.example.securityjwtwithrefreshtoken.entity.RoleType;
import com.example.securityjwtwithrefreshtoken.entity.User;
import com.example.securityjwtwithrefreshtoken.repository.RoleRepository;
import com.example.securityjwtwithrefreshtoken.repository.UserRepository;
import com.example.securityjwtwithrefreshtoken.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "Reverse.am",
                version = "1",
                description = "Reverse app",
                contact = @Contact(name = "bootcamp", email = "aca.bootcamp@gmail.com")))
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
@SpringBootApplication
public class SecurityJwtWithRefreshTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityJwtWithRefreshTokenApplication.class, args);
    }

   // @Bean
    public CommandLineRunner commandLineRunner(UserService userService,
                                               UserRepository userRepository,
                                               RoleRepository roleRepository) {
        return args -> {
            UserDto user = new UserDto();
            user.setName("name1");
            user.setUsername("username1");
            user.setPassword("password1");

            Role role = new Role();
            role.setRoleType(RoleType.USER);

            userService.createUser(user);
            roleRepository.save(role);

            userService.assignRoleToUser("username1", "user");
        };
    }

}
