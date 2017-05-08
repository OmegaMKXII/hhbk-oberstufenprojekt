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
    Label usernameLabel;

    @FXML
    Label passwordLabel;

    @FXML
    Label BenutzerVerwaltungLabel;

    @InjectViewModel
    UserAdminViewModel viewModel;

    @FXML
    public void initialize() {
        usernameTextfield.textProperty()
                .bindBidirectional(viewModel.usernameProperty());
        passwordTextfield.textProperty()
                .bindBidirectional(viewModel.passwordProperty());

        listviewUser.setItems(viewModel.getUserObservableList());

        viewModel.setListView(this.listviewUser);

        viewModel.initialize();
    }

    public void benutzerLoeschenButtonEvent(MouseEvent mouseEvent) {
        this.viewModel.deleteUser();
    }

    public void benutzerHinzufuegenButtonEvent(MouseEvent mouseEvent) {
        viewModel.addUser();
    }

    public void zurueckButtonEvent(MouseEvent mouseEvent) {
        Main.switchToMain();
    }


}
