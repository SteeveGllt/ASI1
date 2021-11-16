package bases;

import java.sql.*;

public class Ado {
    public static Connection open() {
        Connection cnxDirect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cnxDirect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mediatheque?user=root&password=");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("erreur de chargement du pilote : " + e);
        }
        return cnxDirect;

    }
}