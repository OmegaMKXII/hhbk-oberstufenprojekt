package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

/**
 * Created by monikaschepan on 02.05.17.
 */
public class MainWindowView implements FxmlView<LoginViewModel>{


    @FXML
    Label ausbildungsjahrLabel;

    @FXML
    Label ausbildungsberufLabel;

    @FXML
    Button templateGenerierenButton;

    @FXML
    Button benutzerverwaltungButton;

    @FXML
    Button templateLoeschenButton;

    @FXML
    Button pdfGenerierenButton;

    @FXML
    ListView listviewMainWIndow;

    @FXML
    ComboBox<String> ausbidungsberufComboBox;

    @FXML
    ComboBox<Integer> ausbildungsjahrComboBox;

    @InjectViewModel
    LoginViewModel viewModel;

    @FXML
    public void initialize() {
        pdfGenerierenButton.setVisible(false);
        String status = viewModel.currentUser.getRole();
        switch (status)
        {
            case "Lehrer":
                benutzerverwaltungButton.setVisible(false);
                templateLoeschenButton.setVisible(true);
                templateGenerierenButton.setVisible(true);
                pdfGenerierenButton.setVisible(true);
                break;

            case "Gast":
                benutzerverwaltungButton.setVisible(false);
                templateGenerierenButton.setVisible(false);
                templateLoeschenButton.setVisible(false);
                pdfGenerierenButton.setVisible(true);
                break;

            case "Admin":
                benutzerverwaltungButton.setVisible(true);
                templateGenerierenButton.setVisible(true);
                templateLoeschenButton.setVisible(true);
                pdfGenerierenButton.setVisible(true);
                break;

        }

        ausbidungsberufComboBox.getItems().setAll("Fachinformatiker");
        ausbildungsjahrComboBox.getItems().setAll(1,2,3);
        ausbidungsberufComboBox.setPromptText("Bitte auswählen");
        ausbildungsjahrComboBox.setPromptText("Bitte auswählen");
    }

    /**
     * Event für die ComboBox für den Ausbildungsberuf
     * @param actionEvent
     */
    public void ausbidungsberufComboBoxEvent(ActionEvent actionEvent) {
    }

    /**
     * Event für die ComboBox für das Ausbildungsjahr
     * @param actionEvent
     */
    public void ausbidungsjahrComboBoxEvent(ActionEvent actionEvent) {
    }

    /**
     *
     * @param mouseEvent
     */
    public void pdfGenerierenButtonEvent(MouseEvent mouseEvent) {

    }

    /**
     *
     * @param mouseEvent
     */
    public void templateGenerierenButtonEvent(MouseEvent mouseEvent) {
        System.out.print(viewModel.getUsername());
        Main.switchToTemplate();
    }

    /**
     *
     * @param mouseEvent
     */
    public void templateLoeschenButtonEvent(MouseEvent mouseEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Möchten Sie das Template wirklich löschen?");
        alert.showAndWait();
    }

    /**
     * a
     * @param mouseEvent
     */
    public void benutzerverwaltungButtonEvent(MouseEvent mouseEvent) {
        Main.switchToBenutzer();

    }
}
