package objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.collections.ObservableList;

public class FXMLController {

    @FXML
    private Button addButton;

    @FXML
    private TableView<CustomerModel> custTable;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField emailAddTxtFld;

    @FXML
    private TableColumn<CustomerModel, String> emailTableColumn;

    @FXML
    private TextField emailUpdateTxtFld;

    @FXML
    private TextField idAddTxtFld;

    @FXML
    private TextField idDelSearchTxtFld;

    @FXML
    private TableColumn<CustomerModel, Integer> idTableColumn;

    @FXML
    private TextField idUpdateTxtFld;

    @FXML
    private TextField mobileAddTxtFld;

    @FXML
    private TableColumn<CustomerModel, String> mobileTableColumn;

    @FXML
    private TextField mobileUpdateTxtFld;

    @FXML
    private TextField nameAddTxtFld;

    @FXML
    private TableColumn<CustomerModel, String> nameTableColumn;

    @FXML
    private TextField nameUpdateTxtFld;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private Button searchButton;

    @FXML
    private Button showButton;

    @FXML
    private Button updateButton;

    @FXML
    private void initialize() {
        idTableColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailTableColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        mobileTableColumn.setCellValueFactory(cellData -> cellData.getValue().mobileProperty());
    }

    @FXML
    void createDBButtonAction(ActionEvent event) {
        CreateDatabase.createSmtbiz();
    }

    @FXML
    void emailAddAction(ActionEvent event) {

    }

    @FXML
    void emailUpdateAction(ActionEvent event) {

    }

    @FXML
    void idAddAction(ActionEvent event) {

    }

    @FXML
    void idDelSearchAction(ActionEvent event) {

    }

    @FXML
    void idUpdateAction(ActionEvent event) {

    }

    @FXML
    void mobileAddAction(ActionEvent event) {

    }

    @FXML
    void mobileUpdateAction(ActionEvent event) {

    }

    @FXML
    void nameAddAction(ActionEvent event) {

    }

    @FXML
    void nameUpdateAction(ActionEvent event) {

    }

    @FXML
    void showButtonAction(ActionEvent event) {
        clearTextArea();
        try {
            ObservableList<CustomerModel> allCustomers = CustomerDAO.getAllCustomers();
            custTable.setItems(allCustomers);
            clearInputs();
        } catch (Exception ex) {
            System.out.println("Error to list customer information: " + ex);
            clearInputs();
        }
    }

    @FXML
    void searchButtonAction(ActionEvent event) {
        clearTextArea();
        ObservableList<CustomerModel> cust = null;
        try {
            cust = CustomerDAO.searchCustomerById(Integer.valueOf(idDelSearchTxtFld.getText()));
            custTable.setItems(cust);
            clearInputs();
        } catch (Exception ex) {
            custTable.setItems(cust);
            resultTextArea.setText("Invalid SEARCH customer ID " + idDelSearchTxtFld.getText() + ":" + ex);
            clearInputs();
        }
    }

    @FXML
    void addButtonAction(ActionEvent event) {
        clearTextArea();
        try {
            if (CustomerDAO.insertCustomer(Integer.valueOf(idAddTxtFld.getText()), nameAddTxtFld.getText(), emailAddTxtFld.getText(), mobileAddTxtFld.getText())) {
                resultTextArea.setText("Customer ID " + idAddTxtFld.getText() + " information added");
            }
            clearInputs();
        } catch (Exception ex) {
            resultTextArea.setText("Invalid ADD customer ID " + idAddTxtFld.getText() + ":" + ex);
            clearInputs();
        }
    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        clearTextArea();
        try {
            if (CustomerDAO.deleteCustomer(Integer.valueOf(idDelSearchTxtFld.getText()))) {
                resultTextArea.setText("Customer ID " + idDelSearchTxtFld.getText() + " deleted");
            }
            clearInputs();
        } catch (Exception ex) {
            resultTextArea.setText("Invalid DELETE customer ID " + idDelSearchTxtFld.getText() + ":" + ex);
            clearInputs();
        }
    }

    @FXML
    void updateButtonAction(ActionEvent event) {
        clearTextArea();
        try {
            if (CustomerDAO.updateCustomer(Integer.valueOf(idUpdateTxtFld.getText()), nameUpdateTxtFld.getText(), emailUpdateTxtFld.getText(), mobileUpdateTxtFld.getText())) {
                resultTextArea.setText("Customer ID " + idUpdateTxtFld.getText() + " information updated");
            }
            clearInputs();
        } catch (Exception ex) {
            resultTextArea.setText("Invalid UPDATE customer ID " + idUpdateTxtFld.getText() + ":" + ex);
            clearInputs();
        }
    }

    void clearTextArea() {
        resultTextArea.setText("");
    }

    void clearInputs() {
        idAddTxtFld.clear();
        nameAddTxtFld.clear();
        emailAddTxtFld.clear();
        mobileAddTxtFld.clear();
        idDelSearchTxtFld.clear();
        idUpdateTxtFld.clear();
        nameUpdateTxtFld.clear();
        emailUpdateTxtFld.clear();
        mobileUpdateTxtFld.clear();
    }

}
