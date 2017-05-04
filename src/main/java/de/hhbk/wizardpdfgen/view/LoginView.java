package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.main.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginView implements FxmlView<LoginViewModel>, Initializable {

    @FXML
    Label loginLabel;
    @FXML
    Label usernameLabel;
    @FXML
    Label passwordLabel;
    @FXML
    TextField userNameTextfield;
    @FXML
    TextField passwordTextfield;

    @FXML
    Button loginButton;

    @InjectViewModel
    LoginViewModel viewModel;


//    public void initialize() {
//        userNameTextfield.textProperty()
//                .bindBidirectional(viewModel.usernameProperty());
//        passwordTextfield.textProperty()
//                .bindBidirectional(viewModel.passwordProperty());
//
//        loginButton.disableProperty()
//                .bindBidirectional(viewModel.isLoginPossibleProperty());
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userNameTextfield.textProperty()
                .bindBidirectional(viewModel.usernameProperty());
        passwordTextfield.textProperty()
                .bindBidirectional(viewModel.passwordProperty());

        loginButton.disableProperty()
                .bindBidirectional(viewModel.isLoginPossibleProperty());
    }

    /**
     * Eingaben werden mit den Inhalten der DB auf Korrekheit geprüft
     * Je nach Benutzer öffnet sich eine andere Maske (Lehrer, Gast, Admin)
     *
     * @param mouseEvent
     */
    public void loginButtonEvent(MouseEvent mouseEvent) throws IOException {

        viewModel.logIn();

        Main.switchToMain();
    }


    /**
     * je nach Rolle werden Buttons angezeigt oder nicht
     *
     * @return
     */
    public static String getStatus() {

        String status = AuthorisationLevel.ADMIN.toString();
        return status;
    }
}
