package com.epam.cafe.connection;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/epam_cafe" +
            "?useUnicode=true" +
            "&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false" +
            "&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public Connection create() throws SQLException {
        Driver driver = new Driver();
        DriverManager.registerDriver(driver);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
