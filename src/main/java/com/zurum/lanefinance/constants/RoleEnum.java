package com.zurum.lanefinance.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), ACCOUNT_OFFICER("ROLE_ACCOUNT_OFFICER");

    private final String role;

}
