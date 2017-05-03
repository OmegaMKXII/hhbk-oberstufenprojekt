package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage; // **Declare static Stage**

    private static Scene hauptscene;

    private static Scene templatescene;

    private static Scene benutzerscene;

    private void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    static public Stage getPrimaryStage() {
        return Main.primaryStage;
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Parent haupt = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        hauptscene = new Scene(haupt, 900, 800);
        Parent template = FXMLLoader.load(getClass().getResource("template.fxml"));
        templatescene = new Scene(template, 900, 800);
        Parent benutzer = FXMLLoader.load(getClass().getResource("benutzer.fxml"));
        benutzerscene = new Scene(benutzer, 900, 800);
        setPrimaryStage(primaryStage);
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 900, 800));

        primaryStage.show();
    }

    public static void switchToMain()
    {
        primaryStage.setScene(hauptscene);
        primaryStage.setTitle("Main-Window");
    }

    public static void switchToTemplate()
    {
        primaryStage.setScene(templatescene);
        primaryStage.setTitle("Template");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void switchToBenutzer() {

        primaryStage.setScene(benutzerscene);
        primaryStage.setTitle("Benutzerverwaltung");


    }
}

