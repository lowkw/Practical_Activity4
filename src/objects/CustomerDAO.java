package objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class CustomerDAO {

    public static boolean updateCustomer(int id, String name, String email, String mobile) {
        String updateSQL = String.format("UPDATE customer SET `Name` = '%s', `Email` = '%s', `Mobile` = '%s' WHERE Id = '%d';", name, email, mobile, id);
        int count = DBUtil.executeUpdate(updateSQL);

        if (count == 0) {
            System.out.println("Error. Failed to update customer record !!!");
            return false;
        } else {
            System.out.println("Customer updated.");
            return true;
        }
    }

    public static boolean insertCustomer(int id, String name, String email, String mobile) {
        String insertSQL = String.format("INSERT INTO customer (Id, Name, Email, Mobile) VALUES ('%d','%s','%s','%s');", id, name, email, mobile);
        int count = DBUtil.executeUpdate(insertSQL);

        if (count == 0) {
            System.out.println("Error. Failed to add customer record !!!");
            return false;
        } else {
            System.out.println("A new customer record added.");
            return true;
        }
    }

    public static boolean deleteCustomer(int id) {
        String deleteSQL = String.format("DELETE FROM customer where Id= '%d';", id);
        int count = DBUtil.executeUpdate(deleteSQL);

        if (count == 0) {
            System.out.println("Error. Failed to delete customer record !!!");
            return false;
        } else {
            System.out.println("Customer record deleted.");
            return true;
        }
    }

    public static ObservableList<CustomerModel> searchCustomerById(int id) {
        String query = String.format("SELECT * FROM customer WHERE Id= '%d';", id);
        CustomerModel customer = null;
        ObservableList<CustomerModel> custList = null;
        custList = FXCollections.observableArrayList();

        try {
            ResultSet rs = DBUtil.executeQuery(query);

            if (rs.next()) {
                customer = new CustomerModel();
                customer.setId(rs.getInt("Id"));
                customer.setName(rs.getString("Name"));
                customer.setEmail(rs.getString("Email"));
                customer.setMobile(rs.getString("Mobile"));
                custList.add(customer);
            } else {
                return custList;
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception on executeQuery: " + ex.getMessage());
        }

        return custList;
    }

    public static ObservableList<CustomerModel> getAllCustomers() {
        String query = "SELECT * FROM customer;";
        ObservableList<CustomerModel> custList = FXCollections.observableArrayList();

        try {
            ResultSet rs = DBUtil.executeQuery(query);
            while (rs.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setId(rs.getInt("Id"));
                customer.setName(rs.getString("Name"));
                customer.setEmail(rs.getString("Email"));
                customer.setMobile(rs.getString("Mobile"));
                custList.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println("SQL Exception on executeQuery" + ex.getMessage());
        }
        return custList;
    }
}
