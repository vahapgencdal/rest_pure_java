package com.rest.pure.app;

import com.rest.pure.app.errors.GlobalExceptionHandler;
import com.rest.pure.app.utils.PasswordDeCrypt;
import com.rest.pure.domain.dao.UserDaoImpl;
import com.rest.pure.domain.dao.UserDao;
import com.rest.pure.domain.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pure.domain.service.UserServiceImpl;

class Configuration {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final UserDao USER_REPOSITORY = new UserDaoImpl();

    private static final PasswordDeCrypt PASSWORD_ENC_DEC = new PasswordDeCrypt();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_REPOSITORY, PASSWORD_ENC_DEC);

    private static final GlobalExceptionHandler GLOBAL_ERROR_HANDLER = new GlobalExceptionHandler(OBJECT_MAPPER);

    static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    static UserService getUserService() {
        return USER_SERVICE;
    }

    static UserDao getUserRepository() { return USER_REPOSITORY; }

    public static GlobalExceptionHandler getErrorHandler() {
        return GLOBAL_ERROR_HANDLER;
    }
}
