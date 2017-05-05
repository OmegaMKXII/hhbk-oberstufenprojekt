package de.hhbk.wizardpdfgen.viewmodel;

import de.hhbk.wizardpdfgen.model.base.User;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import de.saxsys.mvvmfx.ViewModel;

/**
 * Created by user on 03.05.2017.
 */
public class LoginViewModel implements ViewModel {

    static User currentUser;

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    /**
     * Logs in user
     */
    public void logIn() {
        //TODO Implement login routine (User from DB shall be set in this method)
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
