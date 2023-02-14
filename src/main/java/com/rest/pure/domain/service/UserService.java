package com.rest.pure.domain.service;

import java.util.List;

import com.rest.pure.domain.user.User;
import com.rest.pure.domain.user.UserCreateRequest;
import com.rest.pure.domain.user.UserGetAllResponse;
import com.rest.pure.domain.user.UserGetResponse;
import com.rest.pure.domain.user.UserUpdateRequest;

public interface UserService {
    public void add(UserCreateRequest user) throws Exception;
    public void delete(String id) throws Exception;
    public UserGetResponse getUser(String id) throws Exception;
    public List<UserGetAllResponse> getUsers() throws Exception;
    public void update(UserUpdateRequest emp, String id) throws Exception;
}
