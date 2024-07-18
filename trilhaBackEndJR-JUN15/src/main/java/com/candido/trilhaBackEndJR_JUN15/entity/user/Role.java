package com.candido.trilhaBackEndJR_JUN15.entity.user;

public enum Role {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String role;

    private Role(String role) {
        this.role = role;
    }

    public String getTypeRole() {
        return role;
    }

}
