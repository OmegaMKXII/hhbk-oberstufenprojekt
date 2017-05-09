package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class LoginView implements FxmlView<LoginViewModel> {

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

    private static Logger logger = LogManager.getLogger(LoginView.class);

    /**
     * Set bindings between noe properties and properties of its view model.
     */
    public void initialize() {
        userNameTextfield.textProperty()
                .bindBidirectional(viewModel.usernameProperty());
        passwordTextfield.textProperty()
                .bindBidirectional(viewModel.passwordProperty());

        loginButton.disableProperty()
                .bind((Bindings.isEmpty(viewModel.usernameProperty())
                        .or(Bindings.isEmpty(viewModel.passwordProperty()))));

        logger.info("LoginViewModel initialisiert");
    }

    /**
     * Calls login funtion: {@link LoginViewModel#logIn()}
     * @param mouseEvent event, which triggered action
     */
    public void loginButtonEvent(MouseEvent mouseEvent) throws IOException {
        viewModel.logIn();
    }





}
