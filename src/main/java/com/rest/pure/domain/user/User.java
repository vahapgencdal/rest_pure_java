package com.rest.pure.domain.user;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String password;
}
