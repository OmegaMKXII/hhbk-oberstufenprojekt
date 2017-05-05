package de.hhbk.wizardpdfgen.main;

import de.hhbk.wizardpdfgen.view.LoginView;
import de.hhbk.wizardpdfgen.view.MainWindowView;
import de.hhbk.wizardpdfgen.view.TemplateView;
import de.hhbk.wizardpdfgen.view.UserAdminView;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
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
    public void start(Stage primaryStage) throws Exception {

        Main.stage = primaryStage;

        // Loads the object hierarchy from each FXML document
        final ViewTuple<LoginView, LoginViewModel> viewTupleLogin = FluentViewLoader.fxmlView(LoginView.class).load();
        final Parent loginWindow = viewTupleLogin.getView();

        final ViewTuple<MainWindowView, LoginViewModel> viewTupleMain = FluentViewLoader.fxmlView(MainWindowView.class).load();
        final Parent mainWindow = viewTupleMain.getView();

        final ViewTuple<TemplateView, LoginViewModel> viewTupleTemplate = FluentViewLoader.fxmlView(TemplateView.class).load();
        final Parent templateWindow = viewTupleTemplate.getView();

        final ViewTuple<UserAdminView, LoginViewModel> viewTupleUserAdmin = FluentViewLoader.fxmlView(UserAdminView.class).load();
        final Parent userAdminWindow = viewTupleUserAdmin.getView();

        loginScene = new Scene(loginWindow);
        mainScene = new Scene(mainWindow);
        templateScene = new Scene(templateWindow);
        userAdminScene = new Scene(userAdminWindow);

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");

        primaryStage.show();
    }

    public static void switchToMain() {
        stage.setScene(mainScene);
        stage.setTitle("PDF-Generator");
    }

    public static void switchToTemplate() {
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

