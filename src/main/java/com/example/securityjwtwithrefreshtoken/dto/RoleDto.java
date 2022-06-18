package com.example.securityjwtwithrefreshtoken.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDto {

    @JsonProperty("name")
    private String name;

    public RoleDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
