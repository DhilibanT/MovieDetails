package net.sqlitetutorial;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;


public class SelectApp {

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

    

    public void selectAll(){
        String sql = "SELECT id, name, Actor, Actress, Director, yor FROM Movies";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getString("Actor") + "\t" +
                                   rs.getString("Actress") + "\t" +
                                   rs.getString("Director") + "\t" +
                                   rs.getDouble("yor"));

            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void getMovieswithActor(String input){
        String sql = "SELECT id, name, Actor, Actress, Director, yor "
                + "FROM Movies WHERE Actor ='" +input+"'";

        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("Actor") + "\t" +
                        rs.getString("Actress") + "\t" +
                        rs.getString("Director") + "\t" +
                        rs.getDouble("yor"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public static void main(String[] args) {
        SelectApp app = new SelectApp();
        System.out.println("Print all Without Parameter");
        app.selectAll();


        System.out.println("With Parameter");
        app.getMovieswithActor("Rajini");
    }

}