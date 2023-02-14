package com.rest.pure.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import com.rest.pure.app.utils.Role;
import com.rest.pure.app.exception.UserNotFoundException;
import com.rest.pure.app.utils.PasswordDeCrypt;
import com.rest.pure.domain.dao.UserDao;
import com.rest.pure.domain.user.User;
import com.rest.pure.domain.user.UserCreateRequest;
import com.rest.pure.domain.user.UserGetAllResponse;
import com.rest.pure.domain.user.UserGetResponse;
import com.rest.pure.domain.user.UserUpdateRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDao userDao;
    private final PasswordDeCrypt passwordDeCrypt;

    @Override
    public void add(UserCreateRequest request) throws Exception {
        User user = User.builder()
            .id(UUID.randomUUID().toString())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordDeCrypt.encrypt(request.getPassword()))
            .role(Role.USER.name()).build();

        userDao.add(user);

    }

    @Override
    public void delete(String id) throws Exception {
        userDao.delete(id);
    }

    @Override
    public UserGetResponse getUser(String id) throws Exception {
        Optional<User> user = userDao.getUser(id);
        if (user.isPresent()){
            return UserGetResponse.mapFromUser(user.get());
        }
        throw new UserNotFoundException("User Not Found with user id: "+ id);
    }

    @Override
    public List<UserGetAllResponse> getUsers() throws Exception {
        return userDao.getUsers().stream()
            .map(UserGetAllResponse::mapFromUser)
            .collect(Collectors.toList());
    }

    @Override
    public void update(@NotNull UserUpdateRequest emp, String id) throws Exception {
        Optional<User> user = userDao.getUser(id);
        if (user.isPresent()){
            User updatedUser = user.get();
            updatedUser.setFirstName(emp.getFirstName());
            updatedUser.setLastName(emp.getLastName());
            updatedUser.setEmail(emp.getEmail());
            updatedUser.setRole(emp.getRole());
            userDao.update(updatedUser);
        }
        throw new UserNotFoundException("User Not Found with user id: "+ id);
    }
}
