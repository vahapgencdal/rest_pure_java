package com.rest.pure.app.api.user;

import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pure.app.api.Constants;
import com.rest.pure.app.api.Handler;
import com.rest.pure.app.api.ResponseEntity;
import com.rest.pure.app.api.StatusCode;
import com.rest.pure.app.errors.ApplicationExceptions;
import com.rest.pure.app.errors.GlobalExceptionHandler;
import com.rest.pure.domain.service.UserService;
import com.rest.pure.domain.user.UserCreateRequest;
import com.sun.net.httpserver.HttpExchange;

public class UserHandler extends Handler {

    private final UserService userService;

    public UserHandler(UserService userService, ObjectMapper objectMapper,
                       GlobalExceptionHandler exceptionHandler) {
        super(objectMapper, exceptionHandler);
        this.userService = userService;
    }

    @Override
    protected void execute(HttpExchange exchange) throws Exception {
        byte[] response;
        if ("POST".equals(exchange.getRequestMethod())) {
            ResponseEntity e = doPost(exchange.getRequestBody());
            exchange.getResponseHeaders().putAll(e.getHeaders());
            exchange.sendResponseHeaders(e.getStatusCode().getCode(), 0);
            response = super.writeResponse(e.getBody());
        } else {
            throw ApplicationExceptions.methodNotAllowed(
                "Method " + exchange.getRequestMethod() + " is not allowed for " + exchange.getRequestURI()).get();
        }

        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }

    private ResponseEntity<RegistrationResponse> doPost(InputStream is) throws Exception {
        RegistrationRequest registerRequest = super.readRequest(is, RegistrationRequest.class);

        UserCreateRequest user = UserCreateRequest.builder()
            .email(registerRequest.getLogin())
            .password(registerRequest.getPassword())
            .build();

          userService.add(user);

        RegistrationResponse response = new RegistrationResponse("1");

        return new ResponseEntity<>(response,
            getHeaders(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON), StatusCode.OK);
    }
}
