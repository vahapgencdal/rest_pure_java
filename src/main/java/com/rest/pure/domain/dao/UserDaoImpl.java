package com.rest.pure.domain.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.rest.pure.app.connection.DatabaseConnectionH2Utils;
import com.rest.pure.domain.user.User;

public class UserDaoImpl implements UserDao {

    static Connection con = DatabaseConnectionH2Utils.getConnection();

    @Override
    public int add(@NotNull User newUser) throws SQLException {
        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, newUser.getId());
        params.put(2, newUser.getFirstName());
        params.put(3, newUser.getLastName());
        params.put(4, newUser.getEmail());
        params.put(5, newUser.getRole());
        params.put(6, newUser.getPassword());
        String query = "insert into user(id, first_name, last_name, email, role, password) VALUES (?, ?, ?, ?, ?, ?)";

        return DatabaseConnectionH2Utils.executeUpdate(query, params);
    }

    @Override
    public int delete(String id) throws SQLException {

        String query = "delete from user where id =?";

        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        return DatabaseConnectionH2Utils.executeUpdate(query, params);
    }

    @Override
    public Optional<User> getUser(String id) throws SQLException {

        String query = "select * from user where id= ?";

        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        ResultSet rs = DatabaseConnectionH2Utils.executeQuery(query, params);

        User user = null;
        while (rs.next()) {
            user = mapResultSet(rs);
        }
        return Optional.ofNullable(user);

    }

    @Override
    public Optional<User> getUserByEmail(String email) throws SQLException {
        String query = "select * from user where email= ?";

        HashMap<Integer, Object> params = new HashMap<>();
        params.put(1, email);

        ResultSet rs = DatabaseConnectionH2Utils.executeQuery(query, params);

        User user = null;
        while (rs.next()) {
            user = mapResultSet(rs);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getUsers() throws SQLException {
        String query = "select * from user";
        ResultSet rs = DatabaseConnectionH2Utils.executeQuery(query);
        List<User> ls = new ArrayList();

        while (rs.next()) {
            ls.add(mapResultSet(rs));
        }
        return ls;
    }

    @Override
    public int update(@NotNull User user) throws SQLException {
        String query = "update user set first_name=?, last_name= ?, email= ?, role= ?, password= ? where id = ?";

        HashMap<Integer, Object> params = new HashMap<>();

        params.put(1, user.getFirstName());
        params.put(2, user.getLastName());
        params.put(3, user.getEmail());
        params.put(4, user.getRole());
        params.put(5, user.getPassword());
        params.put(6, user.getId());

        return DatabaseConnectionH2Utils.executeUpdate(query, params);
    }

    private static User mapResultSet(@NotNull ResultSet rs) throws SQLException {
        return User.builder()
            .id(rs.getString("id"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .email(rs.getString("email"))
            .role(rs.getString("role"))
            .password(rs.getString("password")).build();
    }
}
