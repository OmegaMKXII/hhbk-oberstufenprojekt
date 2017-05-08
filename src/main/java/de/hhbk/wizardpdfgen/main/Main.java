package de.hhbk.wizardpdfgen.main;

import de.hhbk.wizardpdfgen.view.LoginView;
import de.hhbk.wizardpdfgen.view.MainWindowView;
import de.hhbk.wizardpdfgen.view.TemplateView;
import de.hhbk.wizardpdfgen.view.UserAdminView;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.hhbk.wizardpdfgen.viewmodel.MainWindowViewModel;
import de.hhbk.wizardpdfgen.viewmodel.TemplateViewModel;
import de.hhbk.wizardpdfgen.viewmodel.UserAdminViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    private static Stage stage;

    private static Scene loginScene;
    private static Scene mainScene;
    private static Scene templateScene;
    private static Scene userAdminScene;

    private static Logger logger = LogManager.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception {

        logger.info("Applikation gestartet");
        Main.stage = primaryStage;

        // Loads the object hierarchy from each FXML document
        final ViewTuple<LoginView, LoginViewModel> viewTupleLogin = FluentViewLoader.fxmlView(LoginView.class).load();
        final Parent loginWindow = viewTupleLogin.getView();

       // final ViewTuple<MainWindowView, MainWindowViewModel> viewTupleMain = FluentViewLoader.fxmlView(MainWindowView.class).load();
        //final Parent mainWindow = viewTupleMain.getView();

        final ViewTuple<TemplateView, TemplateViewModel> viewTupleTemplate = FluentViewLoader.fxmlView(TemplateView.class).load();
        final Parent templateWindow = viewTupleTemplate.getView();

        //final ViewTuple<UserAdminView, UserAdminViewModel> viewTupleUserAdmin = FluentViewLoader.fxmlView(UserAdminView.class).load();
        //final Parent userAdminWindow = viewTupleUserAdmin.getView();

        loginScene = new Scene(loginWindow);
        templateScene = new Scene(templateWindow);

        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login");

        primaryStage.show();
    }

    public static void switchToMain() {
        final ViewTuple<MainWindowView, MainWindowViewModel> viewTupleMain = FluentViewLoader.fxmlView(MainWindowView.class).load();
        final Parent mainWindow = viewTupleMain.getView();
        mainScene = new Scene(mainWindow);
        stage.setScene(mainScene);
        stage.setTitle("PDF-Generator");
    }

    public static void switchToTemplate() {
        stage.setScene(templateScene);
        stage.setTitle("Template");
    }

    public static void switchToUserAdmin() {
        final ViewTuple<UserAdminView, UserAdminViewModel> viewTupleUserAdmin = FluentViewLoader.fxmlView(UserAdminView.class).load();
        final Parent userAdminWindow = viewTupleUserAdmin.getView();
        userAdminScene = new Scene(userAdminWindow);
        stage.setScene(userAdminScene);
        stage.setTitle("Benutzerverwaltung");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

