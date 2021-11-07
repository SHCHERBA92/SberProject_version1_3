package com.example.firstproject.model;

public enum Permission {
    PERMISSION_READ("people:read"),
    PERMISSION_WRITE("people:write");

    private String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
