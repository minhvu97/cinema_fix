package com.green.cinemamanagement.views;

import com.green.cinemamanagement.constant.ColorTheme;
import com.green.cinemamanagement.constant.FontSize;
import com.green.cinemamanagement.controllers.*;
import com.green.cinemamanagement.managers.DBManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private FontSize fontSize = FontSize.SMALL;
    private ColorTheme colorTheme = ColorTheme.DEFAULT;

    private DBManager dbManager;

    public ViewFactory() {

    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public ViewFactory(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void showLoginWindow()
    {
        System.out.println("showing login window");
        BaseController controller = new LoginWindow(this, "LoginWindow.fxml");
        initializeStage(controller);
    }

    public void showMainWindow()
    {
        System.out.println("showing main window");
        BaseController controller = new MainWindow(this, "MainWindow.fxml");
        initializeStage(controller);
    }

    public void showOptionWindow()
    {
        System.out.println("showing option window");
        BaseController controller = new OptionWindow(this, "OptionWindow.fxml");
        initializeStage(controller, true);

    }

    public void showStaffWindow()
    {
        System.out.println("showing staff window");
        BaseController controller = new StaffWindow(this, "StaffWindow.fxml");
        initializeStage(controller);

    }

    public void showAddMemberWindow(AddMemberWindow.AddMemberInterface listener)
    {
        System.out.println("showing add member window");
        BaseController controller = new AddMemberWindow(this, "AddMemberWindow.fxml", listener);
        initializeStage(controller);

    }

    public void closeStage(Stage stage)
    {
        stage.close();
    }

    private void initializeStage ( BaseController controller)
    {
        initializeStage(controller, false);
    }

    private void initializeStage ( BaseController controller, boolean isModal)
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(controller.getFxmlName()));
        fxmlLoader.setController(controller);

        Parent parent = null;
        try {
            parent = fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("initializeStage: failed to load fxml");
            e.printStackTrace();
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        if (isModal)
        {
            stage.initModality(Modality.APPLICATION_MODAL);// dong nay de block all checkbox khi message hien len
        }
        stage.setScene(scene);
        stage.setTitle(controller.getFxmlName());
        stage.show();
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        this.fontSize = fontSize;
    }

    public ColorTheme getColorTheme() {
        return colorTheme;
    }

    public void setColorTheme(ColorTheme colorTheme) {
        this.colorTheme = colorTheme;
    }
}
