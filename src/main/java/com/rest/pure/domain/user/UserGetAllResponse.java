package com.rest.pure.domain.user;

import org.jetbrains.annotations.NotNull;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserGetAllResponse {
    private String id;
    private String firstName;
    private String lastName;

    public static UserGetAllResponse mapFromUser(@NotNull User user){
        return UserGetAllResponse.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .id(user.getId()).build();
    }
}
