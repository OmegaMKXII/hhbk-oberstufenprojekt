package de.hhbk.wizardpdfgen.viewmodel;

import de.hhbk.wizardpdfgen.main.Main;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import de.saxsys.mvvmfx.ViewModel;
import javafx.scene.control.Alert;

/**
 * Created by user on 03.05.2017.
 */
public class LoginViewModel implements ViewModel {

    public static User currentUser = new User("dummy", "Dummy", AuthorisationLevel.GUEST);

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    private MySqlUserAdministrationDAO adminDAO = new MySqlUserAdministrationDAO();


    /**
     * Logs in user
     */
    public void logIn() {
        // Find user, whih matches to given username and password - the combination of username and password is unique

        User usr = adminDAO.selectPasswordByUser(getUsername(),getPassword());
        if(usr != null) {
            String password = usr.getPassword();
            String username = usr.getUsername();

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
