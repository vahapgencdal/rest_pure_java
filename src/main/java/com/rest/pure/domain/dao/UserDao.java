package com.rest.pure.domain.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.rest.pure.domain.user.User;

public interface UserDao {

    public int add(User user) throws SQLException;
    public int delete(String id) throws SQLException;
    public Optional<User> getUser(String id) throws SQLException;
    public Optional<User> getUserByEmail(String email) throws SQLException;
    public List<User> getUsers() throws SQLException;
    public int update(User emp) throws SQLException;
}
