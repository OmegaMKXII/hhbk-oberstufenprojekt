package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import de.hhbk.wizardpdfgen.viewmodel.UserAdminViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by monikaschepan on 02.05.17.
 */
public class UserAdminView implements FxmlView<UserAdminViewModel> {
    @FXML
    ListView listviewUser;

    @FXML
    Button addUserButton;

    @FXML
    Button deleteUserButton;

    @FXML
    Button exitButton;

    @FXML
    TextField usernameTextfield;

    @FXML
    TextField passwordTextfield;

    @FXML
    ComboBox<AuthorisationLevel> userAuthoristaionComboBox;

    @FXML
    Label usernameLabel;

    @FXML
    Label passwordLabel;

    @FXML
    Label userAdminLabel;

    @FXML
    Label userAuthoristaionLabel;

    @InjectViewModel
    UserAdminViewModel viewModel;

    private static Logger logger = LogManager.getLogger(UserAdminView.class);

    @FXML
    public void initialize() {
        usernameTextfield.textProperty()
                .bindBidirectional(viewModel.usernameProperty());
        passwordTextfield.textProperty()
                .bindBidirectional(viewModel.passwordProperty());

        listviewUser.setItems(viewModel.getUserObservableList());

        viewModel.setListView(this.listviewUser);

        try {
            viewModel.initialize();
        } catch (SQLException e) {
            logger.info("Nutzer wird über Funktionsausfall informiert");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Es ist ein Fehler beim Datenbankzugriff aufgetreten");
            alert.showAndWait();
            alert.setTitle("Datenbankzugriff gescheitert");
            alert.setContentText("Folge: Es können keine Nutzerdaten gesehen noch bearbeitet werden.");
        }
    }

    public void deleteUserButtonEvent(MouseEvent mouseEvent) {
        try {
            this.viewModel.deleteUser();
            listviewUser.refresh();
        } catch (SQLException e) {
            logger.info("Nutzer wird über Funktionsausfall informiert");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Es ist ein Fehler beim Datenbankzugriff aufgetreten");
            alert.showAndWait();
            alert.setTitle("Datenbankzugriff gescheitert");
            alert.setContentText("Folge: Es können keine Nutzerdaten bearbeitet werden.");
        }
    }

    public void addUserButtonEvent(MouseEvent mouseEvent) {
        try {
            this.viewModel.addUser();
        } catch (SQLException e) {
            logger.info("Nutzer wird über Funktionsausfall informiert");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Es ist ein Fehler beim Datenbankzugriff aufgetreten");
            alert.showAndWait();
            alert.setTitle("Datenbankzugriff gescheitert");
            alert.setContentText("Folge: Es können keine Nutzerdaten bearbeitet werden.");
        }
    }

    public void zurueckButtonEvent(MouseEvent mouseEvent) {
        Main.switchToMain();
    }

}
