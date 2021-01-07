package com.mazeltov.common.security;

public enum UserPermission {

    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write"),
    REVIEW_READ("review:read"),
    REVIEW_WRITE("review:write"),
    REVIEW_EDIT("review:edit"),
    REVIEW_DELETE("review:delete");

    private final String permission;

    public String getPermission() {
        return permission;
    }

    UserPermission(String permission) {
        this.permission = permission;
    }
}
