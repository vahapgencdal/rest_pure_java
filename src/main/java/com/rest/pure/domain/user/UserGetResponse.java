package com.rest.pure.domain.user;

import org.jetbrains.annotations.NotNull;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserGetResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;


    public static UserGetResponse mapFromUser(@NotNull User user){
        return UserGetResponse.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .id(user.getId()).build();
    }
}
