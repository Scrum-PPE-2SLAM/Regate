package fr.regate.project.model;
import java.sql.*;

public class BddConnection {
    private Connection con;
    private String url;
    private String user;
    private String password;

    public BddConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void Connexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
            System.out.println("connection established");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void CloseConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
