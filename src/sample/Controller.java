package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.util.Authorisation;

import java.io.IOException;

public class Controller {

    @FXML
    Label loginLabel;

    @FXML
    Label benutzerLabel;

    @FXML
    Label passwortLabel;

    @FXML
    TextField benutzerTextfield;

    @FXML
    TextField passwortTextfield;

    @FXML
    Button loginButton;

    
    
    @FXML
    public void initialize() {
       

    }

    /**
     * Eingaben werden mit den Inhalten der DB auf Korrekheit geprüft
     * Je nach Benutzer öffnet sich eine andere Maske (Lehrer, Gast, Admin)
     * @param mouseEvent
     */
    public void loginButtonEvent(MouseEvent mouseEvent) throws IOException {

        Main.switchToMain();
        
    }

    /**
     *  je nach Rolle werden Buttons angezeigt oder nicht
     * @return
     */
    public static String getStatus()
    {

        String status = Authorisation.ADMIN.toString();
        return status;
    }



}
