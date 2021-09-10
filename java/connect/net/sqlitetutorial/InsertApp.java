package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class InsertApp {


    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/cinema.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void insert(String name, String Actor, String Actress, String Director, double yor) {
        String sql = "INSERT INTO Movies(name,Actor,Actress,Director,yor) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, Actor);
            pstmt.setString(3, Actress);
            pstmt.setString(4, Director);
            pstmt.setDouble(5, yor);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {

        InsertApp app = new InsertApp();

        app.insert("Enthiran","Rajini","AishwaryaRai","Shankar", 2010);
        app.insert("Mersel","Vijay","KajalAgarwal","Atlee", 2017);
        app.insert("Baahubali","Prabhas","Tamannah","Rajamouli", 2015);
        app.insert("Avatar","Sam","Zoe","James Cameron", 2009);
    }

}