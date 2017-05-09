package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.model.base.Ausbildung;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlAusbildungDAO;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.hhbk.wizardpdfgen.viewmodel.MainWindowViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.List;

/**
 * Created by monikaschepan on 02.05.17.
 */
public class MainWindowView implements FxmlView<MainWindowViewModel>{


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
    MainWindowViewModel viewModel;

    @FXML
    public void initialize() {
        pdfGenerierenButton.setVisible(false);
        AuthorisationLevel status = LoginViewModel.currentUser.getRole();
        switch (status)
        {
            case TEACHER:
                benutzerverwaltungButton.setVisible(false);
                templateLoeschenButton.setVisible(true);
                templateGenerierenButton.setVisible(true);
                pdfGenerierenButton.setVisible(true);
                break;

            case GUEST:
                benutzerverwaltungButton.setVisible(false);
                templateGenerierenButton.setVisible(false);
                templateLoeschenButton.setVisible(false);
                pdfGenerierenButton.setVisible(true);
                break;

            case ADMIN:
                benutzerverwaltungButton.setVisible(true);
                templateGenerierenButton.setVisible(true);
                templateLoeschenButton.setVisible(true);
                pdfGenerierenButton.setVisible(true);
                break;
            default:
                break;
        }

        List<Ausbildung> ausbildungList = MySqlAusbildungDAO.selectAusbildungsberuf();
        for (Ausbildung a :ausbildungList)
        {
            ausbidungsberufComboBox.getItems().setAll(a.getAusbildungsberuf());
        }

     //   ausbidungsberufComboBox.getItems().setAll("Fachinformatiker");
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
       // System.out.print(LoginViewModel.currentUser.getUser());
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
        Main.switchToUserAdmin();
    }
}
