package de.hhbk.wizardpdfgen.viewmodel;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.UserAdministrationDAO;
import de.hhbk.wizardpdfgen.model.persistence.sql.DAOFactory;
import de.hhbk.wizardpdfgen.view.LoginView;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

/**
 * Author: Kenji Kokubo on 08.05.17<br>
 * This class represents the data and functions of the login view.
 */
public class LoginViewModel implements ViewModel {

    public static User currentUser = new User("Dummy", "Dummy", AuthorisationLevel.GUEST);

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    private static Logger logger = LogManager.getLogger(LoginView.class);

    /**
     * This method reads user input regarding his username and password.<br>
     * These informations are checked with the use of the database.<br>
     * If given username and password combination exists, the user logged in successfully;
     * Otherwise he will be prompted to another attempt.
     */
    public void logIn() {
        // Find user, which matches to given username and password - the combination of username and password is unique

        User usr = null;
        UserAdministrationDAO userAdminDAO = DAOFactory.createUserAdminDAO(DBType.MYSQL_DB);
        try {
            usr = userAdminDAO.getUser(getUsername(),getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(usr != null) {
            String username = usr.getUsername();
            String password = usr.getPassword();

            logger.info("Login als " + getUsername());
            if (getPassword().equals(password) && getUsername().equals(username)) {
                LoginViewModel.currentUser = usr;
                Main.switchToMain();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Das Kennwort oder das Passwort ist falsch");
            alert.showAndWait();
        }
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

}
