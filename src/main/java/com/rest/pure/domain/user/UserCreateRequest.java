package com.rest.pure.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
