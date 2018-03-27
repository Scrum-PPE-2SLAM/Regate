package fr.regate.project.model;
import java.sql.*;

/**
 * 
 * @author Thomas
 *
 */

public class BddConnection {
    private String url;
    private String user;
    private String password;
    private static Connection con;
	private static Statement st;
	private static ResultSet rs;

    /**
     * 
     * @param url 
     * @param user
     * @param password
     */
    public BddConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public static Connection getCon() {
		return con;
	}
    
	public static Statement getSt() {
		return st;
	}
	
	public static ResultSet getRs() {
		return rs;
	}
	
	public static void setCon(Connection con) {
		BddConnection.con = con;
	}

	public static void setSt(Statement st) {
		BddConnection.st = st;
	}

	public static void setRs(ResultSet rs) {
		BddConnection.rs = rs;
	}

	public void Connexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
            System.out.println("connection established");
            st = con.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    public void CloseConnection() {
        try {
            con.close();
            System.out.println("connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
