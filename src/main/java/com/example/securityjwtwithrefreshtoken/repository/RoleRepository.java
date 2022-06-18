package com.example.securityjwtwithrefreshtoken.repository;

import com.example.securityjwtwithrefreshtoken.entity.Role;
import com.example.securityjwtwithrefreshtoken.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleType(RoleType roleType);

}
