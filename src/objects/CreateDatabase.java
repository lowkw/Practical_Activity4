package objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

    public static void createSmtbiz() {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";
        Connection con = null;
        Statement stmt = null;
        String query;
        ResultSet result = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            query = "DROP DATABASE IF EXISTS smtbiz;";
            stmt.executeUpdate(query);
            query = "CREATE DATABASE IF NOT EXISTS smtbiz;";
            stmt.executeUpdate(query);
            query = "USE smtbiz;";
            stmt.executeUpdate(query);
            query = """
				CREATE TABLE Customer (
				Id DECIMAL(6) NOT NULL,
				Name VARCHAR(35),
				Email VARCHAR(320),
				Mobile VARCHAR(15),
				PRIMARY KEY(Id)
				);
				""";
            stmt.executeUpdate(query);
            query = """
				INSERT INTO Customer
					(Id, Name, Email, Mobile)
				VALUES
					(111111,"Steve Jobs","steve.jobs@apple.com","+1 1110111"),
					(222222,"Tim Berners-Lee","tim.berners-lee@w3c.com","+1 2220222"),
					(333333,"Bill Gates","bill.gates@microsoft.com","+1 3330333"),
					(444444,"James Gosling","james.gosling@java.com","+1 4440444"),
					(555555,"Linus Torvalds","linus.torvalds@linux.com","+1 5550555");					
				""";
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("SQL Exception on database smtbiz creation: " + ex.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQL Exception caught: " + ex.getMessage());
            }
        }
    }
}
