package de.hhbk.wizardpdfgen.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import de.hhbk.wizardpdfgen.main.Main;

/**
 * Created by monikaschepan on 02.05.17.
 */
public class UserAdminView {

    @FXML
    ListView listviewBenutzer;

    @FXML
    Button benutzerHinzufuegenButton;

    @FXML
    Button benutzerLoeschenButton;

    @FXML
    Button zurueckButton;

    @FXML
    TextField benutzerNameTextfield;

    @FXML
    TextField benutzerPasswortTextfield;

    @FXML
    Label benutzerNameLabel;

    @FXML
    Label benutzerPasswortLabel;

    @FXML
    Label BenutzerVerwaltungLabel;


    public void TemplateGenerierenButtonEvent(MouseEvent mouseEvent) {
    }

    public void benutzerLoeschenButtonEvent(MouseEvent mouseEvent) {
    }

    public void benutzerHinzufuegenButtonEvent(MouseEvent mouseEvent) {
    }

    public void zurueckButtonEvent(MouseEvent mouseEvent) {
        Main.switchToMain();
    }
}
