package de.hhbk.wizardpdfgen.view;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import de.hhbk.wizardpdfgen.viewmodel.LoginViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.List;
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userNameTextfield.textProperty()
                .bindBidirectional(viewModel.usernameProperty());
        passwordTextfield.textProperty()
                .bindBidirectional(viewModel.passwordProperty());

        loginButton.disableProperty()
                .bind((Bindings.isEmpty(viewModel.usernameProperty())
                        .or(Bindings.isEmpty(viewModel.passwordProperty()))));
    }

    String role;

    Integer id;

    MySqlUserAdministrationDAO mySqlUserAdministrationDAO;

    @FXML
    public void initialize() {

    }

    /**
     * Eingaben werden mit den Inhalten der DB auf Korrekheit geprüft
     * Je nach Benutzer öffnet sich eine andere Maske (Lehrer, Gast, Admin)
     *
     * @param mouseEvent
     */
    public void loginButtonEvent(MouseEvent mouseEvent) throws IOException {


        List<User> userList = MySqlUserAdministrationDAO.selectPasswordByUser(userNameTextfield.getText());
        for (User u : userList) {
            String password = u.getPassword();
            String user = u.getUser();




            if (passwordTextfield.getText().equals(password) && userNameTextfield.getText().equals(user)) {

                role = u.getRole();
                Main.switchToMain();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Das Kennwort oder das Passwort ist falsch");
                alert.showAndWait();

            }
        }
    }

    public static String getStatus() {

        String role = AuthorisationLevel.ADMIN.toString();
        return role;
    }

    public  Integer getUserID() {

        List<User> userList =  MySqlUserAdministrationDAO.selectUserID(userNameTextfield.getText());
        for (User u: userList)
        {
            id = u.getId();
        }
        return id;
    }


}
