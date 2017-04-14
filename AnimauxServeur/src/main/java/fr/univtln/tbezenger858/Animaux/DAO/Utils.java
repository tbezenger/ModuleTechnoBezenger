package fr.univtln.tbezenger858.Animaux.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Tomy Bezenger on 22/02/2017.
 */

public class Utils {
    //public Connection connection;
    public static String url = "jdbc:postgresql://db:5432/animaux";
    public static String user = "animaux";
    public static String password = "password";
    public static Utils ourInstance = new Utils();

    public static Utils getInstance() {
        return ourInstance;
    }

    private Utils() {
        try {
            Connection connection = DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
