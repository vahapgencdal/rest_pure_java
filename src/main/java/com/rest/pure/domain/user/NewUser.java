package com.rest.pure.domain.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewUser {

    String login;
    String password;
}
