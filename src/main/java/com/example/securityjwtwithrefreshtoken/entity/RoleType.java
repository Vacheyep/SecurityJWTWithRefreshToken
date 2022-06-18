package com.example.securityjwtwithrefreshtoken.entity;

import java.util.Locale;

public enum RoleType {
    USER,
    ADMIN;

    public static RoleType fromString(String value) {
        String upperValue = value.toUpperCase(Locale.US);

        for (RoleType roleType : values()) {
            if (roleType.name().equals(upperValue)) {
                return roleType;
            }
        }

        return null;
    }
}
