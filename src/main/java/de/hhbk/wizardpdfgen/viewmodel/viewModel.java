package de.hhbk.wizardpdfgen.viewmodel;

import de.hhbk.wizardpdfgen.model.base.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import de.saxsys.mvvmfx.ViewModel;

/**
 * Created by user on 03.05.2017.
 */
public class viewModel implements ViewModel {

    User currentUser;

    private StringProperty username = new SimpleStringProperty();
    private StringProperty password = new SimpleStringProperty();

    private BooleanProperty isLoginPossible = new SimpleBooleanProperty();


    public void ViewModel(){
            this.initialiseDependencies();
    }

    public void initialiseDependencies(){
        isLoginPossible.bind(username.isEmpty().or(password.isEmpty()));
    }

    /**
     * Logs in user
     */
    public void logIn() {
        System.out.print(this.username.toString() + " ::: " + this.password.toString());
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

    public boolean isIsLoginPossible() {
        return isLoginPossible.get();
    }

    public BooleanProperty isLoginPossibleProperty() {
        return isLoginPossible;
    }

    public void setIsLoginPossible(boolean isLoginPossible) {
        this.isLoginPossible.set(isLoginPossible);
    }
}
