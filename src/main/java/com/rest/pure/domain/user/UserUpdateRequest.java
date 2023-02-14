package com.rest.pure.domain.user;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
