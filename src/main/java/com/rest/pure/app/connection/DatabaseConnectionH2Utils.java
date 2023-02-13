package com.rest.pure.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseConnectionH2Utils {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/pure_java";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";

    private static Connection con = null;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
    {
        return con;
    }

    public static boolean H2_HEALTH_CHECK() throws SQLException {
        Connection con = DatabaseConnectionH2Utils.getConnection();
        PreparedStatement st = con.prepareStatement("SCRIPT TO");
        boolean result = st.execute();
        CLOSE_CONNECTION_WITH_STATEMENT(con,st);
        return result;
    }

    public static void CLOSE_CONNECTION(Connection connection) throws SQLException {
        connection.close();
    }

    public static void CLOSE_CONNECTION_WITH_STATEMENT(Connection connection, PreparedStatement statement) throws SQLException {
        statement.close();
        connection.close();
    }

    public static int executeUpdate(String query, HashMap<Integer, Object> params) throws SQLException {
        Connection con = DatabaseConnectionH2Utils.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        for (Map.Entry<Integer, Object> set : params.entrySet()) {
            ps.setObject(set.getKey(), set.getValue());
        }
        int n = ps.executeUpdate();
        CLOSE_CONNECTION_WITH_STATEMENT(con,ps);
        return n;
    }

    public static ResultSet executeQuery(String query, HashMap<Integer, Object> params) throws SQLException {
        Connection con = DatabaseConnectionH2Utils.getConnection();
        PreparedStatement ps = con.prepareStatement(query);
        for (Map.Entry<Integer, Object> set : params.entrySet()) {
            ps.setObject(set.getKey(), set.getValue());
        }
        ResultSet resultSet =  ps.executeQuery();
        CLOSE_CONNECTION_WITH_STATEMENT(con,ps);
        return resultSet;
    }
}
