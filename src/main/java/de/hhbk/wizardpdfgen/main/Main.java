package de.hhbk.wizardpdfgen.main;

import de.hhbk.wizardpdfgen.view.LoginView;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
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

        Main.stage = primaryStage;

        // Loads the object hierarchy from each FXML document
        final ViewTuple<LoginView, LoginViewModel> viewTuple = FluentViewLoader.fxmlView(LoginView.class).load();
        final Parent loginWindow = viewTuple.getView();
        // Parent loginWindow = FXMLLoader.load(getClass().getResource("../../../../resources/loginWindow.fxml"));
//        Parent mainWindow = FXMLLoader.load(getClass().getResource("../../../../resources/mainWindow.fxml"));
//        Parent templateWindow = FXMLLoader.load(getClass().getResource("../../../../resources/templateWindow.fxml"));
//        Parent userAdminWindow = FXMLLoader.load(getClass().getResource("../../../../resources/userAdminWindow.fxml"));

        // Generate scenes by the FO
        loginScene = new Scene(loginWindow);
//        mainScene = new Scene(mainWindow);
//        templateScene = new Scene(templateWindow);
//        userAdminScene = new Scene(userAdminWindow);


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

