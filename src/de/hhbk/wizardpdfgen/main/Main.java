package de.hhbk.wizardpdfgen.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    private static Scene loginScene;
    private static Scene mainScene;
    private static Scene templateScene;
    private static Scene userAdminScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // Loads the object hierarchy from each FXML document
        Parent loginWindow = FXMLLoader.load(getClass().getResource("../../../../resources/loginWindow.fxml"));
        Parent mainWindow = FXMLLoader.load(getClass().getResource("../../../../resources/mainWindow.fxml"));
        Parent templateWindow = FXMLLoader.load(getClass().getResource("../../../../resources/templateWindow.fxml"));
        Parent userAdminWindow = FXMLLoader.load(getClass().getResource("../../../../resources/userAdminWindow.fxml"));

        // Generate scenes by the FO
        mainScene = new Scene(mainWindow, 900, 800);
        templateScene = new Scene(templateWindow, 900, 800);
        userAdminScene = new Scene(userAdminWindow, 900, 800);
        loginScene = new Scene(loginWindow, 900, 800);

        Main.stage = primaryStage;

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");

        primaryStage.show();
    }

    public static void switchToMain()
    {
        stage.setScene(mainScene);
        stage.setTitle("PDF-Generator");
    }

    public static void switchToTemplate()
    {
        stage.setScene(templateScene);
        stage.setTitle("Template");
    }

    public static void switchToBenutzer() {

        stage.setScene(userAdminScene);
        stage.setTitle("Benutzerverwaltung");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

