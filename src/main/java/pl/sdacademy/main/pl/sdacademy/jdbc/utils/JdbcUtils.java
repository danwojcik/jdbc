package pl.sdacademy.main.pl.sdacademy.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    public static JdbcUtils instance;

    private Connection connection;

    private JdbcUtils(){
        String connectionString = "jdbc:mysql://localhost:3306/runcenter";
        Properties prop = new Properties();
        prop.put("password", "");
        prop.put("user", "root");
        prop.put("serverTimezone", "Europe/Warsaw");
        try {
            connection = DriverManager.getConnection(connectionString, prop);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JdbcUtils getINSTANCE() {
        if(instance == null){
            instance = new JdbcUtils();
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }

}
