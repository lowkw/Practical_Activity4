package objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.rowset.*;

public class DBUtil {

    private static final String URL_DB = "jdbc:mysql://localhost:3306/smtbiz";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection con = null;

    public static void connectDatabase() {
        try {
            con = DriverManager.getConnection(URL_DB, USER, PASSWORD);
            con.setAutoCommit(false);
        } catch (SQLException ex) {
            System.out.println("SQL Exception on database smtbiz connection: " + ex.getMessage());
        }
    }

    public static void closeDatabase() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception on database smtbiz close: " + ex.getMessage());
        }
    }

    public static ResultSet executeQuery(String queryStmt) {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;

        try {
            connectDatabase();
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException ex) {
            System.out.println("SQL Exception on executeQuery: " + ex.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                closeDatabase();
            } catch (SQLException ex) {
                System.out.println("SQL Exception caught on database smtbiz closing: " + ex.getMessage());
            }
        }
        return crs;
    }

    public static int executeUpdate(String updateStmt) {
        Statement stmt = null;
        int count;

        try {
            connectDatabase();
            stmt = con.createStatement();
            count = stmt.executeUpdate(updateStmt);

            if (count != 0) {
                con.commit();
                System.out.println("Database changes commited.");
            }
            return count;
        } catch (SQLException ex) {
            System.out.println("SQL Exception on executeUpdate" + ex.getMessage());
            try {
                if (con != null) {
                    con.rollback();
                    System.out.println("Database changes rolled back.");
                }
                return 0;
            } catch (SQLException ex1) {
                System.out.println("SQL Exception on roll back." + ex1.getMessage());
            }
            return 0;
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                closeDatabase();
            } catch (SQLException ex) {
                System.out.println("SQL Exception caught on database smtbiz closing: " + ex.getMessage());
            }
        }
    }
}
