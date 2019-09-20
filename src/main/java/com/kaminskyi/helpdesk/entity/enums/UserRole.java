package com.kaminskyi.helpdesk.entity.enums;

public enum UserRole {
    ROLE_ADMIN("Admin"), ROLE_USER("User"), ROLE_AGENT("Agent"), ROLE_AUDITOR("Auditor");

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    private UserRole(String roleName) {
        this.roleName = roleName;
    }
}
