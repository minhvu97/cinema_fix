package com.green.cinemamanagement.controllers;

import com.green.cinemamanagement.managers.DBManager;
import com.green.cinemamanagement.models.Staff;
import com.green.cinemamanagement.views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddMemberWindow extends BaseController implements Initializable {
    public interface AddMemberInterface {
        void onMemberAdded(Staff staff);
    }

    public AddMemberWindow(ViewFactory viewFactory, String fxmlName, AddMemberInterface listener) {
        super(viewFactory, fxmlName);
        this.listener = listener;
    }

    private ArrayList<Staff> listStaff;

    private AddMemberInterface listener;

    @FXML
    private Button btnSave;

    @FXML
    private TextField textFieldID; // tfID

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtFirstName;

    @FXML
    private Label lblError;

    public int getInputId() {
        String text = textFieldID.getText();
        return Integer.parseInt(text);
    }

    public String getTxtLastName() {
        return txtLastName.getText();
    }

    public String getTxtFirstName() {
        return txtFirstName.getText();
    }

    @FXML
    void actSave(ActionEvent event) {

        int id = getInputId();

        if ( viewFactory.getDbManager().findStaftByID(id) == null) {
            //Them nhan Viewn
        } else {
            lblError.setText("this ID has been used");
            return;
        }

//        for (Staff staffTemp : listStaff)
//        {
//            if (staffTemp.getID() == getTextFieldID())
//            {
//                lblError.setText("this ID has been used");
//                return;
//            }
//        }

        Staff staff = new Staff(getInputId(), getTxtFirstName(), getTxtLastName());

        if (listener != null) {
            System.out.println("on member add.");
            listener.onMemberAdded(staff);
        }

        closeStage();
    }

    private void getListStaff()
    {
        // hien thi cac dong du lieu
        DBManager dbManager = new DBManager();
        listStaff = dbManager.initDB();
    }

    private void closeStage()
    {
        Stage stage = (Stage)btnSave.getScene().getWindow();
        viewFactory.closeStage(stage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getListStaff();
    }
}
